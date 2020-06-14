package com.tongji.zhou.neo4jdao.Services.ServicesImpl;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;
import com.tongji.zhou.neo4jdao.Entity.Neo4jNode;
import com.tongji.zhou.neo4jdao.Entity.NodeRelation;
import com.tongji.zhou.neo4jdao.Entity.QueryEntity;
import com.tongji.zhou.neo4jdao.Repository.EntityRepository;
import com.tongji.zhou.neo4jdao.Services.IEntityNodeService;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.neo4j.driver.internal.InternalPath.SelfContainedSegment;
import java.util.*;

@Service("EntityNodeService")
public class EntityNodeServiceImpl implements IEntityNodeService {
    private Set<Long> ids=new HashSet<>();
    private Map<Long,Neo4jNode> id2node=new HashMap<>();
    @Autowired
    private EntityRepository entityRepository;

    @Override
    public boolean saveRelation(List<LinkedNodeInfo> list) {
        if(list==null || list.size()==0){
            return false;
        }
        for(LinkedNodeInfo info :list){
            checkId(info.getId());
            Neo4jNode main_node = entityRepository.findByEntityId(info.getId());
            for(Long id : info.getLinked_ids()){
                checkId(id);
                main_node.addRelation(getRelation(main_node,id2node.get(id)));
            }
            entityRepository.save(main_node);
        }
        return true;
    }

    @Override
    public List<List<Integer>> queryPathByTwoNode(QueryEntity entity) {
        Iterable<Map<String,Object>> iterable;
        switch (entity.getMax_jump_num()){
            case 1: case 2:
                iterable=entityRepository.getRelation2(entity);
                break;
            case 3:
                iterable=entityRepository.getRelation3(entity);
                break;
            default:
                iterable=entityRepository.getRelation4(entity);
                break;
        }
        List<List<Integer>> result = new ArrayList<>();
        for(Map<String,Object> row:iterable){
            List<Integer> tmp_path=new ArrayList<>();
            tmp_path.add(entity.getStart());
            Path.Segment[] path= (Path.Segment[]) row.get("p");
            for(Path.Segment segment:path){
                Map<String, Object> map=segment.end().asMap();
                tmp_path.add(Integer.valueOf(map.get("entityId").toString()));
            }
            result.add(tmp_path);
        }
        return result;
    }

    private void checkId(Long id){
        if(!ids.contains(id)){
            Neo4jNode node=entityRepository.findByEntityId(id);
            if(node==null){
                node=new Neo4jNode();
                node.setEntityId(id);
                node=entityRepository.save(node);
            }
            node.setNextNodes(null);
            ids.add(id);
            id2node.put(id,node);
        }
    }

    private NodeRelation getRelation(Neo4jNode from,Neo4jNode to){
        NodeRelation tmp = new NodeRelation();
        tmp.setFrom(from);
        tmp.setTo(to);
        return tmp;
    }
}

package com.tongji.zhou.neo4jdao.Services.ServicesImpl;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;
import com.tongji.zhou.neo4jdao.Entity.Neo4jNode;
import com.tongji.zhou.neo4jdao.Entity.NodeRelation;
import com.tongji.zhou.neo4jdao.Repository.EntityRepository;
import com.tongji.zhou.neo4jdao.Services.IEntityNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List batch=new ArrayList();
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

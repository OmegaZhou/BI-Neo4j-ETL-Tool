package com.tongji.zhou.neo4jdao;

import com.tongji.zhou.neo4jdao.Entity.QueryEntity;
import com.tongji.zhou.neo4jdao.Repository.EntityRepository;
import com.tongji.zhou.neo4jdao.Services.IEntityNodeService;
import com.tongji.zhou.neo4jdao.Services.IOuterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Neo4jdaoApplicationTests {
    @Resource
    EntityRepository entityRepository;
    @Autowired
    IEntityNodeService entityNodeService;
    @Autowired
    IOuterService outerService;
    @Test
    void contextLoads() {
        //entityRepository.deleteAll();
        //Neo4jNode node1=entityRepository.findByEntityId(1L);
        //Neo4jNode node2=entityRepository.findByEntityId(2L);
        //node1.setEntityId(1L);
        //node2.setEntityId(2L);
        //node1=entityRepository.save(node1);
        //entityRepository.save(node2);
        /*node2.setNextNodes(null);
        NodeRelation nodeRelation=new NodeRelation();
        nodeRelation.setTo(node2);
        nodeRelation.setFrom(node1);
        node1.addRelation(nodeRelation);
        entityRepository.save(node1);*/
        //outerService.getIds(1,100);
        QueryEntity entity=new QueryEntity();
        entity.setStart(4);
        entity.setEnd(5);

        entity.setSkip_num(0);
        entity.setLimit_num(100);

        List<List<Integer>> relations=entityNodeService.queryPathByTwoNode(entity);
        System.out.println("df");
    }

}

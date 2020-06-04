package com.tongji.zhou.neo4jdao.Repository;

import com.tongji.zhou.neo4jdao.Entity.Neo4jNode;
import com.tongji.zhou.neo4jdao.Entity.NodeRelation;
import com.tongji.zhou.neo4jdao.Entity.QueryEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface EntityRepository extends Neo4jRepository<Neo4jNode, Long> {
    Neo4jNode findByEntityId(Long entityId);

    @Query("match p=(n:Neo4jNode{entityId::#{#entity.start}})" +
            "-[:LinkedNode*2..4]->(b:Neo4jNode{entityId::#{#entity.end}})" +
            " return p skip :#{#entity.skip_num} limit :#{#entity.limit_num}")
    Iterable<Map<String, Object>> getRelation(@Param("entity") QueryEntity entity);
}

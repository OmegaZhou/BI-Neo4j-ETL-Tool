package com.tongji.zhou.neo4jdao.Repository;

import com.tongji.zhou.neo4jdao.Entity.Neo4jNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface EntityRepository extends Neo4jRepository<Neo4jNode, Long> {
    Neo4jNode findByEntityId(Long entityId);

}

package com.tongji.zhou.neo4jdao.Entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "LinkedNode")
public class NodeRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Neo4jNode from;
    @EndNode
    private Neo4jNode to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Neo4jNode getFrom() {
        return from;
    }

    public void setFrom(Neo4jNode from) {
        this.from = from;
    }

    public Neo4jNode getTo() {
        return to;
    }

    public void setTo(Neo4jNode to) {
        this.to = to;
    }
}

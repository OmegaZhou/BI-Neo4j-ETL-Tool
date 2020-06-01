package com.tongji.zhou.neo4jdao.Entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Neo4jNode {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name="entityId")
    @Index(unique = true)
    private Long entityId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Relationship(type = "LinkedNode")
    private List<NodeRelation> nextNodes;

    public void addRelation(NodeRelation friendshipRelation){
        if(this.nextNodes == null){
            this.nextNodes = new ArrayList<>();
        }
        this.nextNodes.add(friendshipRelation);
    }

    public List<NodeRelation> getNextNodes() {
        return nextNodes;
    }

    public void setNextNodes(List<NodeRelation> nextNodes) {
        this.nextNodes = nextNodes;
    }
}

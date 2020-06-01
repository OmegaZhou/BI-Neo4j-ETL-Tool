package com.tongji.zhou.neo4jdao.Entity;

import java.util.List;

public class LinkedNodeInfo {
    private Long id;
    private List<Long> linked_ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getLinked_ids() {
        return linked_ids;
    }

    public void setLinked_ids(List<Long> linked_ids) {
        this.linked_ids = linked_ids;
    }
}

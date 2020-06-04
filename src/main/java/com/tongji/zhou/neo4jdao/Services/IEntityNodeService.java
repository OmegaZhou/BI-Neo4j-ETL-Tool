package com.tongji.zhou.neo4jdao.Services;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;
import com.tongji.zhou.neo4jdao.Entity.QueryEntity;

import java.util.List;

public interface IEntityNodeService {
    boolean saveRelation(List<LinkedNodeInfo> lists);
    List<List<Integer>> queryPathByTwoNode(QueryEntity entity);
}

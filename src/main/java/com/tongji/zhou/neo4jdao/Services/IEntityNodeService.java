package com.tongji.zhou.neo4jdao.Services;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;

import java.util.List;

public interface IEntityNodeService {
    boolean saveRelation(List<LinkedNodeInfo> lists);
}

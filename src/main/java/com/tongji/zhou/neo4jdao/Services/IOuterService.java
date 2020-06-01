package com.tongji.zhou.neo4jdao.Services;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;

import java.util.List;

public interface IOuterService {
    List<LinkedNodeInfo> getIds(Integer start_from, Integer length);
    Integer getMaxId();
}

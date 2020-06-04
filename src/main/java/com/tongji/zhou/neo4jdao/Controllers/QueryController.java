package com.tongji.zhou.neo4jdao.Controllers;

import com.tongji.zhou.neo4jdao.Entity.QueryEntity;
import com.tongji.zhou.neo4jdao.Services.IEntityNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private IEntityNodeService service;
    @PostMapping("/query_paths_by_two_nodes")
    public List<List<Integer>> queryNodesPaths(@RequestBody QueryEntity entity){
        return service.queryPathByTwoNode(entity);
    }
}

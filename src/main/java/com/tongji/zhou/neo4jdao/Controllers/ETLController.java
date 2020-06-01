package com.tongji.zhou.neo4jdao.Controllers;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;
import com.tongji.zhou.neo4jdao.Services.IEntityNodeService;
import com.tongji.zhou.neo4jdao.Services.IOuterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/etl")
public class ETLController {
    @Autowired
    private IOuterService outerService;
    @Autowired
    private IEntityNodeService entityNodeService;

    @GetMapping("/test")
    public String test(){
        return "OK";
    }

    @GetMapping("/startETL")
    public String startEtl(){
        try{
            int start_from=1;
            int length=200;
            int end_id=outerService.getMaxId();
            while(start_from<=end_id){
                List<LinkedNodeInfo> infos=outerService.getIds(start_from,length);
                System.out.println(start_from+"/"+end_id);
                entityNodeService.saveRelation(infos);
                start_from+=length;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Fail";
        }

        return "Success";
    }
}

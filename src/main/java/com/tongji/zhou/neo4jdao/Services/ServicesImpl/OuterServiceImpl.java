package com.tongji.zhou.neo4jdao.Services.ServicesImpl;

import com.tongji.zhou.neo4jdao.Entity.LinkedNodeInfo;
import com.tongji.zhou.neo4jdao.Services.IOuterService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("OuterService")
public class OuterServiceImpl implements IOuterService {

    @Override
    public List<LinkedNodeInfo> getIds(Integer start_from, Integer length){
        String uri="http://localhost:6791/query/getBatch/";
        RestTemplate restTemplate=new RestTemplate();
        Map obj=restTemplate.getForObject(uri+start_from.toString()+"/"+length.toString(), Map.class);
        List<Map<String,Object>> objs= (List<Map<String, Object>>) obj.get("data");
        List<LinkedNodeInfo> result = new ArrayList<>();
        for(Map<String,Object> item : objs){
            LinkedNodeInfo info =new LinkedNodeInfo();
            info.setId(Long.valueOf (item.get("uniqueId").toString()));
            List<Map<String,Object>> links= (List<Map<String, Object>>) item.get("links");
            List<Long> tmp_links=new ArrayList<>();
            for(Map<String,Object> link : links){
                tmp_links.add(Long.valueOf(link.get("uniqueId").toString()));
            }
            info.setLinked_ids(tmp_links);
            result.add(info);
        }
        return result;
    }

    @Override
    public Integer getMaxId() {
        String uri="http://localhost:6791/query/getMaxUniqueId/";
        RestTemplate restTemplate=new RestTemplate();
        Map obj=restTemplate.getForObject(uri, Map.class);
        return (int)(obj.get("data"));
    }
}

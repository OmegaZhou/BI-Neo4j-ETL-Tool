package com.tongji.zhou.neo4jdao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Neo4jdaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jdaoApplication.class, args);
    }

}

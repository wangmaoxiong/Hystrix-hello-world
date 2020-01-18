package com.wmx.eureka_client2020.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    /**
     * 获取斑马信息
     * http://localhost:9394/zebra/person/info?id=33980
     *
     * @param id
     * @return
     */
    @GetMapping("zebra/person/info")
    public String info(String id) {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("code", 200);
        objectNode.put("id", id);
        objectNode.put("info", "斑马锦衣卫都指挥使");
        return objectNode.toString();
    }
}

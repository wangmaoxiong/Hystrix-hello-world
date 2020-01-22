package com.wmx.echystrix.feignClient.fallback;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wmx.echystrix.feignClient.ZebraFeignClient;
import org.springframework.stereotype.Component;

@Component
public class ZebraFeignClientFallback implements ZebraFeignClient {
    @Override
    public String info(String id) {
        return createMessage(id);
    }

    @Override
    public String cipher(String code) {
        return createMessage(code);
    }

    private String createMessage(Object param) {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("code", 500);
        objectNode.put("param", param.toString());
        objectNode.put("message", "服务故障");
        objectNode.put("timestamp", System.currentTimeMillis());
        return objectNode.toString();
    }
}

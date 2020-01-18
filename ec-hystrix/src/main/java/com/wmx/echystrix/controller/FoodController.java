package com.wmx.echystrix.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class FoodController {

    @Resource
    private RestTemplate restTemplate;
    private static JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;

    /**
     * http://localhost:9395/leopard/food/foods?id=998760
     * 1、当用户调用本接口时，如果本方法内部出现异常，且没有 try-catch 处理，则会默认自动倒退到 fallbackMethod 指定的方法
     * 2、fallbackMethod 属性指定的回调方法的参数和返回值必须与本接口完全一致，否则抛异常.
     * 3、比如 restTemplate.getForObject 超时或者连接拒绝，此时便会自动倒退到 foods_fallbackMethod 方法并返回给用户，且控制台也不会看到异常
     * 4、再比如本接口内部故意写一个 int a = 1 / 0; 因为没有 try-catch 处理，默认也会自动倒退调用 foods_fallbackMethod 方法，且控制台不会打印异常信息
     * 5、如果本接口内部使用 try{xxx} catch(Xxx e){xxx} 捕获了异常，则默认不再倒退到 foods_fallbackMethod 方法，而是接着继续往后走.
     * SEMAPHORE
     * @param id
     * @return
     */
    @GetMapping("leopard/food/foods")
    @HystrixCommand(fallbackMethod = "foods_fallbackMethod")
    public String foods(String id) {
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("code", 200);
        objectNode.put("id", id);
        objectNode.put("food", "红烧狮子头");

        //因为重点是演示 Hystrix，所以服务调用，这里就直接写死了
        String url = "http://localhost:9394/zebra/person/info?id=33980";
        ObjectNode responseEntity = restTemplate.getForObject(url, ObjectNode.class);
        objectNode.set("info", responseEntity);
        return objectNode.toString();
    }

    public String foods_fallbackMethod(String id) {
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("code", 200);
        objectNode.put("id", id);
        objectNode.put("food", "四喜丸子");
        objectNode.set("info", null);
        return objectNode.toString();
    }
}

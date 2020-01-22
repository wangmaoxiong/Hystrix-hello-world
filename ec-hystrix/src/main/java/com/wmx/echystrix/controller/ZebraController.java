package com.wmx.echystrix.controller;

import com.wmx.echystrix.feignClient.ZebraFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ZebraController {

    @Resource
    private ZebraFeignClient zebraFeignClient;

    /**
     * http://localhost:9395/zebra/info?id=1001
     *
     * @param id
     * @return
     */
    @GetMapping("zebra/info")
    public String info(String id) {
        String cipher = zebraFeignClient.info(id);
        return cipher;
    }

    /**
     * http://localhost:9395/zebra/code?code=88YUT998
     *
     * @param code
     * @return
     */
    @GetMapping("zebra/code")
    public String cipher(String code) {
        System.out.println(code);
        String cipher = zebraFeignClient.cipher(code);
        return cipher;
    }

}

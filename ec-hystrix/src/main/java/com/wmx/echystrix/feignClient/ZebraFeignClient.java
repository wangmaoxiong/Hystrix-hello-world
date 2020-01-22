package com.wmx.echystrix.feignClient;

import com.wmx.echystrix.feignClient.fallback.ZebraFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "EC-ZEBRA", fallback = ZebraFeignClientFallback.class)
public interface ZebraFeignClient {
    /**
     * 获取斑马信息
     *
     * @param id
     * @return
     */
    @GetMapping("zebra/person/info")
    public String info(@RequestParam String id);

    /**
     * 获取密钥
     *
     * @return
     */
    @GetMapping("zebra/cipher/{code}")
    public String cipher(@PathVariable("code") String code);
}

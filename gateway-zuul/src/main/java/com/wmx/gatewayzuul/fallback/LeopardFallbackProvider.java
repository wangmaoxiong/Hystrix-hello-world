package com.wmx.gatewayzuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author @wangmaoxiong
 * org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider：当路由发生故障时提供回退.实现类必须是组件，交由 Spring 容器管理.
 * 1、可以为代理的每一个微服务都提供对应的 FallbackProvider，getRote()方法中返回对应的微服务名称
 * 2、如果要为所有路由统一提供默认回退，则使 getRoute 方法返回 * 或 null
 */
@Component
public class LeopardFallbackProvider implements FallbackProvider {

    /**
     * 指定路由目标微服务名称
     */
    @Override
    public String getRoute() {
        //返回路由代理的微服务名称，即网关 zuul.routes.customers.serviceId 配置的值
        //未配置 serviceId 时，就是 zuul.routes.customers 的 customers
        //如果要为所有路由统一提供默认回退，则使 getRoute 方法返回 * 或 null
        return "snow-leopard";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        //根据不同的异常类型返回不同的 Http 状态
        // HttpStatus.GATEWAY_TIMEOUT 表示网关超时(状态码504)，HttpStatus.INTERNAL_SERVER_ERROR 表示 内部服务器错误（状态码500）
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        //org.springframework.http.client.ClientHttpResponse 接口表示 http 响应，直接使用匿名函数实现此接口
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //org.springframework.http.HttpStatus 是一个枚举，其中提供了所有的 http 状态
                //比如 HttpStatus.OK 状态码是 200，HttpStatus.INTERNAL_SERVER_ERROR 状态码是 500;
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //返回原始状态码，同样可以通过 org.springframework.http.HttpStatus 枚举
                //如 HttpStatus.BAD_REQUEST.value() 返回 400
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //返回状态的文本内容，如 HttpStatus.BAD_REQUEST.getReasonPhrase() 返回 Bad Request
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                //返回响应的正文，浏览器直接访问接口发生回退时，它会直接显示在页面上.
                return new ByteArrayInputStream(("fallback " + LeopardFallbackProvider.this.getRoute()).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                //返回 http 头信息
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
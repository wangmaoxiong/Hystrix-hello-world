package com.wmx.gatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangmaoxiong
 * 1、com.netflix.zuul.ZuulFilter：是整个 Zuul 过滤器构架的超类
 * 2、前置过滤器可在 com.netflix.zuul.context.RequestContext 中设置数据，以供下游过滤器使用 .
 * 3、过滤器必须交由 Spring 容器管理，比如用 @Component 或者 @Bean 等
 */
public class QueryParamPreFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(QueryParamPreFilter.class);

    /**
     * 是否继续执行 run 方法. 返回 true 表示继续执行 run 方法中的业务代码，否则 false 不再执行此过滤器
     * FORWARD_TO_KEY = "forward.to"
     * SERVICE_ID_KEY = "serviceId"
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return !ctx.containsKey(FilterConstants.FORWARD_TO_KEY) && !ctx.containsKey(FilterConstants.SERVICE_ID_KEY);
    }

    /**
     * 指定过滤器执行优先级
     * FilterConstants 定义了过滤器常数。pre_decoration_filter_order：表示前置过滤器优先级，常数值 5
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 过滤器类型
     * PRE_TYPE = "pre"：前缀过滤器
     * ROUTE_TYPE = "route"：路由过滤器
     * POST_TYPE = "post"：后缀过滤器
     * ERROR_TYPE = "error"：错误过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 如果 shouldFilter（）返回 true，则将调用 run 方法。 该方法是 ZuulFilter 的核心方法，用于编写业务逻辑.
     *
     * @return
     */
    @Override
    public Object run() {
        //com.netflix.zuul.context.RequestContext：zuul 的请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取 javax.servlet.http.HttpServletRequest
        HttpServletRequest request = ctx.getRequest();

        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        logger.info("用户 ip = {}，port = {}，wmx = {}", remoteHost, remotePort, request.getParameter("wmx"));

        return null;
    }
}
package com.fist.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1.filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
 *  pre：路由之前
 *  routing：路由之时
 *  post： 路由之后
 *  error：发送错误调用
 * 2.filterOrder：过滤的顺序
 * 3.shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
 * 4.run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 */
@Component
public class MyFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info(String.format("%s>>>%s", request.getMethod(), request.getRequestURI().toString()));
        String token = request.getParameter("token");
        if (token == null) {
            log.warn("token is null!");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is null!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        log.info("ok!");
        return null;
    }
}

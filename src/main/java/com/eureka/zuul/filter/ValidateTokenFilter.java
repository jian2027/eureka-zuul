package com.eureka.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ValidateTokenFilter extends ZuulFilter{

    @Override
    public boolean shouldFilter() {
        // 是否执行此过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 处理过程
        RequestContext context = RequestContext.getCurrentContext() ;
        HttpServletRequest request = context.getRequest() ;
        
        Boolean bool = Boolean.valueOf(request.getParameter("token")) ;

        if(bool) {
            context.setSendZuulResponse(true) ; //是否路由
            context.setResponseStatusCode(200);
            context.set("isSuccess", true);
            
        }else {
            context.setSendZuulResponse(false) ; //是否路由
            context.setResponseStatusCode(400);
            context.set("isSuccess", false);
        }
        
        return null;
    }

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用,四种状态 pre、routing、POST、error
    }

    @Override
    public int filterOrder() {
        // 执行顺序
        return 10;
    }
}
package com.southeast.passbook.security;

import com.southeast.passbook.constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>权限拦截器</h1>
 * 完成权限校验，拦截所有的 http 请求
 * Created by 18351 on 2019/5/7.
 */
@Component
public class AuthCheckIntercepter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //在 HTTP 请求真正被处理前
        String token = httpServletRequest.getHeader(Constants.TOKEN_STRING);

        if (StringUtils.isEmpty(token)) { // HTTP 请求为携带 token，不能通过校验
            throw new Exception("Header 中缺少 " + Constants.TOKEN_STRING + "!");
        }

        if (!token.equals(Constants.TOKEN)) { // token 不一致，认为是非法请求
            throw new Exception("Header 中 " + Constants.TOKEN_STRING + "错误!");
        }

        // token 正确，设置好 token，并返回 true
        AccessContext.setToken(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //在 HTTP 请求真正被处理后
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //抛出异常时，postHandle 不会处理，确定 http 请求之后，才会真正执行 --> 所以会做一些清理工作
        AccessContext.clearAccessKey();
    }
}

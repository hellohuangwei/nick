package com.example.demo.Intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProofInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(IpUtil.getIpAddr(request));
        if ( IpUtil.getIpAddr(request).equals("3.238.177.119") || IpUtil.getIpAddr(request).equals("3.215.79.116") ||IpUtil.getIpAddr(request).equals("54.198.39.23")
        || IpUtil.getIpAddr(request).equals("171.223.208.133") || IpUtil.getIpAddr(request).equals("193.112.38.254") || IpUtil.getIpAddr(request).equals("129.204.100.132") || IpUtil.getIpAddr(request).equals("106.52.4.74")){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
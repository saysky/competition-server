package com.xxx.project.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
// 登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        System.out.println("进入拦截器");
        //开发过程中直接返回true
        System.out.println(session.getId());
        System.out.println(session.getMaxInactiveInterval());
//        return true;

        if (session.getAttribute("userName") != null) {
            return true;
        } else {
            // 返回208状态码，表明该请求是没有session的
            response.setStatus(208);
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {

    }
}

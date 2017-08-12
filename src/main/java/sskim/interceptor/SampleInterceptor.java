package sskim.interceptor;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class SampleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre handle...");
        //현재 실행되는 컨트롤러와 메소드의 정보를 파악

        HandlerMethod method = (HandlerMethod) handler;
        Method methodobj = method.getMethod();

        System.out.println("Bean :" + method.getBean());
        System.out.println("Method : "+ methodobj);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post handle");

        Object result = modelAndView.getModel().get("result");

        if (result != null) {
            request.getSession().setAttribute("result", result);
            response.sendRedirect("/doA");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}

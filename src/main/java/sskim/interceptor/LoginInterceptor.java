package sskim.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";
    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            session.removeAttribute(LOGIN);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO = modelMap.get("userVO");

        if (userVO != null) {

            logger.info("new login success");
            session.setAttribute(LOGIN, userVO);
            //response.sendRedirect("/");
            Object dest = session.getAttribute("dest");
            response.sendRedirect(dest != null ? (String) dest : "/sboard/list");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}

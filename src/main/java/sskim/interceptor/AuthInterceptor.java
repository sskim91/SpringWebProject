package sskim.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {
            logger.info("current user is not logined");

            saveDest(request);

            response.sendRedirect("/user/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    private void saveDest(HttpServletRequest request) {

        String uri = request.getRequestURI();

        String query = request.getQueryString();

        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }

        if (request.getMethod().equals("GET")) {
            logger.info("dest :" + (uri + query));
            request.getSession().setAttribute("dest", uri + query);
        }
    }
}

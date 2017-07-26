package sskim.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllAdvice 의 클래스의 메소드는 발생한 Exception 객체의 타입만을 파라미터로 사용할 수 있고,
 * 일반 Controller와 같이 Model을 파라미터로 사용하는 것은 지원하지 않기 때문에 직접 ModelAndView 타입을 사용하는 형태로 작성해야한다.
 */
@ControllerAdvice
public class CommonExceptionAdvice {

    private static final Logger logger = LogManager.getLogger(CommonExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView errorModelAndView(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error_common");
        modelAndView.addObject("exception", ex);

        return modelAndView;
    }
}

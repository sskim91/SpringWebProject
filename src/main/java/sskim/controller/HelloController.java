package sskim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String Hello() {
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void ajaxTest() {

    }
}

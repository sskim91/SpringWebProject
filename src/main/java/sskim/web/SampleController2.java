package sskim.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
    private static final Logger logger = LogManager.getLogger(SampleController2.class);

    @RequestMapping("doC")
    public String doC(@ModelAttribute("msg") String msg) {
        logger.info("doc called...");
        return "result";
    }
}
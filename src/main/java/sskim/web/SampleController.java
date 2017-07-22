package sskim.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    private static final Logger logger = LogManager.getLogger(SampleController.class);

    @RequestMapping("doA")
    public void doA() {
        logger.info("doA called...");
    }

    @RequestMapping("doB")
    public void doB(){
        logger.info("doB called...");
    }
}

package sskim.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    private static final Logger logger = LogManager.getLogger(SampleController.class);

    @RequestMapping("doA")
    public String doA() {
        logger.info("doA called...");

        return "result";
    }

    @RequestMapping("doB")
    public String doB(Model model){
        logger.info("doB called...");

        model.addAttribute("result", "DOB RESULT");

        return "result";

    }
}

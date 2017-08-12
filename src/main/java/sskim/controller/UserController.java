package sskim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sskim.domain.UserVO;
import sskim.dto.LoginDTO;
import sskim.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {

        UserVO vo = service.login(dto);

        if (vo == null) {
            return;
        }

        model.addAttribute("userVO", vo);
    }
}

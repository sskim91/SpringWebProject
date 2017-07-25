package sskim.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sskim.domain.BoardVO;
import sskim.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    private static final Logger logger = LogManager.getLogger(BoardController.class);

    @Autowired
    private BoardService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET(BoardVO board, Model model) throws Exception {
        logger.info("register get..");
    }

    /**
     * @param board
     * @param model
     * @return
     * @throws Exception
     * @explain 파라미터의 수집은 스프링 MVC에서 자동으로 이루어진다. 파라미터의 수집이 필요하면 원하는 객체를 파라미터로 선언
     *           브라우저에서 들어오는 요청(request)이 자동으로 파라미터로 지정한 클래스의 객체 속성 값으로 처리는되는데 이를 바인딩(binding)이라고 한다.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, Model model) throws Exception {
        logger.info("regist post..");
        logger.info(board.toString());

        service.regist(board);
        model.addAttribute("result", "success");
        return "/board/success";
    }
}
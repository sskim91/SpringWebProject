package sskim.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sskim.domain.BoardVO;
import sskim.domain.Criteria;
import sskim.domain.PageMaker;
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
     * @param rttr
     * @return
     * @throws Exception
     * @explain 파라미터의 수집은 스프링 MVC에서 자동으로 이루어진다. 파라미터의 수집이 필요하면 원하는 객체를 파라미터로 선언
     * 브라우저에서 들어오는 요청(request)이 자동으로 파라미터로 지정한 클래스의 객체 속성 값으로 처리는되는데 이를 바인딩(binding)이라고 한다.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
        logger.info("regist post..");
        logger.info(board.toString());

        service.regist(board);

        rttr.addFlashAttribute("msg", "SUCCESS");
        //model.addAttribute("result", "success");
        //return "/board/success";
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public void listAll(Model model) throws Exception {
        logger.info("show all list...리스트");
        model.addAttribute("list", service.listAll());
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
        service.remove(bno);
        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public void modifyGET(int bno, Model model) throws Exception {
        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

        logger.info("mod post...");
        service.modify(board);
        rttr.addFlashAttribute("msg", "SUCCESS");
        return "redirect:/board/listAll";
    }

    @RequestMapping(value = "/listCri", method = RequestMethod.GET)
    public void listAll(Criteria cri, Model model) throws Exception {
        logger.info("show list Page with Criteria...");
        model.addAttribute("list", service.listCriteria(cri));
    }

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public void listPage(Criteria cri, Model model) throws Exception {

        logger.info(cri.toString());
        model.addAttribute("list", service.listCriteria(cri));
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(service.listCountCriteria(cri));

        model.addAttribute("pageMaker", pageMaker);
    }

    @RequestMapping(value = "/readPage", method = RequestMethod.GET)
    public void read(@RequestParam("bno") int bno,
                     @ModelAttribute("cri") Criteria cri,
                     Model model) throws Exception {

        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/removePage", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") int bno,
                         Criteria cri,
                         RedirectAttributes rttr) throws Exception {

        service.remove(bno);

        rttr.addAttribute("page", cri.getPage());
        rttr.addAttribute("perPageNuM", cri.getPerPageNum());
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listPage";
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
    public void modifyPagingGET(@RequestParam("bno") int bno,
                                @ModelAttribute("cri") Criteria cri,
                                Model model) throws Exception {

        model.addAttribute(service.read(bno));
    }

    @RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
    public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {

        service.modify(board);

        rttr.addAttribute("page", cri.getPage());
        rttr.addAttribute("perPageNum", cri.getPerPageNum());
        rttr.addFlashAttribute("msg", "SUCCESS");

        return "redirect:/board/listPage";
    }
}
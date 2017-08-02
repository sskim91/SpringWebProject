package sskim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sskim.domain.Criteria;
import sskim.domain.PageMaker;
import sskim.domain.ReplyVO;
import sskim.service.ReplyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO vo) {

        ResponseEntity<String> entity = null;
        try {
            service.addReply(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno) {

        ResponseEntity<List<ReplyVO>> entity = null;
        try {
            entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("rno") int rno,
                                         @RequestBody ReplyVO vo) {

        ResponseEntity<String> entity = null;
        try {
            vo.setRno(rno);
            service.modifyReply(vo);

            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("rno") int rno) {

        ResponseEntity<String> entity = null;
        try {
            service.removeReply(rno);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno,
                                                        @PathVariable("page") int page) {

        ResponseEntity<Map<String, Object>> entity = null;

        try {
            Criteria cri = new Criteria();
            cri.setPage(page);

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCri(cri);

            Map<String, Object> map = new HashMap<>();
            List<ReplyVO> list = service.listReplyPage(bno, cri);

            map.put("list", list);

            int replyCount = service.count(bno);
            pageMaker.setTotalCount(replyCount);

            map.put("pageMaker", pageMaker);

            entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}

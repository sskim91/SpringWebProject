package sskim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sskim.dao.BoardDAO;
import sskim.dao.ReplyDAO;
import sskim.domain.Criteria;
import sskim.domain.ReplyVO;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyDAO replyDAO;

    @Autowired
    private BoardDAO boardDAO;

    @Transactional
    @Override
    public void addReply(ReplyVO vo) throws Exception {
        replyDAO.create(vo);
        boardDAO.updateReplyCnt(vo.getBno(), 1);
    }

    @Override
    public List<ReplyVO> listReply(int bno) throws Exception {
        return replyDAO.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO vo) throws Exception {
        replyDAO.update(vo);
    }

    @Transactional
    @Override
    public void removeReply(int rno) throws Exception {
        int bno = replyDAO.getBno(rno);
        replyDAO.delete(rno);
        boardDAO.updateReplyCnt(bno, -1);
    }

    @Override
    public List<ReplyVO> listReplyPage(int bno, Criteria cri) throws Exception {
        return replyDAO.listPage(bno, cri);
    }

    @Override
    public int count(int bno) throws Exception {
        return replyDAO.count(bno);
    }

}

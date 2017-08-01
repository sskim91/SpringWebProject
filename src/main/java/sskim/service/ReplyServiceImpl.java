package sskim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sskim.dao.ReplyDAO;
import sskim.domain.ReplyVO;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyDAO dao;

    @Override
    public void addReply(ReplyVO vo) throws Exception {
        dao.create(vo);
    }

    @Override
    public List<ReplyVO> listReply(int bno) throws Exception {
        return dao.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Override
    public void removeReply(int rno) throws Exception {
        dao.delete(rno);
    }
}

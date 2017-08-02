package sskim.service;

import sskim.domain.Criteria;
import sskim.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public void addReply(ReplyVO vo) throws Exception;

    public List<ReplyVO> listReply(int bno) throws Exception;

    public void modifyReply(ReplyVO vo) throws Exception;

    public void removeReply(int rno) throws Exception;

    //댓글 페이징
    public List<ReplyVO> listReplyPage(int bno, Criteria cri) throws Exception;

    public int count(int bno) throws Exception;
}

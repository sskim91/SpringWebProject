package sskim.service;

import sskim.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public void addReply(ReplyVO vo) throws Exception;

    public List<ReplyVO> listReply(int bno) throws Exception;

    public void modifyReply(ReplyVO vo) throws Exception;

    public void removeReply(int rno) throws Exception;

}

package sskim.dao;

import sskim.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    public List<ReplyVO> list(int bno) throws Exception;

    public void create(ReplyVO vo) throws Exception;

    public void update(ReplyVO vo) throws Exception;

    public void delete(int rno) throws Exception;

}

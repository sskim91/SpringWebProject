package sskim.dao;

import sskim.domain.Criteria;
import sskim.domain.ReplyVO;

import java.util.List;

public interface ReplyDAO {

    public List<ReplyVO> list(int bno) throws Exception;

    public void create(ReplyVO vo) throws Exception;

    public void update(ReplyVO vo) throws Exception;

    public void delete(int rno) throws Exception;

    //댓글 페이징 처리
    public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception;

    public int count(int bno) throws Exception;

    public int getBno(int rno) throws Exception;

}

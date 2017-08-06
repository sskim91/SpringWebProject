package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sskim.domain.Criteria;
import sskim.domain.ReplyVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.ReplyMapper";

    @Override
    public List<ReplyVO> list(int bno) throws Exception {
        return session.selectList(namespace + ".list", bno);
    }

    @Override
    public void create(ReplyVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(int rno) throws Exception {
        session.delete(namespace + ".delete", rno);
    }

    @Override
    public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("cri", cri);

        return session.selectList(namespace + ".listPage", paramMap);
    }

    @Override
    public int count(int bno) throws Exception {
        return session.selectOne(namespace + ".count", bno);
    }

    @Override
    public int getBno(int rno) throws Exception {
        return session.selectOne(namespace + ".getBno", rno);
    }
}

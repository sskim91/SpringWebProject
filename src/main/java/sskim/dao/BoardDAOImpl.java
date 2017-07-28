package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sskim.domain.BoardVO;
import sskim.domain.Criteria;
import sskim.domain.SearchCriteria;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.BoardMapper";

    @Override
    public void create(BoardVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public BoardVO read(int bno) throws Exception {
        return session.selectOne(namespace + ".read", bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(int bno) throws Exception {
        session.delete(namespace + ".delete", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace + ".listAll");
    }

    @Override
    public List<BoardVO> listPage(int page) throws Exception {
        if (page <= 0) {
            page = 1;
        }
        page = (page -1) * 10;
        return session.selectList(namespace+".listPage", page);
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return session.selectList(namespace + ".listCriteria", cri);
    }

    @Override
    public int countPaging(Criteria cri) throws Exception {
        return session.selectOne(namespace + ".countPaging", cri);
    }

    @Override
    public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
        return session.selectList(namespace + ".listSearch", cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return session.selectOne(namespace + ".listSearchCount", cri);
    }
}

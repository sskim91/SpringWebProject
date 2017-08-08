package sskim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sskim.dao.BoardDAO;
import sskim.domain.BoardVO;
import sskim.domain.Criteria;
import sskim.domain.SearchCriteria;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO dao;

    @Transactional
    @Override
    public void regist(BoardVO board) throws Exception {
        dao.create(board);

        String[] files = board.getFiles();

        if (files == null) {
            return;
        }

        for (String fileName : files) {
            dao.addAttach(fileName);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(int bno) throws Exception {
        dao.updateViewCnt(bno);
        return dao.read(bno);
    }

    @Override
    public void modify(BoardVO board) throws Exception {
        dao.update(board);
    }

    @Override
    public void remove(int bno) throws Exception {
        dao.delete(bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return dao.listAll();
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return dao.listCriteria(cri);
    }

    @Override
    public int listCountCriteria(Criteria cri) throws Exception {
        return dao.countPaging(cri);
    }

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return dao.listSearchCount(cri);
    }

    @Override
    public List<String> getAttach(int bno) throws Exception {
        return dao.getAttach(bno);
    }

}
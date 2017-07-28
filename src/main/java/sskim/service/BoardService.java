package sskim.service;

import sskim.domain.BoardVO;
import sskim.domain.Criteria;
import sskim.domain.SearchCriteria;

import java.util.List;

public interface BoardService {
    public void regist(BoardVO board) throws Exception;

    public BoardVO read(int bno) throws Exception;

    public void modify(BoardVO board) throws Exception;

    public void remove(int bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listCriteria(Criteria cri) throws Exception;

    public int listCountCriteria(Criteria cri) throws Exception;

    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

    public int listSearchCount(SearchCriteria cri) throws Exception;
}

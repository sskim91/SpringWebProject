import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sskim.dao.BoardDAO;
import sskim.domain.BoardVO;
import sskim.domain.Criteria;
import sskim.domain.SearchCriteria;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/META-INF/spring/applicationContext.xml"})
public class BoardDAOTest {

    @Autowired
    private BoardDAO dao;

    private static Logger logger = LogManager.getLogger(BoardDAOTest.class);

    @Test
    public void testCreate() throws Exception{
        BoardVO board = new BoardVO();
        board.setTitle("새로운 글을 넣습니다.");
        board.setContent("새로운 글을 넣습니다.");
        board.setWriter("sskim");
        dao.create(board);
    }

    @Test
    public void testRead() throws Exception{
        logger.info(dao.read(1).toString());
    }

    @Test
    public void testUpdate() throws Exception{
        BoardVO board = new BoardVO();
        board.setBno(1);
        board.setTitle("수정된 글입니다.");
        board.setContent("수정 테스트");
        dao.update(board);
    }

    @Test
    public void testDelete() throws Exception{
        dao.delete(1);
    }

    //페이징 처리 SQL 테스트
    @Test
    public void testListPage() throws Exception {
        int page = 3;
        List<BoardVO> list = dao.listPage(page);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }
    }

    @Test
    public void testListCriteria() throws Exception {

        Criteria cri = new Criteria();
        cri.setPage(2);
        cri.setPerPageNum(10);

        List<BoardVO> list = dao.listCriteria(cri);
        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }

    @Test
    public void testURI() throws Exception {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

    @Test
    public void testURI2() throws Exception {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                        .path("/{module}/{page}")
                        .queryParam("bno", 12)
                        .queryParam("perPageNum", 20)
                        .build()
                        .expand("board", "read")
                        .encode();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());
    }

    @Test
    public void testDynamic1() throws Exception {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("글");
        cri.setSearchType("t");

        logger.info("======================");

        List<BoardVO> list = dao.listSearch(cri);

        for (BoardVO board : list) {
            logger.info(board.getBno() + ": " + board.getTitle());
        }

        logger.info("===================");
        logger.info("count : " + dao.listSearchCount(cri));
    }
}

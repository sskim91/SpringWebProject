import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sskim.dao.BoardDAO;
import sskim.domain.BoardVO;

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
}

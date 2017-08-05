package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sskim.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.MessageMapper";

    @Override
    public void create(MessageVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public MessageVO readMessage(int mno) throws Exception {
        return session.selectOne(namespace + ".readMessage", mno);
    }

    @Override
    public void updateState(int mno) throws Exception {
        session.update(namespace + ".updateState", mno);
    }
}

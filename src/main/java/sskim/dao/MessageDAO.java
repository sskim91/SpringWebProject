package sskim.dao;

import sskim.domain.MessageVO;

public interface MessageDAO {

    public void create(MessageVO vo) throws Exception;

    public MessageVO readMessage(int mno) throws Exception;

    public void updateState(int mno) throws Exception;

}

package sskim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sskim.dao.MessageDAO;
import sskim.dao.PointDAO;
import sskim.domain.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private PointDAO pointDAO;

    @Transactional
    @Override
    public void addMessage(MessageVO vo) throws Exception {

        messageDAO.create(vo);
        pointDAO.updatePoint(vo.getSender(), 10);
    }

    @Override
    public MessageVO readMessage(String uid, int mno) throws Exception {

        messageDAO.updateState(mno);
        pointDAO.updatePoint(uid, 5);
        return messageDAO.readMessage(mno);
    }

}

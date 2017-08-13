package sskim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sskim.dao.UserDAO;
import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return dao.login(dto);
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) {
        dao.keepLogin(uid, sessionId, next);
    }

    @Override
    public UserVO checkLoginBefore(String value) {
        return dao.checkUserWithSessionKey(value);
    }

}

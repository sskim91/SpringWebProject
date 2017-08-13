package sskim.dao;

import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

import java.util.Date;

public interface UserDAO {

    public UserVO login(LoginDTO dto) throws Exception;

    //자동 로그인
    public void keepLogin(String uid, String sessionId, Date next);

    public UserVO checkUserWithSessionKey(String value);
}

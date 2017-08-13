package sskim.service;

import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

import java.util.Date;

public interface UserService {

    public UserVO login(LoginDTO dto) throws Exception;

    //자동 로그인
    public void keepLogin(String uid, String sessionId, Date next);

    public UserVO checkLoginBefore(String value);

}

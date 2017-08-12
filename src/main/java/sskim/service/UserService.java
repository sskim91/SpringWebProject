package sskim.service;

import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

public interface UserService {

    public UserVO login(LoginDTO dto) throws Exception;
}

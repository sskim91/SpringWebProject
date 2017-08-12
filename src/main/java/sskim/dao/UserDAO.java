package sskim.dao;

import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

public interface UserDAO {

    public UserVO login(LoginDTO dto) throws Exception;
}

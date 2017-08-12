package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return session.selectOne(namespace + ".login", dto);
    }
}

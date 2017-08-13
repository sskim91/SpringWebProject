package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sskim.domain.UserVO;
import sskim.dto.LoginDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return session.selectOne(namespace + ".login", dto);
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        session.update(namespace + ".keepLogin", paramMap);
    }

    @Override
    public UserVO checkUserWithSessionKey(String value) {
        return session.selectOne(namespace + ".checkUserWithSessionKey", value);
    }
}

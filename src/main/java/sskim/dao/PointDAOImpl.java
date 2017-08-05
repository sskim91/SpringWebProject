package sskim.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PointDAOImpl implements PointDAO {

    @Autowired
    private SqlSession session;

    private static String namespace = "sskim.mapper.PointMapper";

    @Override
    public void updatePoint(String uid, int point) throws Exception {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("point", point);

        session.update(namespace + ".updatePoint", paramMap);

    }
}

package com.oncore.userend.groovy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 3/20/16.
 */
public interface GBaseDao {
    long insert( Map params,String userId) throws SQLException;
    Map get(long id,String userId)  throws SQLException;
    boolean update(long id, Map params,String userId)  throws SQLException;
    boolean delete(long id,String userId)  throws SQLException;
    List list(String userId)  throws SQLException;
    Map list(String userId,int page,int rows) throws SQLException;
}

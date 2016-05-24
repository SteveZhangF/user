package groovy.Company.entity_Company_1459305908432;

import com.oncore.userend.groovy.GBaseDao;
import groovy.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import groovy.sql.GroovyRowResult;
import java.sql.SQLException;
import java.sql.ResultSet;

@Repository()
@Transactional
class Company implements GBaseDao{

@Autowired Sql sql;
@Override
long insert( Map params,String userId) throws SQLException {
String insert = "insert into entity_Company_1459305908432 (Name,Address,Phone,userId,deleted) values(:Name,:Address,:Phone,:userId,false)";
Map map = new HashMap();
map.put("Name",params.get("Name"));
map.put("Address",params.get("Address"));
map.put("Phone",params.get("Phone"));
map.put("userId",userId);
List list = this.sql.executeInsert(map, insert);
return list.get(0).get(0);
}
@Override
Map get(long id,String userId)  throws SQLException {
GroovyRowResult result =  this.sql.firstRow("select id,Name,Address,Phone,userId from entity_Company_1459305908432 where deleted=false and id='"+id + "' and userId='"+userId+"'");
return result;
}
@Override
boolean update(long id, Map params,String userId)  throws SQLException  {
String updateSql = "update entity_Company_1459305908432 set  Name=:Name,Address=:Address,Phone=:Phone  where id=:id and userId=:userId";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
map.put("Name",params.get("Name"));
map.put("Address",params.get("Address"));
map.put("Phone",params.get("Phone"));
return this.sql.executeUpdate(map,updateSql)
}

@Override
boolean delete(long id,String userId)  throws SQLException  {
String deleteSql = "update entity_Company_1459305908432 set deleted=true where userId=:userId and id =:id";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
return this.sql.executeUpdate(map,deleteSql);
}
@Override
List list(String userId)  throws SQLException {
String listSql = "select id,Name,Address,Phone,userId from entity_Company_1459305908432 where deleted=false and  userId='" + userId + "'";
return sql.rows(listSql);
}

    @Override
    Map list(String userId, int page, int row) throws SQLException {
        String listSql = "select id,Name,Address,Phone,userId from entity_Company_1459305908432  where deleted=false and  userId='" + userId + "'";
        String countSQL = "select count(id) from entity_Company_1459305908432 where deleted=false and  userId='" + userId + "'";
        int count;
        sql.query(countSQL, { ResultSet rs ->
            while (rs.next()) count = rs.getInt('count(id)')
        });
        List list = sql.rows(listSql,(page-1)*row,row);
        Map map = new HashMap();
        map.put("total",count);
        map.put("rows",list)
        return map;
    }
}

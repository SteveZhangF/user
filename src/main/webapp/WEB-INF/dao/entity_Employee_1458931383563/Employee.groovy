package groovy.Employee.entity_Employee_1458931383563;

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
class Employee implements GBaseDao{

@Autowired Sql sql;
@Override
long insert( Map params,String userId) throws SQLException {
String insert = "insert into entity_Employee_1458931383563 (First_Name,Last_Name,SSNumber,phone,userId,deleted) values(:First_Name,:Last_Name,:SSNumber,:phone,:userId,false)";
Map map = new HashMap();
map.put("First_Name",params.get("First_Name"));
map.put("Last_Name",params.get("Last_Name"));
map.put("SSNumber",params.get("SSNumber"));
map.put("phone",params.get("phone"));
map.put("userId",userId);
List list = this.sql.executeInsert(map, insert);
return list.get(0).get(0);
}
@Override
Map get(long id,String userId)  throws SQLException {
GroovyRowResult result =  this.sql.firstRow("select id,First_Name,Last_Name,SSNumber,phone,userId from entity_Employee_1458931383563 where deleted=false and id='"+id + "' and userId='"+userId+"'");
return result;
}
@Override
boolean update(long id, Map params,String userId)  throws SQLException  {
String updateSql = "update entity_Employee_1458931383563 set  First_Name=:First_Name,Last_Name=:Last_Name,SSNumber=:SSNumber,phone=:phone  where id=:id and userId=:userId";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
map.put("First_Name",params.get("First_Name"));
map.put("Last_Name",params.get("Last_Name"));
map.put("SSNumber",params.get("SSNumber"));
map.put("phone",params.get("phone"));
return this.sql.executeUpdate(map,updateSql)
}

@Override
boolean delete(long id,String userId)  throws SQLException  {
String deleteSql = "update entity_Employee_1458931383563 set deleted=true where userId=:userId and id =:id";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
return this.sql.executeUpdate(map,deleteSql);
}
@Override
List list(String userId)  throws SQLException {
String listSql = "select id,First_Name,Last_Name,SSNumber,phone,userId from entity_Employee_1458931383563 where deleted=false and  userId='" + userId + "'";
return sql.rows(listSql);
}

    @Override
    Map list(String userId, int page, int row) throws SQLException {
        String listSql = "select id,First_Name,Last_Name,SSNumber,phone,userId from entity_Employee_1458931383563  where deleted=false and  userId='" + userId + "'";
        String countSQL = "select count(id) from entity_Employee_1458931383563 where deleted=false and  userId='" + userId + "'";
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

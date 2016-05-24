package groovy.Employee_Information.report_Employee_Information_1458961598483;

import com.oncore.userend.groovy.ReportGBaseDao;
import groovy.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import groovy.sql.GroovyRowResult;
import java.sql.SQLException;
import java.sql.ResultSet;
@Repository()
@Transactional
class Employee_Information  implements ReportGBaseDao{

@Autowired Sql sql;
@Override
long insert( Map params,String userId) throws SQLException {
String insert = "insert into report_Employee_Information_1458961598483 (Company_Name_4028818953c566f00153c56966a30008,Employee_First_Name_4028818953c566f00153c56966830004,Employee_Last_Name_4028818953c566f00153c56966850005,Employee_SSNumber_4028818953c566f00153c56966870006,Employee_phone_4028818953c566f00153c56966890007,generated,userId,deleted) values(:Company_Name_4028818953c566f00153c56966a30008,:Employee_First_Name_4028818953c566f00153c56966830004,:Employee_Last_Name_4028818953c566f00153c56966850005,:Employee_SSNumber_4028818953c566f00153c56966870006,:Employee_phone_4028818953c566f00153c56966890007,:generated,:userId,false)";
Map map = new HashMap();
map.put("Company_Name_4028818953c566f00153c56966a30008",params.get("Company_Name_4028818953c566f00153c56966a30008"));
map.put("Employee_First_Name_4028818953c566f00153c56966830004",params.get("Employee_First_Name_4028818953c566f00153c56966830004"));
map.put("Employee_Last_Name_4028818953c566f00153c56966850005",params.get("Employee_Last_Name_4028818953c566f00153c56966850005"));
map.put("Employee_SSNumber_4028818953c566f00153c56966870006",params.get("Employee_SSNumber_4028818953c566f00153c56966870006"));
map.put("Employee_phone_4028818953c566f00153c56966890007",params.get("Employee_phone_4028818953c566f00153c56966890007"));
map.put("generated",params.get("generated"));
map.put("userId",userId);
List list = this.sql.executeInsert(map, insert);
return list.get(0).get(0);
}


@Override
Map get(long id,String userId)  throws SQLException {
GroovyRowResult result =  this.sql.firstRow("select report_Employee_Information_1458961598483.userId,report_Employee_Information_1458961598483.generated,entity_Company_14593059084320.Name as Company_Name_4028818953c566f00153c56966a30008 ,entity_Employee_14589313835631.First_Name as Employee_First_Name_4028818953c566f00153c56966830004 ,entity_Employee_14589313835632.Last_Name as Employee_Last_Name_4028818953c566f00153c56966850005 ,entity_Employee_14589313835633.SSNumber as Employee_SSNumber_4028818953c566f00153c56966870006 ,entity_Employee_14589313835634.phone as Employee_phone_4028818953c566f00153c56966890007  from report_Employee_Information_1458961598483   join entity_Company_1459305908432 entity_Company_14593059084320  join entity_Employee_1458931383563 entity_Employee_14589313835631  join entity_Employee_1458931383563 entity_Employee_14589313835632  join entity_Employee_1458931383563 entity_Employee_14589313835633  join entity_Employee_1458931383563 entity_Employee_14589313835634   on    entity_Company_14593059084320.id=report_Employee_Information_1458961598483.Company_Name_4028818953c566f00153c56966a30008  and   entity_Employee_14589313835631.id=report_Employee_Information_1458961598483.Employee_First_Name_4028818953c566f00153c56966830004  and   entity_Employee_14589313835632.id=report_Employee_Information_1458961598483.Employee_Last_Name_4028818953c566f00153c56966850005  and   entity_Employee_14589313835633.id=report_Employee_Information_1458961598483.Employee_SSNumber_4028818953c566f00153c56966870006  and   entity_Employee_14589313835634.id=report_Employee_Information_1458961598483.Employee_phone_4028818953c566f00153c56966890007   where report_Employee_Information_1458961598483.deleted=false and report_Employee_Information_1458961598483.id='"+id + "' and report_Employee_Information_1458961598483.userId='"+userId+"'");
return result;
}

@Override
boolean update(long id, Map params,String userId)  throws SQLException  {
String updateSql = "update report_Employee_Information_1458961598483 set  Company_Name_4028818953c566f00153c56966a30008=:Company_Name_4028818953c566f00153c56966a30008,Employee_First_Name_4028818953c566f00153c56966830004=:Employee_First_Name_4028818953c566f00153c56966830004,Employee_Last_Name_4028818953c566f00153c56966850005=:Employee_Last_Name_4028818953c566f00153c56966850005,Employee_SSNumber_4028818953c566f00153c56966870006=:Employee_SSNumber_4028818953c566f00153c56966870006,Employee_phone_4028818953c566f00153c56966890007=:Employee_phone_4028818953c566f00153c56966890007,generated=:generated  path=null where id=:id and userId=:userId";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
map.put("Company_Name_4028818953c566f00153c56966a30008",params.get("Company_Name_4028818953c566f00153c56966a30008"));
map.put("Employee_First_Name_4028818953c566f00153c56966830004",params.get("Employee_First_Name_4028818953c566f00153c56966830004"));
map.put("Employee_Last_Name_4028818953c566f00153c56966850005",params.get("Employee_Last_Name_4028818953c566f00153c56966850005"));
map.put("Employee_SSNumber_4028818953c566f00153c56966870006",params.get("Employee_SSNumber_4028818953c566f00153c56966870006"));
map.put("Employee_phone_4028818953c566f00153c56966890007",params.get("Employee_phone_4028818953c566f00153c56966890007"));
map.put("generated",params.get("generated"));
return this.sql.executeUpdate(map,updateSql)
}

@Override
boolean delete(long id,String userId)  throws SQLException  {
String deleteSql = "update report_Employee_Information_1458961598483 set deleted=true where userId=:userId and id =:id";
Map map = new HashMap();
map.put("id",id);
map.put("userId",userId);
return this.sql.executeUpdate(map,deleteSql);
}
@Override
List list(String userId)  throws SQLException {
//String listSql = "select * from report_Employee_Information_1458961598483 where deleted=false and  userId='" + userId + "'";
String listSql = "select report_Employee_Information_1458961598483.id as id,report_Employee_Information_1458961598483.userId,report_Employee_Information_1458961598483.generated,entity_Company_14593059084320.Name as Company_Name_4028818953c566f00153c56966a30008 ,entity_Employee_14589313835631.First_Name as Employee_First_Name_4028818953c566f00153c56966830004 ,entity_Employee_14589313835632.Last_Name as Employee_Last_Name_4028818953c566f00153c56966850005 ,entity_Employee_14589313835633.SSNumber as Employee_SSNumber_4028818953c566f00153c56966870006 ,entity_Employee_14589313835634.phone as Employee_phone_4028818953c566f00153c56966890007  from report_Employee_Information_1458961598483   join entity_Company_1459305908432 entity_Company_14593059084320  join entity_Employee_1458931383563 entity_Employee_14589313835631  join entity_Employee_1458931383563 entity_Employee_14589313835632  join entity_Employee_1458931383563 entity_Employee_14589313835633  join entity_Employee_1458931383563 entity_Employee_14589313835634   on    entity_Company_14593059084320.id=report_Employee_Information_1458961598483.Company_Name_4028818953c566f00153c56966a30008  and   entity_Employee_14589313835631.id=report_Employee_Information_1458961598483.Employee_First_Name_4028818953c566f00153c56966830004  and   entity_Employee_14589313835632.id=report_Employee_Information_1458961598483.Employee_Last_Name_4028818953c566f00153c56966850005  and   entity_Employee_14589313835633.id=report_Employee_Information_1458961598483.Employee_SSNumber_4028818953c566f00153c56966870006  and   entity_Employee_14589313835634.id=report_Employee_Information_1458961598483.Employee_phone_4028818953c566f00153c56966890007   where report_Employee_Information_1458961598483.deleted=false and report_Employee_Information_1458961598483.userId='"+userId+"'";

return sql.rows(listSql);
}

 @Override
    Map list(String userId, int page, int row) throws SQLException {
        String listSql = "select report_Employee_Information_1458961598483.id as id, report_Employee_Information_1458961598483.userId,report_Employee_Information_1458961598483.generated,entity_Company_14593059084320.Name as Company_Name_4028818953c566f00153c56966a30008 ,entity_Employee_14589313835631.First_Name as Employee_First_Name_4028818953c566f00153c56966830004 ,entity_Employee_14589313835632.Last_Name as Employee_Last_Name_4028818953c566f00153c56966850005 ,entity_Employee_14589313835633.SSNumber as Employee_SSNumber_4028818953c566f00153c56966870006 ,entity_Employee_14589313835634.phone as Employee_phone_4028818953c566f00153c56966890007  from report_Employee_Information_1458961598483   join entity_Company_1459305908432 entity_Company_14593059084320  join entity_Employee_1458931383563 entity_Employee_14589313835631  join entity_Employee_1458931383563 entity_Employee_14589313835632  join entity_Employee_1458931383563 entity_Employee_14589313835633  join entity_Employee_1458931383563 entity_Employee_14589313835634   on    entity_Company_14593059084320.id=report_Employee_Information_1458961598483.Company_Name_4028818953c566f00153c56966a30008  and   entity_Employee_14589313835631.id=report_Employee_Information_1458961598483.Employee_First_Name_4028818953c566f00153c56966830004  and   entity_Employee_14589313835632.id=report_Employee_Information_1458961598483.Employee_Last_Name_4028818953c566f00153c56966850005  and   entity_Employee_14589313835633.id=report_Employee_Information_1458961598483.Employee_SSNumber_4028818953c566f00153c56966870006  and   entity_Employee_14589313835634.id=report_Employee_Information_1458961598483.Employee_phone_4028818953c566f00153c56966890007   where report_Employee_Information_1458961598483.deleted=false and report_Employee_Information_1458961598483.userId='"+userId+"'";
       	String countSQL = "select count(id) from report_Employee_Information_1458961598483 where deleted=false and  userId='" + userId + "'";
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

   @Override
    Map getOptions(String user_id) {
        Map map = new HashMap();
        String Company_Name_4028818953c566f00153c56966a30008 = "select id,Name from entity_Company_1459305908432 where deleted=false and  userId='"+user_id+"'";
        List Company_Name_4028818953c566f00153c56966a30008_options = sql.rows(Company_Name_4028818953c566f00153c56966a30008);
        map.put('Company_Name_4028818953c566f00153c56966a30008',Company_Name_4028818953c566f00153c56966a30008_options);
        String Employee_First_Name_4028818953c566f00153c56966830004 = "select id,First_Name from entity_Employee_1458931383563 where deleted=false and  userId='"+user_id+"'";
        List Employee_First_Name_4028818953c566f00153c56966830004_options = sql.rows(Employee_First_Name_4028818953c566f00153c56966830004);
        map.put('Employee_First_Name_4028818953c566f00153c56966830004',Employee_First_Name_4028818953c566f00153c56966830004_options);
        String Employee_Last_Name_4028818953c566f00153c56966850005 = "select id,Last_Name from entity_Employee_1458931383563 where deleted=false and  userId='"+user_id+"'";
        List Employee_Last_Name_4028818953c566f00153c56966850005_options = sql.rows(Employee_Last_Name_4028818953c566f00153c56966850005);
        map.put('Employee_Last_Name_4028818953c566f00153c56966850005',Employee_Last_Name_4028818953c566f00153c56966850005_options);
        String Employee_SSNumber_4028818953c566f00153c56966870006 = "select id,SSNumber from entity_Employee_1458931383563 where deleted=false and  userId='"+user_id+"'";
        List Employee_SSNumber_4028818953c566f00153c56966870006_options = sql.rows(Employee_SSNumber_4028818953c566f00153c56966870006);
        map.put('Employee_SSNumber_4028818953c566f00153c56966870006',Employee_SSNumber_4028818953c566f00153c56966870006_options);
        String Employee_phone_4028818953c566f00153c56966890007 = "select id,phone from entity_Employee_1458931383563 where deleted=false and  userId='"+user_id+"'";
        List Employee_phone_4028818953c566f00153c56966890007_options = sql.rows(Employee_phone_4028818953c566f00153c56966890007);
        map.put('Employee_phone_4028818953c566f00153c56966890007',Employee_phone_4028818953c566f00153c56966890007_options);
        
        return map;
    }

        @Override
    String getReportURL(long id, String user_id) {
        String q = "select id, path from report_Employee_Information_1458961598483 where deleted = false and userId='"+user_id+"' and id='"+id+"'";
        String  url = null;
        sql.query(q,{
            ResultSet rs ->
                if (rs.next()) url = rs.getString('path')
        })
        return url;
    }

    @Override
    boolean setReportURL(long id, String user_id,String path) {
        String q = "update report_Employee_Information_1458961598483 set path = '"+path+"' where userId='"+user_id+"' and id='"+id+"'";
        return sql.executeUpdate(q);
    }
    @Override
    boolean clearPath() {
        String clp = "update report_Employee_Information_1458961598483 set path=null";
        return sql.executeUpdate(clp);
    }

}

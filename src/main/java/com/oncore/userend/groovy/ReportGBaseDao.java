package com.oncore.userend.groovy;

import java.util.List;
import java.util.Map;

/**
 * Created by steve on 3/26/16.
 */
public interface ReportGBaseDao extends GBaseDao {

    Map getOptions(String user_id);
    String getReportURL(long id, String user_id);
    boolean setReportURL(long id, String user_id,String path);
    boolean clearPath();

}

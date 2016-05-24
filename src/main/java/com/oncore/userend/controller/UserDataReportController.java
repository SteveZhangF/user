package com.oncore.userend.controller;

/**
 * Created by steve on 3/24/16.
 */

import com.oncore.userend.groovy.GBaseDao;
import com.oncore.userend.groovy.ReportGBaseDao;
import com.oncore.userend.groovy.register.GroovyRegister;
import com.oncore.userend.helper.ReportGenerator;
import groovy.sql.GroovyResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 3/20/16.
 */

@RestController
public class UserDataReportController {

    @Autowired
    GroovyRegister groovyRegister;

    @RequestMapping(value = "report_list")
    public ModelAndView getEntityList(@RequestParam("report") String entityTableName) {
        ModelAndView modelAndView = new ModelAndView(entityTableName + "/list");
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(entityTableName);
        System.out.println(gBaseDao.getClass().getName());
        if (gBaseDao instanceof ReportGBaseDao) {
            ReportGBaseDao reportGBaseDao = (ReportGBaseDao) gBaseDao;
            Map<String, List<GroovyResultSet>> map = (Map<String, List<GroovyResultSet>>) reportGBaseDao.getOptions("1");
            modelAndView.addAllObjects(map);
        }
        return modelAndView;
    }

    @RequestMapping(value = "reports/{tableName}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map listReport(@PathVariable("tableName") String reports, @RequestParam("page") int page, @RequestParam("rows") int rows) {
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(reports);

        try {
            Map map = gBaseDao.list("1", page, rows);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("count", 0);
        return map;
    }


    @RequestMapping(value = "reports/{tableName}/", method = RequestMethod.PUT)
    public ResponseEntity updateReport(@PathVariable("tableName") String tableName, HttpServletRequest request) {

        GBaseDao gBaseDao = groovyRegister.getGroovyDao(tableName);
        Map map1 = new HashMap();

        Map<String, String[]> map = request.getParameterMap();
        long id = Long.parseLong(map.get("id")[0]);
        for (String key : map.keySet()) {
            map1.put(key, map.get(key)[0]);
        }
        try {
            gBaseDao.update(id, map1, "1");
            return new ResponseEntity(gBaseDao.get(id, "1"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "reports/{tableName}/", method = RequestMethod.POST)
    public ResponseEntity insertReport(@PathVariable("tableName") String tableName, HttpServletRequest request) {

        GBaseDao gBaseDao = groovyRegister.getGroovyDao(tableName);
        Map map1 = new HashMap();

        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            map1.put(key, map.get(key)[0]);
        }
        try {
            long id = gBaseDao.insert(map1, "1");
            return new ResponseEntity(gBaseDao.get(id, "1"), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "reports/{tableName}/delete/{entityId}", method = RequestMethod.GET)
    public ResponseEntity deleteReport(@PathVariable("tableName") String tableName, @PathVariable("entityId") long id, HttpServletRequest request) {
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(tableName);
        try {
            if (gBaseDao.delete(id, "1")) {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Autowired
    ReportGenerator reportGenerator;

    @RequestMapping(value = "reports/{tableName}/url/{entityId}", method = RequestMethod.GET)
    public ResponseEntity getReportURL(@PathVariable("tableName") String tableName, @PathVariable("entityId") long id, HttpServletRequest request) {
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(tableName);
        String url;
        try {

            ReportGBaseDao reportGBaseDao = (ReportGBaseDao) gBaseDao;
            String path = reportGBaseDao.getReportURL(id, "1");
            if (path != null) {
                url = reportGenerator.getReport(path);
            } else {
                Map map = reportGBaseDao.get(id, "1");
                String[] arr =  reportGenerator.getReport(tableName, map);
                path = arr[0];
                url = arr[1];
                reportGBaseDao.setReportURL(id,"1",path);
            }
            return new ResponseEntity(url, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

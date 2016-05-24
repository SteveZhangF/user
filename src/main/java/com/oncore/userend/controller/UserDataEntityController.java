package com.oncore.userend.controller;

import com.oncore.userend.groovy.GBaseDao;
import com.oncore.userend.groovy.register.GroovyRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve on 3/20/16.
 */
@RestController
public class UserDataEntityController {

    @Autowired
    GroovyRegister groovyRegister;

    @RequestMapping(value = "entity_list")
       public ModelAndView getEntityList(@RequestParam("entity") String entityTableName) {
        return new ModelAndView(entityTableName + "/list");
    }


    @RequestMapping(value = "entities/{tableName}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map listEntity(@PathVariable("tableName") String entityTableName, @RequestParam("page") int page, @RequestParam("rows") int rows) {
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(entityTableName);

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


    @RequestMapping(value = "entities/{tableName}/", method = RequestMethod.PUT)
    public ResponseEntity updateEntity(@PathVariable("tableName") String tableName, HttpServletRequest request) {

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


    @RequestMapping(value = "entities/{tableName}/", method = RequestMethod.POST)
    public ResponseEntity insertEntity(@PathVariable("tableName") String tableName, HttpServletRequest request) {

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

    @RequestMapping(value = "entities/{tableName}/delete/{entityId}", method = RequestMethod.GET)
    public ResponseEntity deleteEntity(@PathVariable("tableName") String tableName, @PathVariable("entityId") long id, HttpServletRequest request) {
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


}

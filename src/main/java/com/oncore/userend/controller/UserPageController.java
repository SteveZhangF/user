package com.oncore.userend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.helper.HttpModuleInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by steve on 3/29/16.
 */
@Controller
public class UserPageController {
    @Autowired
    CommonConfigure commonConfigure;
    @Autowired
    HttpModuleInfoGenerator httpModuleInfoGenerator;

    @RequestMapping(value = "menu/left")
    public ModelAndView getLeftMenu() {
        JsonNode root = httpModuleInfoGenerator.getModule("");
        ModelAndView modelAndView = new ModelAndView("layout/left");
        modelAndView.addObject("root",root);
        return modelAndView;
    }

    @RequestMapping(value = "menu/header")
    public ModelAndView getHeader(){
        return new ModelAndView("layout/header");
    }


    @RequestMapping(value = "menu/footer")
    public ModelAndView getFooter(){
        return new ModelAndView("layout/footer");
    }





}

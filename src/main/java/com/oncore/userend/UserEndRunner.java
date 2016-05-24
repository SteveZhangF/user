package com.oncore.userend;

import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.groovy.register.GroovyRegister;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;

/**
 * Created by steve on 3/20/16.
 */
public class UserEndRunner implements ApplicationListener<ContextRefreshedEvent> {
    private GroovyRegister groovyRegister;
    private CommonConfigure commonConfigure;

    public CommonConfigure getCommonConfigure() {
        return commonConfigure;
    }

    public void setCommonConfigure(CommonConfigure commonConfigure) {
        this.commonConfigure = commonConfigure;
    }

    public GroovyRegister getGroovyRegister() {
        return groovyRegister;
    }

    public void setGroovyRegister(GroovyRegister groovyRegister) {
        this.groovyRegister = groovyRegister;
    }

    Log log = LogFactory.getLog(UserEndRunner.class);

    public void init() {
        log.info("loading groovy bean...");
        String baseDir = commonConfigure.getWebBase() + "/WEB-INF/" + "dao/";
        File daoDir = new File(baseDir);
        if (daoDir.exists() && daoDir.isDirectory()) {
            File[] folders = daoDir.listFiles();
            for (File folder : folders) {
                String beanName = folder.getName();
                File[] files = folder.listFiles();
                if (files.length > 0) {
                    File daoFile = files[0];
                    groovyRegister.registerGroovyDao(beanName, daoFile);
                }
            }
        }
    }



    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            init();
        }
    }



}

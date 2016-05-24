package com.oncore.userend.groovy.register;

import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.groovy.GBaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by steve on 3/20/16.
 * <p>
 * Created by steve on 1/16/16.
 */

/**
 * Created by steve on 1/16/16.
 */

@Component
public class GroovyRegister implements ApplicationContextAware {

    Log log = LogFactory.getLog(GroovyRegister.class);

    private ApplicationContext applicationContext;

    private static final String REFRESH_CHECK_DELAY_STR = "org.springframework.scripting.support.ScriptFactoryPostProcessor.refreshCheckDelay";
    private static final String LANGUAGE = "org.springframework.scripting.support.ScriptFactoryPostProcessor.language";
    private static final String BEAN_CLASS_NAME = "org.springframework.scripting.groovy.GroovyScriptFactory";

    private DefaultListableBeanFactory beanFactory = null;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.applicationContext = context;
        this.beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }


    /**
     * add or update one groovy dao bean to context
     */
    public void registerGroovyDao(String beanName, File file) {
        log.info("registering groovy bean " + beanName + " at " + file.getAbsolutePath());
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClassName(BEAN_CLASS_NAME);
        // 刷新时间
        bd.setAttribute(REFRESH_CHECK_DELAY_STR, 500);
        // 语言脚本
        bd.setAttribute(LANGUAGE, "groovy");
        // 文件目录
        /**
         * file: load from file
         * http: load from net
         * classpath: load from classpath
         * classpath*:load from classpath jar lib .etc.
         * */
        bd.getConstructorArgumentValues().addIndexedArgumentValue(0, "file:" + file.getAbsolutePath());
        // 注册到spring容器
        beanFactory.registerBeanDefinition(beanName, bd);

        log.info("registered groovy bean " + beanName + " at " + file.getAbsolutePath());
    }

    @Autowired
    CommonConfigure commonConfigure;

    public GBaseDao getGroovyDao(String beanName) {
        Object bean = applicationContext.getBean(beanName);
        if (bean == null) {
            return null;
        }
        return (GBaseDao) bean;
    }
}


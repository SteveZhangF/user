package com.oncore.userend.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by steve on 3/21/16.
 */
@Configuration
public class CommonConfigure {

    @Value("${module_url}")
    private String module_url;

    private String webBase;
    @Value("${aws_access_key}")
    private String aws_access_key;
    @Value("${aws_secret_key}")
    private String aws_secret_key;
    @Value("${aws_report_template_bucket_name}")
    private String aws_report_template_bucket_name;
    @Value("${template_path}")
    private String template_path;


    public String getModule_url() {
        return module_url;
    }

    public void setModule_url(String module_url) {
        this.module_url = module_url;
    }

    public String getTemplate_path() {
        return template_path;
    }

    public void setTemplate_path(String template_path) {
        this.template_path = template_path;
    }

    public String getWebBase() {
        if(webBase == null){
            webBase = System.getProperty("oncore.user.web.root");
        }
        return webBase;
    }

    public void setWebBase(String webBase) {
        this.webBase = webBase;
    }

    public String getAws_access_key() {
        return aws_access_key;
    }

    public void setAws_access_key(String aws_access_key) {
        this.aws_access_key = aws_access_key;
    }

    public String getAws_secret_key() {
        return aws_secret_key;
    }

    public void setAws_secret_key(String aws_secret_key) {
        this.aws_secret_key = aws_secret_key;
    }

    public String getAws_report_template_bucket_name() {
        return aws_report_template_bucket_name;
    }

    public void setAws_report_template_bucket_name(String aws_report_template_bucket_name) {
        this.aws_report_template_bucket_name = aws_report_template_bucket_name;
    }
}

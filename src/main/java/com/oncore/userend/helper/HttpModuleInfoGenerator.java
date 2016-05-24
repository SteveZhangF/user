package com.oncore.userend.helper;

import com.amazonaws.util.StringInputStream;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oncore.userend.configure.CommonConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by steve on 3/25/16.
 */
@Component
public class HttpModuleInfoGenerator implements ModuleInfoGenerator {
    @Autowired
    HttpConnection httpConnection;
    @Autowired
    CommonConfigure commonConfigure;
    public JsonNode getModule(String module_id) {
        try {
            String moduelString = httpConnection.getData(commonConfigure.getModule_url().replace("{id}",module_id));
            JsonNode root = new ObjectMapper().readTree(moduelString);
            System.out.println(root.asText());
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

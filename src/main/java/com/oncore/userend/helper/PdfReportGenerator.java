package com.oncore.userend.helper;


import com.lowagie.text.DocumentException;
import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.storage.downloader.DownLoader;
import com.oncore.userend.storage.uploader.UpLoader;
import freemarker.template.Configuration;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

/**
 * Created by steve on 3/27/16.
 */
public class PdfReportGenerator extends ReportGenerator {


    public PdfReportGenerator(CommonConfigure commonConfigure, DownLoader downLoader, UpLoader upLoader) {
        super(commonConfigure, downLoader, upLoader);
    }


    @Override
    public String[] getReport(String templateName, Map element) throws IOException {
        String template = getTemplateFile(templateName);
        if(element!=null)
        for(Object key:element.keySet()){

            if(key instanceof  String && element.get(key)!=null){
                template = template.replace("${"+key+"}",element.get(key).toString());
            }
        }
        /**
         * //TODO
         * need to move the xml headers to an configure file or database,
         * cause we never know what headers we will use
         * */
        String xmlHeader = "<?xml version=\"1.1\" ?><!DOCTYPE naughtyxml [<!ENTITY nbsp \"&#0160;\"><!ENTITY copy \"&#0169;\"><!ENTITY rsquo   \"&#x20199;\"><!ENTITY ldquo   \"&#8220;\"><!ENTITY rdquo   \"&#8221;\"><!ENTITY ndash  \"&#x2013;\" > <!ENTITY mdash  \"&#x2014;\" ><!ENTITY lsquo   \"&#8216;\">]>	";
        File tmp = File.createTempFile("report_",".pdf");
        OutputStream os = new FileOutputStream(tmp);
        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocument(url);
        renderer.setDocumentFromString(xmlHeader + template);
//        renderer.getSharedContext().setBaseURL("file:/D:/Work/Demo2do/Yoda/branch/Yoda%20-%20All/conf/template/");

        renderer.layout();
        try {
            renderer.createPDF(os);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        os.close();
        upLoader.upload(tmp,tmp.getName());
        String[] arr = new String[2];
        arr[1] = downLoader.getDownloadUrl(tmp.getName());
        arr[0] = tmp.getName();
        return arr;
    }

    @Override
    public String getReport(String path) {
        return downLoader.getDownloadUrl(path);
    }
}

package com.oncore.userend.helper;

import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.storage.downloader.DownLoader;
import com.oncore.userend.storage.uploader.UpLoader;

import java.io.*;
import java.util.Map;

/**
 * Created by steve on 3/27/16.
 */
public abstract class ReportGenerator {

    DownLoader downLoader;
    UpLoader upLoader;
    CommonConfigure commonConfigure;
    String templatePath;

    public ReportGenerator(CommonConfigure commonConfigure, DownLoader downLoader, UpLoader upLoader) {
        this.downLoader = downLoader;
        this.upLoader = upLoader;
        this.commonConfigure = commonConfigure;
    }


    private void initTemplatePath() {
        if (this.templatePath == null) {
            this.templatePath = commonConfigure.getWebBase() + "/" + commonConfigure.getTemplate_path();
            File tempDir = new File(this.templatePath);
            if (!(tempDir.exists() && tempDir.isDirectory())) {
                tempDir.mkdirs();
            }
        }
    }

    public void clearTemplateFile(String templateName){
        initTemplatePath();
        System.out.println("cleaning... "+templateName);
        File temp = new File(this.templatePath + "/" + templateName);
        temp.delete();

    }

    protected String getTemplateFile(String templateName) throws IOException {
        initTemplatePath();
        File temp = new File(this.templatePath + "/" + templateName);
        if (!temp.exists()) {
            String content = downLoader.getReportContent(templateName);
            System.out.println(content);
            FileWriter fileWriter = new FileWriter(temp);
            fileWriter.write(content);
            fileWriter.close();
        }

        BufferedReader fileReader = new BufferedReader(new FileReader(temp));
        String buf = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((buf = fileReader.readLine()) != null) {
            stringBuffer.append(buf);
        }
        fileReader.close();
        return stringBuffer.toString();
    }

    public abstract String[] getReport(String templateName, Map element) throws IOException;
    public abstract String getReport(String path);

}

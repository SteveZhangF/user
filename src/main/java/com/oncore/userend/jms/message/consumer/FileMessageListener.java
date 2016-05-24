package com.oncore.userend.jms.message.consumer;

import com.oncore.userend.configure.CommonConfigure;
import com.oncore.userend.groovy.GBaseDao;
import com.oncore.userend.groovy.ReportGBaseDao;
import com.oncore.userend.groovy.register.GroovyRegister;
import com.oncore.userend.helper.PdfReportGenerator;
import com.oncore.userend.helper.ReportGenerator;
import org.apache.activemq.BlobMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by steve on 3/21/16.
 * <p>
 * <p>
 * Receive file from middleware
 */
public class FileMessageListener implements MessageListener {
    @Autowired
    CommonConfigure commonConfigure;
    Log log = LogFactory.getLog(FileMessageListener.class);

    public void onMessage(Message message) {
        if (message instanceof BlobMessage) {
            log.info("received blob message...");
            BlobMessage blobMessage = (BlobMessage) message;
            try {
                String name = blobMessage.getStringProperty("NAME");
                String path = blobMessage.getStringProperty("PATH");
                String type = blobMessage.getStringProperty("TYPE");

                InputStream inputStream = blobMessage.getInputStream();
                FileType type1 = FileType.valueOf(type);
                log.info("handling " + type1 + " ..." + name);
                switch (type1) {
                    case EntityDao:
                        handleDao(name, path, inputStream);
                        break;
                    case EntityHtml:
                        handleHtml(name, path, inputStream);
                        break;
                    case ReportDao:
                        handleDao(name, path, inputStream);
                        break;
                    case ReportHtml:
                        handleHtml(name, path, inputStream);
                        break;
                    default:
                        log.error("wrong message type");
                        break;
                }


            } catch (JMSException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }

    @Autowired
    private GroovyRegister groovyRegister;
    @Autowired
    private ReportGenerator reportGenerator;

    private void handleDao(String beanName, String path, InputStream inputStream) throws IOException {
        File file = writeFile(inputStream, "/dao/" + path, beanName);
        groovyRegister.registerGroovyDao(path, file);
        // remove the existing template and set path in the table to null
        GBaseDao gBaseDao = groovyRegister.getGroovyDao(path);
        if(gBaseDao instanceof ReportGBaseDao){
            ((ReportGBaseDao)gBaseDao).clearPath();
        }
        reportGenerator.clearTemplateFile(path);

    }

    private void handleHtml(String name, String path, InputStream inputStream) throws IOException {
        writeFile(inputStream, "/views/" + path, name);
    }

    private File writeFile(InputStream inputStream, String path, String name) throws IOException {

        File dir = new File(commonConfigure.getWebBase() + "/WEB-INF/" + path);
        dir.mkdirs();
        File file = new File(dir.getAbsolutePath() + "/" + name);
        if (file.exists()) {
            file.delete();
        }
        byte[] buff = new byte[256];
        FileOutputStream fileWriter = new FileOutputStream(file);
        int size;
        while ((size = inputStream.read(buff)) != -1) {
            fileWriter.write(buff, 0, size);
        }
        fileWriter.close();
        log.info("wrote file to " + file.getAbsolutePath());
        return file;
    }
}

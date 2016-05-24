package com.oncore.userend.storage.downloader;

import com.oncore.userend.configure.CommonConfigure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by steve on 3/6/16.
 */
public abstract class DownLoader {
    CommonConfigure commonConfigure;
    Log log = LogFactory.getLog(DownLoader.class);

    @Autowired
    public DownLoader(CommonConfigure commonConfigure) {
        this.commonConfigure = commonConfigure;
    }

    public abstract String getDownloadUrl(String key);

    public abstract String getReportContent(String targetName) throws IOException;

    public String downContent(String key) throws IOException {
        String url = getDownloadUrl(key);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            BufferedReader rd = null;
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } else {
            return null;
        }

    }


}

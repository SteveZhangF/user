package com.oncore.userend.helper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by steve on 3/30/16.
 */
@Component
public class HttpConnection {
    public enum HTTP_METHOD {
        get, post, put, delete
    }

    public String getData(String url) throws IOException {
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
                result.append("\n");
            }
            return result.toString();
        }
        return null;
    }
//
//    public String getData(String url, HTTP_METHOD http_method) throws IOException {
//
//    }
}

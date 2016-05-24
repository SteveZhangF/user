package com.oncore.userend.jms.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oncore.userend.groovy.register.GroovyRegister;

import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve on 3/20/16.
 */
public class GroovyDaoMessageListener implements MessageListener {

    private GroovyRegister groovyRegister;

    public GroovyRegister getGroovyRegister() {
        return groovyRegister;
    }

    public void setGroovyRegister(GroovyRegister groovyRegister) {
        this.groovyRegister = groovyRegister;
    }

    public void onMessage(Message message) {
        TextMessage mapMessage = (TextMessage) message;
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(mapMessage.getText());
            String name = node.get("name").asText();
            String path = node.get("path").asText();
            File file = new File(path);
            if (file.exists()) {
                groovyRegister.registerGroovyDao(name,file);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

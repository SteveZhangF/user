package com.oncore.userend.helper;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * Created by steve on 3/25/16.
 */
public interface ModuleInfoGenerator {
    JsonNode getModule(String module_id);
}

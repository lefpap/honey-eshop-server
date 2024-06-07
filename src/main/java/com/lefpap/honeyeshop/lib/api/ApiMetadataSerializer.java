package com.lefpap.honeyeshop.lib.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ApiMetadataSerializer extends JsonSerializer<ApiMetadata>{


    @Override
    public void serialize(ApiMetadata metadata, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ObjectNode root = metadata.getRoot();
        gen.writeTree(root);
    }
    
}

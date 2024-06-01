package com.lefpap.honeyeshop.lib;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;

@JsonSerialize(using = MetadataSerializer.class)
public class Metadata {
    
    @Getter
    private final ObjectNode root;

    private Metadata(ObjectNode root) {
        this.root = root;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        
        private final ObjectNode root = JsonNodeFactory.instance.objectNode();

        public Builder addMeta(String key, Object value) {
            root.set(key, JsonNodeFactory.instance.pojoNode(value));
            return this;
        }

        public Builder addMetaPagination(Page<?> page) {
            ObjectNode pagination = JsonNodeFactory.instance.objectNode()
                .put("page", page.getNumber())
                .put("size", page.getSize())
                .put("totalElements", page.getTotalElements())
                .put("totalPages", page.getTotalPages())
                .put("isFirst", page.isFirst())
                .put("isLast", page.isLast());

            root.set("_pagination", pagination);
            
            return this;
        }

        public Builder addMetaLink(String rel, String href) {
            
            ArrayNode links = root.has("_links") 
                ? root.withArray("_links") 
                : JsonNodeFactory.instance.arrayNode();

            ObjectNode link = JsonNodeFactory.instance.objectNode()
                .put("rel", rel)
                .put("href", href);

            links.add(link);
            root.set("_links", links);

            return this;
        }

        public Metadata build() {
            return new Metadata(root);
        }
    }

}

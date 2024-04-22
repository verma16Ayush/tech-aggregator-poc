package com.avr.techaggregator.models;

import java.util.List;
import java.util.Map;

public class SemanticSearchDocument {
    private String content;
    private String content_type;
    private Object embedding; // This can be of any type, depending on the actual response
    private String id;
    private List<String> id_hash_keys;
    private Map<String, String> meta;
    private double score;
    public void setContent(String content) {
        this.content = content;
    }
    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
    public void setEmbedding(Object embedding) {
        this.embedding = embedding;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId_hash_keys(List<String> id_hash_keys) {
        this.id_hash_keys = id_hash_keys;
    }
    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public String getContent() {
        return content;
    }
    public String getContent_type() {
        return content_type;
    }
    public Object getEmbedding() {
        return embedding;
    }
    public String getId() {
        return id;
    }
    public List<String> getId_hash_keys() {
        return id_hash_keys;
    }
    public Map<String, String> getMeta() {
        return meta;
    }
    public double getScore() {
        return score;
    }

}

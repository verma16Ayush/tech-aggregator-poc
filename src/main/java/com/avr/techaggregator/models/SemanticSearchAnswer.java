package com.avr.techaggregator.models;

import java.util.List;
import java.util.Map;

public class SemanticSearchAnswer {
    private String answer;
    private String context;
    private List<String> document_ids;
    private Map<String, String> meta;
    private List<SemanticSearchOffset> offsets_in_context;
    private List<SemanticSearchOffset> offsets_in_document;
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public List<String> getDocument_ids() {
        return document_ids;
    }
    public void setDocument_ids(List<String> document_ids) {
        this.document_ids = document_ids;
    }
    public Map<String, String> getMeta() {
        return meta;
    }
    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }
    public List<SemanticSearchOffset> getOffsets_in_context() {
        return offsets_in_context;
    }
    public void setOffsets_in_context(List<SemanticSearchOffset> offsets_in_context) {
        this.offsets_in_context = offsets_in_context;
    }
    public List<SemanticSearchOffset> getOffsets_in_document() {
        return offsets_in_document;
    }
    public void setOffsets_in_document(List<SemanticSearchOffset> offsets_in_document) {
        this.offsets_in_document = offsets_in_document;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    private double score;
    private String type;

}

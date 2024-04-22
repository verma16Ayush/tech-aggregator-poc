package com.avr.techaggregator.models;

import java.util.List;

public class SemanticSearchQueryResponseType {
    private List<SemanticSearchAnswer> answers;
    private List<SemanticSearchDocument> documents;
    private double no_ans_gap;
    private String node_id;
    private Object params; // This can be of any type, depending on the actual response
    private String query;
    private String root_node;

    // Getters and setters
    public List<SemanticSearchAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SemanticSearchAnswer> answers) {
        this.answers = answers;
    }

    public List<SemanticSearchDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<SemanticSearchDocument> documents) {
        this.documents = documents;
    }

    public double getNo_ans_gap() {
        return no_ans_gap;
    }

    public void setNo_ans_gap(double no_ans_gap) {
        this.no_ans_gap = no_ans_gap;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRoot_node() {
        return root_node;
    }

    public void setRoot_node(String root_node) {
        this.root_node = root_node;
    }
}

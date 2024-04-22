package com.avr.techaggregator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avr.techaggregator.models.RunQueryRequest;
import com.avr.techaggregator.models.SemanticSearchQueryResponseType;
import com.avr.techaggregator.services.Fetcher;

@RestController
public class Controller {

    @Autowired
    private Fetcher fetcher;
    
    @RequestMapping("/")
    public String HomeMapping() {    
        return "Hello World: Tech-Aggregator";
    }

    @GetMapping("/api/runQuery")
    @ResponseBody
    public SemanticSearchQueryResponseType runQuery(@RequestBody RunQueryRequest query) {
        return fetcher.makeSemanticSearchQuery(query);
    }
}

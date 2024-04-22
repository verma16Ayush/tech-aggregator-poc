package com.avr.techaggregator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.avr.techaggregator.models.RunQueryRequest;
import com.avr.techaggregator.models.SemanticSearchQueryResponseType;
import com.avr.techaggregator.constants.Constants;

@Service
public class Fetcher {
    public SemanticSearchQueryResponseType makeSemanticSearchQuery(RunQueryRequest query) {
        String endpointUrl = Constants.SEMANTIC_SEARCH_QUERY;

        RestTemplate restTemplate = new RestTemplate();

        SemanticSearchQueryResponseType semanticSearchQueryResponseType = restTemplate.getForObject(endpointUrl + query.getQuery(), SemanticSearchQueryResponseType.class);

        return semanticSearchQueryResponseType;
    }
}

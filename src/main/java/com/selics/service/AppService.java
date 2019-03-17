package com.selics.service;

import com.selics.AppConstants;
import com.selics.model.Score;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the business service logic required by the controller
 *
 * @author Karthikeyan Varadarajan
 */

@Service
public class AppService {

    /**
     * Response json from Amazon search api as string
     */
    private static String responseJSON;

    /**
     * Setter for storing response JSON
     *
     * @param responseJSON Response json from Amazon search api
     */
    private static void setResponseJSON(String responseJSON) {
        AppService.responseJSON = responseJSON;
    }

    /**
     * Algorithm to calculate the usage score from given input keyword
     *
     * @param keyword Input keyword
     * @return Json object of Score containing keyword and the score
     */
    public Score getScore(String keyword) {

        getHttpAsync(keyword.replace(AppConstants.TARGET1, AppConstants.REPLACEMENT1));

        // Parsing input json string manually since the json did not follow a proper structure to cast
        // it into a model class
        Optional<String[]> optionalCandidateSet = parseAndGetCandidateSet();

        int score = 0;
        if (optionalCandidateSet.isPresent()){
            List<String> candidateList = Arrays.asList(optionalCandidateSet.get());

            //Direct score if the search keyword exactly matches one of 10 suggestion
            score = !candidateList.contains(keyword) ? 0 : 100 - ((candidateList.indexOf(keyword)) * 10);

            //Partial score if the search keyword is partially listed in candidate list
            if (score == 0) {
                for (String val : candidateList) {
                    if (val.contains(keyword)) {
                        score += 7;
                    }
                }
            }
        }
        return new Score(keyword, score);
    }

    /**
     * Logic to parse and retrieve the Amazon api list of suggestions
     *
     * @return optional array of strings containing amazon api search results
     */
    private Optional<String[]> parseAndGetCandidateSet() {
        String[] candidateSet = responseJSON.split(AppConstants.REGEX1)[2].split(AppConstants.REGEX2);
        for (int i = 0; i < candidateSet.length; i++) {
            String s = candidateSet[i];
            candidateSet[i] = s.replace(AppConstants.TARGET2, AppConstants.REPLACEMENT2);
        }
        return Optional.of(candidateSet);
    }

    /**
     * Asynchronous HTTP Get request to Amazon api using Java 11 HTTP client.
     *
     * @param keyword Search keyword
     */
    private void getHttpAsync(String keyword) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AppConstants.AMAZON_REST_API + keyword))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(AppService::setResponseJSON)
                .join();
    }
}
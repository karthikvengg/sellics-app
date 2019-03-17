package com.selics.controller;

import com.selics.model.Score;
import com.selics.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Class implements the REST resources required for input and output of Sellics score application
 *
 * @author Karthikeyan Varadarajan
 */

@SuppressWarnings("unused")
@RestController
public class AppController {

    /**
     * Service class instance
     */
    @Autowired
    private AppService appService;

    /**
     * This method returns the score of the given keyword in Amazon search results
     *
     * @param keyword Amazon search keyword
     * @return Response Entity object of type Score with a status code of 200 if the score is successfully calculated
     * for the given search keyword
     */
    @GetMapping("/estimate")
    public ResponseEntity<Score> getResponse(@RequestParam String keyword) {
        return new ResponseEntity<>(appService.getScore(keyword), HttpStatus.OK);
    }
}
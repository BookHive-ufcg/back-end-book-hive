package com.bookhive.ufcg.bookhive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookhive.ufcg.bookhive.service.RecommendationService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class RecommendationController {
    @Autowired
    private RecommendationService recommendService;

    @RequestMapping(value = "recommend/{genre}/{title}", method = RequestMethod.GET)
    public String generateContent(@PathVariable("genre") String genre, @PathVariable("title") String title) {
        return recommendService.generateContent(genre, title);
    }
}

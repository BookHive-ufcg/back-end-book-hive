package com.bookhive.ufcg.bookhive.controller;

import com.bookhive.ufcg.bookhive.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class RecommendationController {
    @Autowired
    private RecommendationService recommendService;

    @RequestMapping(value = "recommend/{genre}/{title}", method = RequestMethod.PUT)
    public String generateContent(@PathVariable("genre") String genre, @PathVariable("title") String title) {
        return recommendService.generateContent(genre, title);
    }
}

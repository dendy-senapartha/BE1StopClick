package com.Be1StopClick.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/web-service/*")
public class GreetingController {

    @RequestMapping(value = "/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String greeting() {
        return "greeting";
    }

}
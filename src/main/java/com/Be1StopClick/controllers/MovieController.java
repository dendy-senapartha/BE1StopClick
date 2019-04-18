package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.MovieDao;
import com.Be1StopClick.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
public class MovieController {


    @Autowired
    private MovieDao movieRepository;

    @PostMapping(value = "/movies/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getAllMovie(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> productList = movieRepository.findAll();
        if (!productList.isEmpty()) {
            map.put("result", productList);
            return map;
        }
        map.put("result", null);
        return map;
    }
}

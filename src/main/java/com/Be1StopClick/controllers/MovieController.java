package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.dto.MovieDTO;
import com.Be1StopClick.dto.response.CheckIfProductAlreadyBuyedResponse;
import com.Be1StopClick.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
public class MovieController {

    public static int MOVIE_CATEGORY = 3;

    @Autowired
    private ProductDao productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/movies/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<MovieDTO>> getAllMovie(@RequestBody Map<String, Object> body) {
        Map<String, List<MovieDTO>> map = new HashMap<>();
        List<Product> movieList = productRepository.findAllProductByCategoryId(MOVIE_CATEGORY);
        //map.put("result", movieList);
        map.put("result", movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/movies/find-by-title",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<MovieDTO>> findMovieByTitle(@RequestBody Map<String, Object> body) {
        Map<String, List<MovieDTO>> map = new HashMap<>();
        Optional<Object> title = Optional.ofNullable(body.get("title"));
        int catID = MOVIE_CATEGORY;
        List<Product> movieList;
        if (title.isPresent()) {
            movieList = productRepository.findProductByCategoryIdAndTitle(catID, title.get().toString());
        } else {
            movieList = productRepository.findProductByCategoryIdAndTitle(catID, "");
        }
        map.put("result", movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/movies/get-movies-of-user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<MovieDTO>> getMoviesOfUser(@RequestBody Map<String, Object> body) {
        Map<String, List<MovieDTO>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        int catID = MOVIE_CATEGORY;
        List<Product> movieList = new ArrayList<>();
        if (userid.isPresent()) {
            movieList = productRepository.getBuyedProductOfUserByCategory(catID, userid.get().toString());
        }
        map.put("result", movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/movies/find-movies-of-user-byid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<MovieDTO>> findMoviesOfUserByid(@RequestBody Map<String, Object> body) {
        Map<String, List<MovieDTO>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));
        int catID = MOVIE_CATEGORY;
        List<Product> movieList = new ArrayList<>();
        if (userid.isPresent() && productId.isPresent()) {
            movieList = productRepository.findBuyedProductOfUserByCategoryAndProdId(catID, userid.get().toString(), productId.get().toString());
        } else if (userid.isPresent()) {
            movieList = productRepository.findBuyedProductOfUserByCategoryAndProdId(catID, userid.get().toString(), "");
        }
        map.put("result", movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/movies/find-movies-of-user-product-name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<MovieDTO>> findMoviesOfUserByProductName(@RequestBody Map<String, Object> body) {
        Map<String, List<MovieDTO>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        Optional<Object> productName = Optional.ofNullable(body.get("productName"));
        int catID = MOVIE_CATEGORY;
        List<Product> movieList = new ArrayList<>();
        if (userid.isPresent() && productName.isPresent()) {
            movieList = productRepository.findBuyedProductOfUserByCategoryAndProductName(catID, userid.get().toString(), productName.get().toString());
        } else if (userid.isPresent()) {
            movieList = productRepository.findBuyedProductOfUserByCategoryAndProductName(catID, userid.get().toString(), "");
        }
        map.put("result", movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/movies/check-if-movie-already-buyed",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, CheckIfProductAlreadyBuyedResponse> checkIfMovieAlreadyBuyed(@RequestBody Map<String, Object> body) {
        Map<String, CheckIfProductAlreadyBuyedResponse> map = new HashMap<>();
        CheckIfProductAlreadyBuyedResponse response = new CheckIfProductAlreadyBuyedResponse();
        Optional<Object> userid = Optional.ofNullable(body.get("userId"));
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));
        boolean status = false;
        if (userid.isPresent() && productId.isPresent()) {
            Product product = productRepository.checkIfProductAlreadyOrdered(userid.get().toString(), productId.get().toString());
            if (product != null) {
                status = true;
            }
        }
        response.status = status;
        map.put("result", response);
        return map;
    }
}

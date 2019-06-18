package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.model.Album;
import com.Be1StopClick.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
public class ProductController {

    public static int MUSIC_CATEGORY = 6;
    public static int MOVIE_CATEGORY = 3;

    @Autowired
    private ProductDao productRepository;


    @PostMapping(value = "/movies/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getAllMovie(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> movieList = productRepository.findAllProductByCategoryId(MOVIE_CATEGORY);
        map.put("result", movieList);
        return map;
    }

    @PostMapping(value = "/movies/find-by-title",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> findMovieByTitle(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> title = Optional.ofNullable(body.get("title"));
        int catID = MOVIE_CATEGORY;
        List<Product> musicList;
        if (title.isPresent()) {
            musicList = productRepository.findProductByCategoryIdAndTitle(catID, title.get().toString());
        } else {
            musicList = productRepository.findProductByCategoryIdAndTitle(catID, "");
        }
        map.put("result", musicList);
        return map;
    }

    @PostMapping(value = "/movies/get-movies-of-user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getMoviesOfUser(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        int catID = MOVIE_CATEGORY;
        List<Product> musicList = new ArrayList<>();
        if (userid.isPresent()) {
            musicList = productRepository.getBuyedProductOfUserByCategory(catID, userid.get().toString());
        }
        map.put("result", musicList);
        return map;
    }

    @PostMapping(value = "/movies/find-movies-of-user-byid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> findMoviesOfUserByid(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));
        int catID = MOVIE_CATEGORY;
        List<Product> productList = new ArrayList<>();
        if (userid.isPresent() && productId.isPresent()) {
            productList = productRepository.findBuyedProductOfUserByCategoryAndProdId(catID, userid.get().toString(), productId.get().toString());
        } else if (userid.isPresent()) {
            productList = productRepository.findBuyedProductOfUserByCategoryAndProdId(catID, userid.get().toString(), "");
        }
        map.put("result", productList);
        return map;
    }

    @PostMapping(value = "/movies/find-movies-of-user-product-name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> findMoviesOfUserByProductName(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        Optional<Object> productName = Optional.ofNullable(body.get("productName"));
        int catID = MOVIE_CATEGORY;
        List<Product> productList = new ArrayList<>();
        if (userid.isPresent() && productName.isPresent()) {
            productList = productRepository.findBuyedProductOfUserByCategoryAndProductName(catID, userid.get().toString(), productName.get().toString());
        } else if (userid.isPresent()) {
            productList = productRepository.findBuyedProductOfUserByCategoryAndProductName(catID, userid.get().toString(), "");
        }
        map.put("result", productList);
        return map;
    }

    @PostMapping(value = "/musics/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getAllMusic(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> musicList = productRepository.findAllProductByCategoryId(MUSIC_CATEGORY);
        map.put("result", musicList);
        return map;

    }

    @PostMapping(value = "/musics/get-all-album",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Album>> getAllMusicAlbum(@RequestBody Map<String, Object> body) {
        Map<String, List<Album>> map = new HashMap<>();
        List<Album> albumList = productRepository.findAllAlbum();
        map.put("result", albumList);
        return map;
    }

    @PostMapping(value = "/musics/find-by-title",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> findTrackByTitle(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> title = Optional.ofNullable(body.get("title"));
        int catID = MUSIC_CATEGORY;
        List<Product> musicList;
        if (title.isPresent()) {
            musicList = productRepository.findProductByCategoryIdAndTitle(catID, title.get().toString());
        } else {
            musicList = productRepository.findProductByCategoryIdAndTitle(catID, "");
        }
        map.put("result", musicList);
        return map;
    }

    @PostMapping(value = "/musics/get-musics-of-user",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getMusicOfUser(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        int catID = MUSIC_CATEGORY;
        List<Product> musicList = new ArrayList<>();
        if (userid.isPresent()) {
            musicList = productRepository.getBuyedProductOfUserByCategory(catID, userid.get().toString());
        }
        map.put("result", musicList);
        return map;
    }
}

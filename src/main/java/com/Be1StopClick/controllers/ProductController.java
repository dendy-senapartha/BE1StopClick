package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.model.Album;
import com.Be1StopClick.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by dendy-prtha on 16/04/2019.
 * controller for product
 */

@RestController
public class ProductController {

    @Autowired
    private ProductDao productRepository;


    @PostMapping(value = "/movies/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getAllMovie(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> movieList = productRepository.findAllProductByCategoryId(3);
        if (!movieList.isEmpty()) {
            map.put("result", movieList);
            return map;
        }
        map.put("result", null);
        return map;
    }

    @PostMapping(value = "/musics/get-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Product>> getAllMusic(@RequestBody Map<String, Object> body) {
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> musicList = productRepository.findAllProductByCategoryId(6);
        if (!musicList.isEmpty()) {
            map.put("result", musicList);
            return map;
        }
        map.put("result", null);
        return map;
    }

    @PostMapping(value = "/musics/get-all-album",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Album>> getAllMusicAlbum(@RequestBody Map<String, Object> body) {
        Map<String, List<Album>> map = new HashMap<>();
        List<Album> albumList = productRepository.findAllAlbum();
        if (!albumList.isEmpty()) {
            map.put("result", albumList);
            return map;
        }
        map.put("result", null);
        return map;
    }
}

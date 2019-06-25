package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.AlbumDao;
import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.dto.AlbumDTO;
import com.Be1StopClick.model.Album;
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
public class MusicController {

    public static int MUSIC_CATEGORY = 6;

    @Autowired
    private ProductDao productRepository;

    @Autowired
    private AlbumDao albumRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public Map<String, List<AlbumDTO>> getAllMusicAlbum(@RequestBody Map<String, Object> body) {
        Map<String, List<AlbumDTO>> map = new HashMap<>();
        List<Album> albumList = albumRepository.findAll();
        map.put("result", albumList.stream()
                .map(album -> modelMapper.map(album, AlbumDTO.class))
                .collect(Collectors.toList()));
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
    public Map<String, List<AlbumDTO>> getMusicOfUser(@RequestBody Map<String, Object> body) {
        Map<String, List<AlbumDTO>> map = new HashMap<>();
        Optional<Object> userid = Optional.ofNullable(body.get("userid"));
        List<Album> albumList = new ArrayList<>();
        if (userid.isPresent()) {
            albumList = albumRepository.getBuyedAlbumOfUser(userid.get().toString());
        }
        map.put("result", albumList.stream()
                .map(album -> modelMapper.map(album, AlbumDTO.class))
                .collect(Collectors.toList()));
        return map;
    }
}

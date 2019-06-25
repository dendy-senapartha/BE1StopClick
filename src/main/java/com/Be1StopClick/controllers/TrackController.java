package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.dao.TrackDao;
import com.Be1StopClick.dto.SongDTO;
import com.Be1StopClick.dto.TrackDTO;
import com.Be1StopClick.model.Product;
import com.Be1StopClick.model.Track;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Controller
 */

@RestController
public class TrackController {

    @Autowired
    private TrackDao trackRepository;

    @Autowired
    private ProductDao productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/track/get-track-by-productId",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<TrackDTO>> getTracksByProductId(@RequestBody Map<String, Object> body) {
        Map<String, List<TrackDTO>> map = new HashMap<>();
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));

        List<Track> trackList = new ArrayList<>();
        if (productId.isPresent()) {
            trackList = trackRepository.findTrackByProductId(productId.get().toString());
        }
        map.put("result", trackList.stream()
                .map(album -> modelMapper.map(album, TrackDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/track/get-album-songs",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<SongDTO>> getAlbumSong(@RequestBody Map<String, Object> body) {
        Map<String, List<SongDTO>> map = new HashMap<>();
        Optional<Object> albumId = Optional.ofNullable(body.get("albumId"));

        List<Product> songList = new ArrayList<>();
        if (albumId.isPresent()) {
            songList = productRepository.getAlbumProducts(albumId.get().toString());
        }
        map.put("result", songList.stream()
                .map(song -> modelMapper.map(song, SongDTO.class))
                .collect(Collectors.toList()));
        return map;
    }

    @PostMapping(value = "/track/find-userbuyed-songs-by-albumid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<SongDTO>> findUserBuyedSongsByAlbumId(@RequestBody Map<String, Object> body) {
        Map<String, List<SongDTO>> map = new HashMap<>();
        Optional<Object> userId= Optional.ofNullable(body.get("userId"));
        Optional<Object> albumId = Optional.ofNullable(body.get("albumId"));
        List<Product> songList = new ArrayList<>();
        if (albumId.isPresent()) {
            songList = productRepository.findBuyedProductByUserIdAndAlbumId(userId.get().toString(),
                    albumId.get().toString());
        }
        map.put("result", songList.stream()
                .map(song -> modelMapper.map(song, SongDTO.class))
                .collect(Collectors.toList()));
        return map;
    }
}

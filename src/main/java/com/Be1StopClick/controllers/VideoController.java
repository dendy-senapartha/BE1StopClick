package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.VideoDao;
import com.Be1StopClick.dto.VideoDTO;
import com.Be1StopClick.model.Video;
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
public class VideoController {

    @Autowired
    private VideoDao videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/video/get-video-by-productId",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<VideoDTO>> getVideoByProductId(@RequestBody Map<String, Object> body) {
        Map<String, List<VideoDTO>> map = new HashMap<>();
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));

        List<Video> videoList = new ArrayList<>();
        if (productId.isPresent()) {
            videoList = videoRepository.findVideoByProductId(productId.get().toString());
        }
        map.put("result", videoList.stream()
                .map(album -> modelMapper.map(album, VideoDTO.class))
                .collect(Collectors.toList()));
        return map;
    }
}

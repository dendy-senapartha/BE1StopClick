package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.TrackDao;
import com.Be1StopClick.model.Track;
import com.Be1StopClick.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Controller
 */

@RestController
public class TrackController {

    @Autowired
    private TrackDao trackRepository;

    @PostMapping(value = "/track/get-track-by-productId",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Track>> getTrackByProductId(@RequestBody Map<String, Object> body) {
        Map<String, List<Track>> map = new HashMap<>();
        Optional<Object> productId = Optional.ofNullable(body.get("productId"));

        List<Track> trackList = new ArrayList<>();
        if (productId.isPresent()) {
            trackList = trackRepository.findTrackByProductId(productId.get().toString());
        }
        map.put("result", trackList);
        return map;
    }
}

package com.Be1StopClick.dto;

import com.Be1StopClick.model.Album;
import com.Be1StopClick.model.Artist;
import com.Be1StopClick.model.Product;
import com.Be1StopClick.model.TrackType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * Track DTO
 */
public class TrackDTO {

    private int id;

    private TrackTypeDTO trackType;

    private String streamUrl;

    private int length;

    private List<ArtistDTO> artists = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public TrackTypeDTO getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackTypeDTO trackType) {
        this.trackType = trackType;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}

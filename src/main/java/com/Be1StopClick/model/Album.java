package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 21/05/2019.
 * entity Album
 */

@Entity
@Table(name = "albums")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "album_image_url")
    private String albumImageUrl;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("album")
    private List<Track> tracks = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTracks(Track track) {
        this.tracks.add(track);
        track.setAlbum(this);
    }

    public void removeTrack(Track track){
        track.setAlbum(null);
        this.tracks.remove(track);
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }
}

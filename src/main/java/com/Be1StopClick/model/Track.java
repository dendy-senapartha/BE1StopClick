package com.Be1StopClick.model;

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
 * Track Entity
 */

@Entity
@Table(name = "tracks")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "track_type_id")
    private TrackType trackType;

    @Column(name = "stream_url")
    private String streamUrl;

    @Column(name = "length")
    private int length;

    @ManyToOne
    @JsonIgnoreProperties("tracks")
    private Album album;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="track_artist",
            joinColumns={@JoinColumn(name="track_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")})
    @JsonIgnoreProperties("tracks")
    private List<Artist> artists= new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return (Product) Hibernate.unproxy(product);
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public TrackType getTrackType() {
        return (TrackType) Hibernate.unproxy(trackType);
    }

    public void setTrackType(TrackType trackType) {
        this.trackType = trackType;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}

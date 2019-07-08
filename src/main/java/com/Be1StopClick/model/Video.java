package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * video
 */

@Entity
@Table(name = "video")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_type_id")
    private VideoType videoType;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "studio")
    private String studio;

    @Column(name = "age_rating")
    private int ageRating;

    @Column(name = "avg_rating")
    private float avgRating;

    @Column(name = "overall_rank")
    private int overallRank;

    @Column(name = "stream_url")
    private String streamUrl;

    @Column(name = "duration")
    private int duration;

    @ManyToMany
    @JoinTable(
            name="actor_video",
            joinColumns={@JoinColumn(name="video_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="actor_id", referencedColumnName="id")})
    private List<Actor> actors= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="director_video",
            joinColumns={@JoinColumn(name="video_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="director_id", referencedColumnName="id")})
    private List<Director> directors= new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public int getOverallRank() {
        return overallRank;
    }

    public void setOverallRank(int overallRank) {
        this.overallRank = overallRank;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
}

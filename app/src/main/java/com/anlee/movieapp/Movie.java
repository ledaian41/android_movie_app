package com.anlee.movieapp;

//        vote_count: 1115,
//        id: 297762,
//        video: false,
//        vote_average: 7.1,
//        title: "Wonder Woman",
//        popularity: 115.676051,
//        poster_path: "/gfJGlDaHuWimErCr5Ql0I8x9QSy.jpg",
//        original_language: "en",
//        original_title: "Wonder Woman",
//        genre_ids: [
//        28,
//        12,
//        14,
//        878
//        ],
//        backdrop_path: "/hA5oCgvgCxj5MEWcLpjXXTwEANF.jpg",
//        adult: false,
//        overview: "An Amazon princess comes to the world of Man to become the greatest of the female superheroes.",
//        release_date: "2017-05-30"

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    private static final String POST_FIX = "https://image.tmdb.org/t/p/w500";

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("popularity")
    private double popularity;
    
    @SerializedName("release_date")
    private String releaseDate;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return POST_FIX + posterPath;
    }

    public String getBackdropPath() {return POST_FIX + backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}

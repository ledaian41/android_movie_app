package com.anlee.movieapp.api;

import com.anlee.movieapp.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lê Đại An on 15-Jun-17.
 */

public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}

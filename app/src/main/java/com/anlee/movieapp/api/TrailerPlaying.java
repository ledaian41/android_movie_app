package com.anlee.movieapp.api;

import com.anlee.movieapp.model.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lê Đại An on 17-Jun-17.
 */

public class TrailerPlaying {
    @SerializedName("youtube")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }
}

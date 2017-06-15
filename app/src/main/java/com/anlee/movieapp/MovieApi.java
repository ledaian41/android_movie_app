package com.anlee.movieapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> nowPlaying();
}

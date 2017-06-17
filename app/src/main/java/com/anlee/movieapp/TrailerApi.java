package com.anlee.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lê Đại An on 17-Jun-17.
 */

public interface TrailerApi {
    @GET("{id}/trailers")
    Call<TrailerPlaying> trailer(@Path("id") String id);
}

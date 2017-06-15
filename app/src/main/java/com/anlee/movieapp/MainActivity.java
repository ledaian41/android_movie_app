package com.anlee.movieapp;

import android.os.Handler;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.List;
import java.util.Set;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;

import static android.R.attr.data;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovies;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Lookup the list view and swipe container view
        lvMovies = (ListView) findViewById(R.id.lvMovie);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
//        Get data from Api response
        Retrofit retrofit = RetrofitUtil.create();
        final MovieApi movieApi = retrofit.create(MovieApi.class);
        getData(movieApi);
//        Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(movieApi);
                        swipeContainer.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private void getData(MovieApi movieApi) {
        movieApi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                List<Movie> movies = response.body().getMovies();
                lvMovies.setAdapter(new MovieAdapter(MainActivity.this, movies));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

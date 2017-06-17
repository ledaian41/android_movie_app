package com.anlee.movieapp;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvMovies)
    public RecyclerView rvMovies;
    @BindView(R.id.swipeContainer)
    public SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Get data from Api response
        Retrofit retrofit = RetrofitUtil.create();
        final MovieApi movieApi = retrofit.create(MovieApi.class);
        getData(movieApi);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
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
                rvMovies.setAdapter(new MovieAdapter(movies));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

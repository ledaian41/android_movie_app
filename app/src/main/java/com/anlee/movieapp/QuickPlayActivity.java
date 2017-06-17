package com.anlee.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuickPlayActivity extends AppCompatActivity {

    private static final String NAME_TRAILER = "official trailer";
    //    @BindView(R.id.player)
    private YouTubePlayerFragment youtubeFragment;
    private Trailer mTrailer;
    private static final String ERROR = "Don't have trailer yet !!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);
        youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.player);
        String id = String.valueOf(getIntent().getLongExtra("id", -1));
        Retrofit retrofit = RetrofitUtil.create();
        TrailerApi trailerApi = retrofit.create(TrailerApi.class);
        trailerApi.trailer(id).enqueue(new Callback<TrailerPlaying>() {
            @Override
            public void onResponse(Call<TrailerPlaying> call, Response<TrailerPlaying> response) {
                try {
                    Trailer tmp = response.body().getTrailers().get(0);
                } catch (Exception e) {
                    finish();
                    Toast.makeText(QuickPlayActivity.this, ERROR, Toast.LENGTH_SHORT).show();
                    return;
                }
                mTrailer = new Trailer(
                        response.body().getTrailers().get(0).getName(),
                        response.body().getTrailers().get(0).getSize(),
                        response.body().getTrailers().get(0).getSource(),
                        response.body().getTrailers().get(0).getType()
                );
                for (Trailer trailer : response.body().getTrailers()) {
                    if (trailer.getName().equalsIgnoreCase(NAME_TRAILER)) {
                        mTrailer = new Trailer(trailer.getName(), trailer.getSize(), trailer.getSource(), trailer.getType());
                        break;
                    }
                }
                youtubeFragment.initialize(BuildConfig.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(mTrailer.getSource());
                        youTubePlayer.setFullscreen(true);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
            }

            @Override
            public void onFailure(Call<TrailerPlaying> call, Throwable t) {

            }
        });

    }


}

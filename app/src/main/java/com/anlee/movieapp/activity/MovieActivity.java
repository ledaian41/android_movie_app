package com.anlee.movieapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.anlee.movieapp.model.Movie;
import com.anlee.movieapp.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {
    @BindView(R.id.ivCoverMovie)
    public ImageView ivCoverMovie;

    @BindView(R.id.tvTitleMovie)
    public TextView tvTitleMovie;

    @BindView(R.id.tvOverviewMovie)
    public TextView tvOverviewMovie;

    @BindView(R.id.rbRate)
    public RatingBar rbRate;

    @BindView(R.id.tvPopularity)
    public TextView tvPopularity;

    @BindView(R.id.tvReleaseDate)
    public TextView tvReleaseDate;

    private static final String RELEASE_DATE = "Release Date: ";
    private static final String POPULARITY = "Popularity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        final Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        bindView(movie);
        ivCoverMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieActivity.this, QuickPlayActivity.class);
                i.putExtra("id",movie.getId());
                startActivity(i);
            }
        });
    }

    private void bindView(Movie movie) {
        loadImage(ivCoverMovie, movie.getBackdropPath());
        tvTitleMovie.setText(movie.getTitle());
        tvOverviewMovie.setText(movie.getOverview());

        rbRate.setMax(10);
        rbRate.setStepSize(10.0f);
        rbRate.setNumStars(5);
        rbRate.setRating((float) (movie.getVoteAverage() / 2));
//        Set color for rating bar
        LayerDrawable stars = (LayerDrawable) rbRate.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        String popularity = String.valueOf(movie.getPopularity());
        popularity = POPULARITY + popularity;
        tvPopularity.setText(popularity);
        String releaseDate = RELEASE_DATE + movie.getReleaseDate();
        tvReleaseDate.setText(releaseDate);
    }

    private void loadImage(ImageView imageView, String path) {
        Glide.with(MovieActivity.this)
                .load(path)
                .placeholder(R.drawable.cover_sample)
                .into(imageView);
    }
}

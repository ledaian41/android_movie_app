package com.anlee.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anlee.movieapp.R;
import com.anlee.movieapp.activity.MovieActivity;
import com.anlee.movieapp.activity.QuickPlayActivity;
import com.anlee.movieapp.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lê Đại An on 15-Jun-17.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_MOVIE = 1;
    private static final int TYPE_POPULAR_MOVIE = 2;
    private static final int VOTE_AVERAGE_FIX = 7;
    private List<Movie> movies;
    private RecyclerView.ViewHolder viewHolder;
    private Context context;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        //        Get the data item type for this position
        if (viewType == TYPE_MOVIE) {
            viewHolder = getMovieView(parent);
        } else if (viewType == TYPE_POPULAR_MOVIE) {
            viewHolder = getPopularMovieView(parent);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == TYPE_MOVIE) {
            ViewHolderMovie viewHolderMovie = (ViewHolderMovie) holder;
            bindViewHolderMovie(viewHolderMovie, position);
        //        Set on item click Listener (for medium rate movie)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Movie movie = movies.get(position);
                    launchComposeView(movie);
                }
            });
        } else if (holder.getItemViewType() == TYPE_POPULAR_MOVIE) {
            ViewHolderPopularMovie viewHolderPopularMovie = (ViewHolderPopularMovie) holder;
            bindViewHolderPopularMovie(viewHolderPopularMovie, position);
        }
    }

    public void launchComposeView(Movie movie) {
        // 		  first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(context, MovieActivity.class);
        // 		  put "extras" into the bundle for access in the second activity
        i.putExtra("movie", movie);
        // 		  brings up the second activity
        context.startActivity(i);
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_MOVIE;
        if (movies.get(position).getVoteAverage() > VOTE_AVERAGE_FIX) {
            type = TYPE_POPULAR_MOVIE;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private RecyclerView.ViewHolder getPopularMovieView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item_movie, parent, false);
        viewHolder = new ViewHolderPopularMovie(view);
        return viewHolder;
    }


    private RecyclerView.ViewHolder getMovieView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        viewHolder = new ViewHolderMovie(view);
        return viewHolder;
    }

    private void bindViewHolderMovie(ViewHolderMovie viewHolderMovie, int position) {
        Movie movie = movies.get(position);
        viewHolderMovie.tvTitle.setText(movie.getTitle());
        viewHolderMovie.tvOverview.setText(movie.getOverview());
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            loadImage(viewHolderMovie.ivCover, movie.getBackdropPath());
        } else {
            loadImage(viewHolderMovie.ivCover, movie.getPosterPath());
        }
    }

    private void bindViewHolderPopularMovie(ViewHolderPopularMovie viewHolderPopularMovie, int position) {
        final Movie movie = movies.get(position);
        loadImage(viewHolderPopularMovie.ivCover, movie.getBackdropPath());
        viewHolderPopularMovie.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, QuickPlayActivity.class);
                i.putExtra("id",movie.getId());
                context.startActivity(i);
            }
        });
    }

    private void loadImage(ImageView imageView, String path) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.progress_animation)
                .into(imageView);
    }

    public class ViewHolderMovie extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCover)
        public ImageView ivCover;
        @BindView(R.id.tvTitle)
        public TextView tvTitle;
        @BindView(R.id.tvOverview)
        public TextView tvOverview;

        public ViewHolderMovie(View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);
        }
    }

    public class ViewHolderPopularMovie extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCover)
        public ImageView ivCover;

        public ViewHolderPopularMovie(View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);
        }
    }

}

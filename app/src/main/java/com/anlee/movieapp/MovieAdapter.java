package com.anlee.movieapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Lê Đại An on 15-Jun-17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private List<Movie> movies;

    public MovieAdapter(@NonNull Context context, List<Movie> movies) {
        super(context, -1);
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Fill Data
        bindViewHolder(position,viewHolder);
        return convertView;
    }

    private void bindViewHolder(int position, ViewHolder viewHolder){
        Movie movie = movies.get(position);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            loadImage(viewHolder.ivCover,movie.getBackdropPath());
        }else{
            loadImage(viewHolder.ivCover,movie.getPosterPath());
        }
    }

    private void loadImage(ImageView imageView, String path){
        Glide.with(getContext())
                .load(path)
                .into(imageView);
    }

    private class ViewHolder {
        public ViewHolder(View convertView) {
            ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
        }

        public final ImageView ivCover;
        public final TextView tvTitle;
        public final TextView tvOverview;
    }

}

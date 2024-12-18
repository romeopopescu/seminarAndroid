package com.example.seminarfromzero.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seminarfromzero.R;
import com.example.seminarfromzero.utils.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movieList;
    private Context context;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_movies_row, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.tvTitle.setText(movie.getMovieTitle());
        holder.tvProfit.setText("Profit " + movie.getProfit());
        holder.tvReleaseDate.setText("Date " + movie.getReleaseDate());
        holder.tvMovieGenre.setText(movie.getMovieGenre());
        holder.tvPlatform.setText(movie.getPlatform());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvReleaseDate, tvProfit, tvPlatform, tvMovieGenre;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvProfit = itemView.findViewById(R.id.tvProfit);
            tvPlatform = itemView.findViewById(R.id.tvPlatform);
            tvMovieGenre = itemView.findViewById(R.id.tvMovieGenre);
        }
    }

}

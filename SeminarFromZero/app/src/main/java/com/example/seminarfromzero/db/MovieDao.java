package com.example.seminarfromzero.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.seminarfromzero.utils.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("DELETE FROM movies")
    void deleteAll();

    @Query("DELETE FROM movies WHERE movieTitle = :title")
    void delete(String title);
}

package com.example.test2_songclass.db;

import android.graphics.Movie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test2_songclass.Song;

import java.util.List;

@Dao
public interface SongDao {
    @Insert
    void insert(Song song);

    @Query("SELECT * FROM songs")
    List<Song> getAllSongs();

    @Query("SELECT * FROM songs ORDER BY id ASC")
    List<Song> getSortedSongs();
}

package com.example.seminarfromzero.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.seminarfromzero.utils.Cinema;

import java.util.List;

@Dao
public interface CinemaDao {

    @Insert
    void insert(Cinema cinema);

    @Query("SELECT * FROM cinemas")
    List<Cinema> getAll();
}

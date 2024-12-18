package com.example.seminarfromzero.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.seminarfromzero.utils.Cinema;
import com.example.seminarfromzero.utils.DateConverter;
import com.example.seminarfromzero.utils.Movie;

import java.lang.reflect.GenericDeclaration;

@Database(entities = {Movie.class, Cinema.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class MovieDB extends RoomDatabase {
    private final static String DB_NAME = "movies_db";
    private static MovieDB INSTANCE;

    public static MovieDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MovieDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract MovieDao getMovieDao();
    public abstract CinemaDao getCinemaDao();
}

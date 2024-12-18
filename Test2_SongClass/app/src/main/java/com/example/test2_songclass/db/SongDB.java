package com.example.test2_songclass.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.test2_songclass.Song;
import com.example.test2_songclass.utils.DateConverter;

@Database(entities = {Song.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SongDB extends RoomDatabase {
    private static final String DB_NAME = "songs";

    private static SongDB INSTANCE;

    public static SongDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, SongDB.class, DB_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public abstract SongDao getSongDao();
}

package com.example.test2_songclass.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Constants {
    String KEY_ARRAY = "songs";
    String KEY_SONG_TITLE = "songTitle";
    String KEY_ARTIST = "artist";
    String KEY_VIEWS = "noOfViews";
    String KEY_RELEASE_DATE = "songReleaseDate";

    public static final String DATE_PATTERN = "dd-MM-yyyy";
    DateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

}

package com.example.seminarfromzero.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Constants {

    public static final String DATE_PATTERN = "dd-MM-yyyy";
    DateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

    String KEY_JSON_ARRAY = "movies";
    String KEY_MOVIE_TITLE = "title";
    String KEY_RELEASE_DATE = "releaseDate";
    String KEY_MOVIE_PROFIT = "profit";
    String KEY_MOVIE_GENRE = "movieGenre";
    String KEY_MOVIE_PLATFORM = "platform";
}

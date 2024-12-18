package com.example.seminarfromzero.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private List<Movie> movies = new ArrayList<>();
    private Movie movie;
    String text;

    public List<Movie> parse (InputStream inputStream) {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, "utf-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("Movie")) {
                            movie = new Movie();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("Movie")) {
                            movies.add(movie);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_MOVIE_TITLE)) {
                            movie.setMovieTitle(text);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_RELEASE_DATE)) {
                            movie.setReleaseDate(Constants.simpleDateFormat.parse(text));
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_MOVIE_PROFIT)) {
                            movie.setProfit(Integer.parseInt(text));
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_MOVIE_GENRE)) {
                            movie.setMovieGenre(text);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_MOVIE_PLATFORM)) {
                            movie.setPlatform(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}

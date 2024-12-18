package com.example.test2song;

import androidx.annotation.NonNull;

import java.util.Date;

public class Song {
    String songTitle;
    String artist;
    int noOfViews;
    Date songReleaseDate;

    public Song(String songTitle, String artist, int noOfViews, Date songReleaseDate) {
        this.setSongTitle(songTitle);
        this.artist = artist;
        this.setNoOfViews(noOfViews);
        this.songReleaseDate = songReleaseDate;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        if (songTitle != null) {
            this.songTitle = songTitle;
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNoOfViews() {
        return noOfViews;
    }

    public void setNoOfViews(int noOfViews) {
        if (noOfViews > 0) {
            this.noOfViews = noOfViews;
        }
    }

    public Date getSongReleaseDate() {
        return songReleaseDate;
    }

    public void setSongReleaseDate(Date songReleaseDate) {
        this.songReleaseDate = songReleaseDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "songTitle='" + songTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", noOfViews=" + noOfViews +
                ", songReleaseDate=" + songReleaseDate +
                '}';
    }
}

package com.example.test2_songclass;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.test2_songclass.utils.Constants;

import java.text.ParseException;
import java.util.Date;

@Entity(tableName = "songs")
public class Song implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String songTitle;
    private String artist;
    private int noOfViews;
    private Date songReleaseDate;

    @Ignore
    public Song() {}
    @Ignore
    public Song(String songTitle, String artist, int noOfViews, Date songReleaseDate) {
        this.songTitle = songTitle;
        this.artist = artist;
        this.noOfViews = noOfViews;
        this.songReleaseDate = songReleaseDate;
    }

    public Song(int id, String songTitle, String artist, int noOfViews, Date songReleaseDate) {
        this.id = id;
        this.songTitle = songTitle;
        this.artist = artist;
        this.noOfViews = noOfViews;
        this.songReleaseDate = songReleaseDate;
    }

    protected Song(Parcel in) {
        id = in.readInt();
        songTitle = in.readString();
        artist = in.readString();
        noOfViews = in.readInt();
        try {
            songReleaseDate = Constants.simpleDateFormat.parse(in.readString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Song{" +
                "songTitle='" + songTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", noOfViews=" + noOfViews +
                ", songReleaseDate=" + songReleaseDate +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(songTitle);
        dest.writeString(artist);
        dest.writeInt(noOfViews);
        dest.writeString(Constants.simpleDateFormat.format(songReleaseDate));
    }
}

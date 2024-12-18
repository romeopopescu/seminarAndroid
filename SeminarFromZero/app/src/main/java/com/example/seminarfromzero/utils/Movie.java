package com.example.seminarfromzero.utils;

import static androidx.room.ForeignKey.CASCADE;

import static com.example.seminarfromzero.utils.Constants.simpleDateFormat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "movies", foreignKeys = @ForeignKey(entity = Cinema.class, parentColumns = "id", childColumns = "idCinema", onDelete = CASCADE))
public class Movie implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idCinema;
    String movieTitle;
    Date releaseDate;
    int profit;
    String movieGenre;
    String platform;
//    public static final String DATE_PATTERN = "dd-MM-yyyy";
//    DateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);

    @Ignore
    public Movie() {}

    @Ignore
    public Movie(String movieTitle, Date releaseDate, int profit, String movieGenre, String platform) {
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.profit = profit;
        this.movieGenre = movieGenre;
        this.platform = platform;
    }

    public Movie(int id, int idCinema, String movieTitle, Date releaseDate, int profit, String movieGenre, String platform) {
        this.id = id;
        this.idCinema = idCinema;
        this.movieTitle = movieTitle;
        this.releaseDate = releaseDate;
        this.profit = profit;
        this.movieGenre = movieGenre;
        this.platform = platform;
    }

    protected Movie(Parcel in) {
        movieTitle = in.readString();
        try {
            releaseDate = simpleDateFormat.parse(in.readString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        profit = in.readInt();
        movieGenre = in.readString();
        platform = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieTitle);
        dest.writeString(simpleDateFormat.format(releaseDate));
        dest.writeInt(profit);
        dest.writeString(movieGenre);
        dest.writeString(platform);
    }

    @NonNull
    @Override
    public String toString() {
        return "Movie{" +
                "movieTitle='" + movieTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", profit=" + profit +
                ", movieGenre='" + movieGenre + '\'' +
                ", platform='" + platform + '\'' +
                ", simpleDateFormat=" + simpleDateFormat +
                '}';
    }
}

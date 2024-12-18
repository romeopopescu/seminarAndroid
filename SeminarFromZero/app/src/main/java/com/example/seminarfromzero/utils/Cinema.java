package com.example.seminarfromzero.utils;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cinemas")
public class Cinema {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cinemaName;
    private int noRooms;
    private String location;

    public Cinema(int id, String cinemaName, int noRooms, String location) {
        this.id = id;
        this.cinemaName = cinemaName;
        this.noRooms = noRooms;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", cinemaName='" + cinemaName + '\'' +
                ", noRooms=" + noRooms +
                ", location='" + location + '\'' +
                '}';
    }
}

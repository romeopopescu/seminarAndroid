package com.example.seminarfromzero.utils;

import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromTimeStamp(Long timeStamp) {
        return timeStamp != null ? new Date(timeStamp) : null;
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date != null ? date.getTime() : null;
    }
}

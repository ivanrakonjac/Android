package com.ika.threading.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public long dateToLong (Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date longToDate (long timestamp){
        return new Date(timestamp);
    }

}

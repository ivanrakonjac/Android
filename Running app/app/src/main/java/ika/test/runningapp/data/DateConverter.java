package ika.test.runningapp.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public long dateToTimestamp(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date timestampToDate(long timestamp){
        return new Date(timestamp);
    }

}

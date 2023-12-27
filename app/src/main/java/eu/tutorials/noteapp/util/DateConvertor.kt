package eu.tutorials.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConvertor {
    @TypeConverter
    fun timeStampFromDate(date:Date) : Long{
        return date.time
    }

    @TypeConverter
    fun dateFromTimestamp(timestamp:Long) :Date?{
        return Date(timestamp)
    }
}
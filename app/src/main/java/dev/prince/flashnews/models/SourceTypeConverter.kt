package dev.prince.flashnews.models

import androidx.room.TypeConverter
import com.google.gson.Gson

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.let {
            Gson().toJson(it)
        }
    }

    @TypeConverter
    fun toSource(sourceJson: String?): Source? {
        return sourceJson?.let {
            Gson().fromJson(it, Source::class.java)
        }
    }
}
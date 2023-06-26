package dev.prince.flashnews.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    @ColumnInfo(name = "source_id")
    var sourceId: String?,
    @SerializedName("name")
    var sourceName: String?
)
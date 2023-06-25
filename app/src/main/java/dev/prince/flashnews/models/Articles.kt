package dev.prince.flashnews.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "articles",
    indices = [Index(value = ["url"], unique = true)]
)
data class Articles(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("author")
    var author: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("content")
    var content: String?
)
//    : Serializable {
//    var type: String = ""
//}
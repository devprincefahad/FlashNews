package dev.prince.flashnews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.prince.flashnews.models.News
import dev.prince.flashnews.util.BOOKMARKED

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allNews: List<News>)

    @Query("SELECT * from news order by id DESC")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * from news order by id DESC")
    fun getTopHeadlines(): LiveData<List<News>>

    @Query("DELETE FROM news WHERE id= :id AND type = '$BOOKMARKED'")
    suspend fun deleteNews(id: Int)

    @Query("SELECT * FROM news WHERE type = '$BOOKMARKED'")
    fun getBookmarkedNews(title: String): List<News>

}
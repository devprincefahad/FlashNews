package dev.prince.flashnews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.util.BOOKMARKED

@Dao
interface NewsDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allNews: List<Articles>)

//    @Query("SELECT * from articles order by id DESC")
//    fun getAllNews(): LiveData<List<Articles>>

    @Query("SELECT * from articles order by id DESC")
    fun getTopHeadlines(): LiveData<List<Articles>>

//    @Query("DELETE FROM articles WHERE id= :id AND type = '$BOOKMARKED'")
//    suspend fun deleteNews(id: Int)

//    @Query("SELECT * FROM news WHERE type = '$BOOKMARKED'")
//    fun getBookmarkedNews(title: String): List<News>

}
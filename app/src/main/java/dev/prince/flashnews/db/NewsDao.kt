package dev.prince.flashnews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.util.SOURCE_REUTERS

@Dao
interface NewsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(news: News)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allNews: List<Articles>)
//    @Query("SELECT * from articles order by id DESC")
//    fun getAllNews(): LiveData<List<Articles>>
    @Query("SELECT * from articles WHERE source_id= :sourceId order by article_id DESC")
    fun getTopHeadlines(sourceId: String): LiveData<List<Articles>>

    @Query("SELECT * FROM articles WHERE source_id= :sourceId ORDER BY article_id DESC")
    fun getRecommendedHeadlines(sourceId: String): LiveData<List<Articles>>
//    fun getRecommendedHeadlines(): LiveData<List<Articles>>
//    @Query("DELETE FROM articles WHERE id= :id AND type = '$BOOKMARKED'")
//    suspend fun deleteNews(id: Int)

//    @Query("SELECT * FROM articles WHERE type =:category")
//    fun getNewsByCategory(category: String): LiveData<List<Articles>>

    @Query("SELECT * FROM articles WHERE url =:url")
    fun getNews(url: String): LiveData<Articles>

}
package dev.prince.flashnews.data.datasource

import android.util.Log
import dev.prince.flashnews.api.API_KEY2
import dev.prince.flashnews.api.ApiService
import dev.prince.flashnews.data.network.ApiResult
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.db.NewsDatabase
import dev.prince.flashnews.models.NewsResponse
import dev.prince.flashnews.util.SOURCE
import retrofit2.HttpException
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: ApiService,
    db: NewsDatabase
) : Repository {

    private val newsDao = db.newsDao()
    override val topNews = newsDao.getTopHeadlines()

    override suspend fun getTopRatedNews(): ApiResult<NewsResponse?> {
        return try {
            val result = api.getTopNews(SOURCE, API_KEY2)
            val news = result.body()?.articles
            if (result.isSuccessful) {
                Log.d("news-data", "result: $result +  news: $news")
                news?.let { newsDao.insert(it) }
                ApiResult.Success(result.body())
            } else {
                Log.d("news-data", "error occured")
                ApiResult.Error("API request failed with code ${result.code()}")
            }
        } catch (e: HttpException) {
            Log.d("news-data", "HttpException: ${e.message()}")
            ApiResult.Error("HTTP exception occurred: ${e.localizedMessage}")
        } catch (e: Exception) {
            Log.d("news-data", "HttpException: ${e.message}")
            ApiResult.Error("An error occurred: ${e.localizedMessage}")
        }
    }
}
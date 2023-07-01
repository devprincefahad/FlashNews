package dev.prince.flashnews.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import dev.prince.flashnews.R
import dev.prince.flashnews.api.API_KEY2
import dev.prince.flashnews.api.ApiService
import dev.prince.flashnews.data.network.ApiResult
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.db.NewsDatabase
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.models.NewsResponse
import dev.prince.flashnews.util.SOURCE_BBC_NEWS
import dev.prince.flashnews.util.SOURCE_REUTERS
import retrofit2.HttpException
import javax.inject.Inject

class DataSource @Inject constructor(
    private val api: ApiService,
    db: NewsDatabase
) : Repository {

    private val newsDao = db.newsDao()
    override val topNews = newsDao.getTopHeadlines(SOURCE_REUTERS)
    override val recommendedNews = newsDao.getRecommendedHeadlines(SOURCE_BBC_NEWS)
    override fun getNewsByUrl(url: String): LiveData<Articles> {
        return newsDao.getNews(url)
    }

    override suspend fun getNews(source: String): ApiResult<NewsResponse?> {
        return try {
            val result = api.getNewsBySource(source)
            val news = result.body()?.articles
            if (result.isSuccessful) {
                news?.let { newsDao.insert(it) }
                ApiResult.Success(result.body())
            } else {
                ApiResult.Error("${R.string.request_failed} ${result.code()}")
            }
        } catch (e: HttpException) {
            ApiResult.Error("${R.string.http_exception}  ${e.localizedMessage}")
        } catch (e: Exception) {
            ApiResult.Error("${R.string.error_occurred} ${e.localizedMessage}")
        }
    }

    override suspend fun getNewsByCategory(
        category: String
    ): ApiResult<NewsResponse?> {
        return try {
            val result = api.getNewsByCategory(category)
            val news = result.body()?.articles
//                ?.onEach {
//                it.type = category
//            }
            if (result.isSuccessful) {
                news?.let { newsDao.insert(it) }
                ApiResult.Success(result.body())
            } else {
                ApiResult.Error("${R.string.request_failed} ${result.code()}")
            }
        } catch (e: HttpException) {
            ApiResult.Error("${R.string.http_exception}  ${e.localizedMessage}")
        } catch (e: Exception) {
            ApiResult.Error("${R.string.error_occurred} ${e.localizedMessage}")
        }
    }

}
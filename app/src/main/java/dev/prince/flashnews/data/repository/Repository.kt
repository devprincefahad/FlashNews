package dev.prince.flashnews.data.repository

import androidx.lifecycle.LiveData
import dev.prince.flashnews.data.network.ApiResult
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.models.NewsResponse

interface Repository {

    val topNews: LiveData<List<Articles>>

    val recommendedNews: LiveData<List<Articles>>

    suspend fun getNews(source: String): ApiResult<NewsResponse?>

}
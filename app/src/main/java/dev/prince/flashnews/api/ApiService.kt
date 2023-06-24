package dev.prince.flashnews.api

import dev.prince.flashnews.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "80e490c9cbd94770a888da8084ec17ab"

interface ApiService {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    suspend fun getNewsByCategory(
        @Query("country") country: String,
        @Query("pagesize") pageSize: Int,
        @Query("category") category: String,
        @Query("q") query: String?,
        @Query("apiKey") api_Key: String
    ): NewsResponse

    //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=ccb2e680af1e4ed0bf132ead97ef5453

    @GET("v2/top-headlines?sources=bbc-news&apiKey=$API_KEY")
    suspend fun getTopNews(
        @Query("pagesize") pageSize: Int,
        @Query("apiKey") api_Key: String
    ): NewsResponse


}
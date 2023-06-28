package dev.prince.flashnews.api

import dev.prince.flashnews.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//const val API_KEY1 = "80e490c9cbd94770a888da8084ec17ab"
const val API_KEY2 = "ccb2e680af1e4ed0bf132ead97ef5453"
const val API_KEY3 = "a58ef6d2bd544329bdbd7f57cc3b48a9"

interface ApiService {

    //    https://newsapi.org/v2/top-headlines/sources?category=business&apiKey=ccb2e680af1e4ed0bf132ead97ef5453

    @GET("v2/top-headlines")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY2
    ): Response<NewsResponse>

//    @Query("pagesize") pageSize: Int,
//    @Query("category") category: String,
//    @Query("apiKey") apiKey: String
    //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=ccb2e680af1e4ed0bf132ead97ef5453

    @GET("v2/top-headlines")
    suspend fun getNewsBySource(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY2
    ): Response<NewsResponse>
//?sources=bbc-news&apiKey={apiKey}
}
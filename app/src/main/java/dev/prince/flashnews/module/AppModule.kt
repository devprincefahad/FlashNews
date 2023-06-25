package dev.prince.flashnews.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.prince.flashnews.api.ApiService
import dev.prince.flashnews.data.datasource.DataSource
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.db.NewsDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://newsapi.org/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news_database"
    ).build()

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService, database: NewsDatabase): DataSource =
        DataSource(apiService, database)

    @Singleton
    @Provides
    fun provideRepository(dataSource: DataSource): Repository = dataSource

}
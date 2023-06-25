package dev.prince.flashnews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.prince.flashnews.models.Articles

@Database(entities = [Articles::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}
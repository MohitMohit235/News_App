package com.example.news.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.data.model.News


@Database(
        entities = [News::class],
        version = 2,
        exportSchema = false
)


abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    
    companion object {
        @Volatile
        private var INSTANCE: NewsDataBase? = null
        
        fun getDatabase(context: Context): NewsDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                        context.applicationContext,
                        NewsDataBase::class.java,
                        "news_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
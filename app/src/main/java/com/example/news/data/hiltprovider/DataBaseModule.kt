package com.example.news.data.hiltprovider

import android.content.Context
import androidx.room.Room
import com.example.news.data.local.NewsDao
import com.example.news.data.local.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    
    @Provides
    @Singleton
    fun provideAppDataBase(
            @ApplicationContext context: Context,
    ): NewsDataBase {
        return Room.databaseBuilder(
                context = context,
                NewsDataBase::class.java,
                "news_database"
        )
                .fallbackToDestructiveMigration()
                .build()
    }
    
    @Provides
    @Singleton
    fun provideNewsDao(dataBase: NewsDataBase): NewsDao{
        return dataBase.newsDao()
    }
    
}
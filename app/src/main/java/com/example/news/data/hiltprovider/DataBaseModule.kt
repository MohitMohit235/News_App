package com.example.news.data.hiltprovider

import android.content.Context
import androidx.room.Room
import com.example.news.data.localdatabase.AppDataBase
import com.example.news.data.localdatabase.BookMarkDao
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
    ): AppDataBase {
        return Room.databaseBuilder(
                context = context,
                AppDataBase::class.java,
                "news_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideBookMarkDao(database: AppDataBase): BookMarkDao{
        return database.bookMarkDao()
    }
    
}
package com.example.news.data.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


@Dao
interface BookMarkDao {
    
    @Singleton
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookMark(news: NewsEntity)
    
    
    @Singleton
    @Delete
    suspend fun removeBookMark(news: NewsEntity)
    
    @Singleton
    @Query("SELECT * FROM news_table")
    fun getAllBookMarkNews(): Flow<List<NewsEntity>>
    
    
    
    @Singleton
    @Query("SELECT EXISTS(SELECT * FROM news_table WHERE artical_id = :newsId)")
    fun isNewsBoolMark(newsId: String): Flow<Boolean>
}
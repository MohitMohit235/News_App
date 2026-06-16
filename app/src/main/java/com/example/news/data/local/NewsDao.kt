package com.example.news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    
    //  CACHE (in offline data news)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<News>)
    
    @Query("SELECT*FROM news_table ORDER BY pubDate DESC")
    fun getCachedNews(): Flow<List<News>>
    
    @Query("DELETE FROM news_table WHERE isBookmarked = 0")
    suspend fun clearOldCache()
    
    //  BOOKMARK
    @Query("UPDATE news_table SET isBookmarked = :value WHERE link = :link")
    suspend fun toggleBookMark(link: String,value: Boolean)
    
    @Query("SELECT*FROM news_table WHERE isBookmarked = 1")
    fun getBookMarkNews(): Flow<List<News>>
    
    // RECENTLY READ
    @Query("UPDATE news_table SET readAt = :time WHERE artical_id = :id")
    suspend fun markAsRead(id: String, time: Long = System.currentTimeMillis())
    
    @Query("SELECT * FROM news_table WHERE readAt IS NOT NULL ORDER BY readAt DESC LIMIT 20")
    fun getRecentlyRead(): Flow<List<News>>
    
    @Delete
    suspend fun deleteNews(news: News)
    
}
package com.example.news.domain.repository

import androidx.paging.PagingData
import com.example.news.data.model.News
import com.example.news.data.model.NewsList
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(category: String): Result<NewsList>
    
    fun getPagesNews(
            category: String,
            query: String
    ): Flow<PagingData<News>>
    
    suspend fun saveNews(news: News)
    suspend fun toggleBookMark(news: News)
    fun getBookmarkedNews(): Flow<List<News>>
    suspend fun markAsRead(news: News)
    fun getRecentlyRead(): Flow<List<News>>
    suspend fun deleteNews(news: News)
}
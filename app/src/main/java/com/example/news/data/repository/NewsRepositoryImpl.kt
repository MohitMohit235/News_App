package com.example.news.data.repository


import android.annotation.SuppressLint
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.local.NewsDao
import com.example.news.data.model.News
import com.example.news.data.model.NewsList
import com.example.news.data.pagging.NewsPaggingSource
import com.example.news.data.remote.ApiService
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
        private val api: ApiService,
        private val dao : NewsDao
) : NewsRepository {
    
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getNews(category: String): Result<NewsList> {
        return try {
            val response = api.getNewsList(
                    category = category
            )
            Result.success(response)
        } catch (exp: Exception) {
            Result.failure(exp)
        }
    }
    
    override fun getPagesNews(
            category: String,
            query: String,
            cuntry: String,
    ): Flow<PagingData<News>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 10,
                        enablePlaceholders = false,
                        prefetchDistance = 3
                ),
                pagingSourceFactory = {
                    NewsPaggingSource(api, category, query, cuntry)
                },
        ).flow
    }
    
    override suspend fun saveNews(news: News) {
        dao.insertAllNews(listOf(news))
    }
    
    override suspend fun toggleBookMark(news: News) {
        dao.insertAllNews(
                listOf(
                        news.copy(
                                link = news.link ?:"",
                                isBookmarked = true
                        )
                )
        )
    }
    
    override fun getBookmarkedNews(): Flow<List<News>> {
        return dao.getBookMarkNews()
    }
    
    override suspend fun markAsRead(news: News) {
        dao.markAsRead(news.artical_id?:return)
    }
    
    override fun getRecentlyRead(): Flow<List<News>> {
        return dao.getRecentlyRead()
    }
    
    override suspend fun deleteNews(news: News) {
        dao.deleteNews(news)
    }
    
}
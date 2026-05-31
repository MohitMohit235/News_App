package com.example.news.data.repository


import android.annotation.SuppressLint
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.localdatabase.BookMarkDao
import com.example.news.data.localdatabase.NewsEntity
import com.example.news.data.model.News
import com.example.news.data.model.NewsList
import com.example.news.data.pagging.NewsPaggingSource
import com.example.news.data.remote.ApiService
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
        private val api: ApiService,
        private val bookMarkDao: BookMarkDao,
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
    
    override fun getBookMarkNews(): Flow<List<NewsEntity>> = bookMarkDao.getAllBookMarkNews()
    
    override fun isBookmark(newsId: String): Flow<Boolean> =
        bookMarkDao.isNewsBoolMark(newsId = newsId)
    
    override suspend fun addBookMark(news: NewsEntity) {
        bookMarkDao.saveBookMark(news = news)
    }
    
    override suspend fun removeBookMark(news: NewsEntity) {
        bookMarkDao.removeBookMark(news = news)
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
    
}
package com.example.news.data.repository


import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.model.News
import com.example.news.data.model.NewsList
import com.example.news.data.pagging.NewsPaggingSource
import com.example.news.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor (
    private val api : ApiService,
){

    @SuppressLint("SuspiciousIndentation")
    suspend fun getNews(category: String): Result<NewsList>{
        return try {
        val response = api.getNewsList(
            category = category)

            Log.d("API_RESPONSE", response.toString())

            Result.success(response)
        }catch (exp: Exception){
            Result.failure(exp)
        }
    }


    fun getPagesNews(
        category: String,
        searchQuery: String,
        cuntry: String
    ): Flow<PagingData<News>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 3
            ),
            pagingSourceFactory = {
                NewsPaggingSource(api,category,searchQuery,cuntry)
            },
        ).flow
    }

}
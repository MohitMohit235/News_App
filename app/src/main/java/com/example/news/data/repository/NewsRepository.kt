package com.example.news.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.news.data.model.NewsList
import com.example.news.data.remote.ApiService
import com.google.gson.Gson
import javax.inject.Inject

class NewsRepository @Inject constructor (
    private val api : ApiService
){
    @SuppressLint("SuspiciousIndentation")
    suspend fun getNews(category: String): Result<NewsList>{
        return try {
        val response = api.getNewsList(
            category = category,
//            limit = 1,
//            skip = 1
        )
            Result.success(response)
        }catch (exp: Exception){
            Result.failure(exp)
        }
    }
}
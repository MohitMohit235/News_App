package com.example.news.data.remote

import retrofit2.http.Query
import com.example.news.data.model.NewsList
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton


interface ApiService {
        @GET("latest")
        suspend fun getNewsList(
            @Query("apikey") apiKey: String = "pub_cb4140cfede24d6ab3514922638ae865",
            @Query("category") category: String?=null,
            @Query("q") searchQuery: String?=null,
            @Query("page") page : String? = null
        ): NewsList
}
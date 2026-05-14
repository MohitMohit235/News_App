package com.example.news.data.model

data class NewsList(
    val status: String,
    val totalResults: Int,
    val results: List<News>,
    val nextPage: String?

)
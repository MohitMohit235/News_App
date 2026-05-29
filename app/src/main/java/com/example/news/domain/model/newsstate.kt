package com.example.news.domain.model

import com.example.news.data.model.NewsList

object newsstate {
    data class NewsState(
            val Loading : Boolean = false,
            val Success: NewsList? = null,
            val Falior: String? = null
    )
}
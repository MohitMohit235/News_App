package com.example.news.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsList(
    val status: String,
    val totalResults: Int,
    val results: List<News>,
    val nextPage: String?

) : Parcelable
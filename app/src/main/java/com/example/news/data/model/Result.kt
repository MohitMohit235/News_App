package com.example.news.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    val artical_id : String?,
    val link: String?,
    val image_url: String?,
    val title: String?,
    val description: String?,
    val pubDate: String?,
    val source_name: String?,
): Parcelable
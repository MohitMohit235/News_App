package com.example.news.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "news_table")
data class News(
        val artical_id: String? = null,
        
        @PrimaryKey
        val link: String = "",
        val image_url: String? = null,
        val title: String? = null,
        val description: String? = null,
        val pubDate: String? = null,
        val source_name: String? = null,
        
        val isBookmarked: Boolean = false,
        val readAt: Long? = null,
        val cachedAt: Long = System.currentTimeMillis(),
        
        ) : Parcelable
package com.example.news.data.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.data.model.News

@Entity(tableName = "news_table")
data class NewsEntity(
        @PrimaryKey
        val artical_id: String,
        val link: String,
        val image_url: String,
        val title: String,
        val description: String,
        val pubDate: String,
        val source_name: String
)

fun News.toNewsEntity(): NewsEntity {
    return NewsEntity(
            artical_id = this.artical_id ?: "",
            link = this.link ?: "",
            image_url = this.image_url ?: "",
            title = this.title ?: "No Title",
            description = this.description ?: "No Description",
            pubDate = this.pubDate ?: "",
            source_name = this.source_name ?: ""
    )
}




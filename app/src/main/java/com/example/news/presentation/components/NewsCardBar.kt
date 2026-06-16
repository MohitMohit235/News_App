package com.example.news.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.data.model.News
import com.example.news.presentation.viewmodel.NewsViewModel


@Composable
fun NewsCard(
        news: News,
        onclick: () -> Unit,
        onBookMark: (News) -> Unit,
) {
    val Font = FontFamily(Font(R.font.jrfonts))
    var isBookmarked by remember(news.link) {
        mutableStateOf(news.isBookmarked)
    }
    Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onclick() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column {
            
            Box(
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
            ) {
                AsyncImage(
                        model = news.image_url,
                        contentDescription = news.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                )
                
                
                if (news.source_name == "BBC" || news.source_name == "CNN") {
                    Box(
                            modifier = Modifier
                                    .padding(10.dp)
                                    .align(Alignment.TopStart)
                                    .background(
                                            color = Color(0xFFE24B4A),
                                            shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                                text = "Breaking",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                IconButton(
                        onClick = {onBookMark(news)},
                        modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 10.dp, end = 10.dp)
                                .padding(6.dp)
                                .size(30.dp)
                                .background(
                                        color = Color.Black.copy(alpha = 0.6f),
                                        shape = CircleShape
                                )
                               
                ) {
                    Icon(
                            imageVector = if(isBookmarked)
                                Icons.Filled.Bookmark
                            else
                                Icons.Outlined.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked)
                                Color(0xFFFFAA00)
                            else
                                Color.White,
                            modifier = Modifier.size(18.dp)
                    )
                }
            }
            
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                
                
                Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                            text = news.source_name ?: "Unknown",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                    )
                    Text(
                            text = "·",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                            text = news.pubDate ?: "",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                news.title?.let {
                    Text(
                            text = it,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Font,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                    )
                }
                
                
                news.description?.let {
                    Text(
                            text = it,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontFamily = Font,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

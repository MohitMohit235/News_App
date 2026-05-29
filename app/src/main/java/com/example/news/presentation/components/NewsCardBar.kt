package com.example.news.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.data.model.News


@Composable
fun NewsCard(
        news: News,
        onclick: () -> Unit,
) {
    val Font = FontFamily(Font(R.font.jrfonts))
    
    Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .height(102.dp)
                    .clickable {onclick() }
    ) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                    model = news.image_url,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(102.dp)
                            .clip(shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            )
            
            Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                news.title?.let {
                    Text(
                            text = it,
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.onBackground,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = Font,
                            fontSize = 16.sp
                    )
                }
                
                news.description?.let {
                    Text(
                            text = it,
                            maxLines = 2,
                            color = MaterialTheme.colorScheme.onBackground,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = Font,
                            fontSize = 11.sp
                    )
                }
            }
            
        }
        
    }
    
}

package com.example.news.presentation.onbodingscreens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.data.model.News
import com.example.news.presentation.viewmodel.NewsViewModel

@Composable
fun NewsDetailScreen(
        navController: NavController,
        viewModel: NewsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val font = FontFamily(Font(R.font.jrfonts))
    val news =
        navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<News>("news")
    
    
    val isSaved by viewModel.isNewsSave(news?.artical_id).collectAsState(initial = false)
    
    LazyColumn(
            modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.onBackground)
    ) {
        item {
            Box(
                    modifier = Modifier
                            .fillMaxWidth(),
                    contentAlignment = Alignment.Center
            ) {
                
                AsyncImage(
                        model = news?.image_url ?: Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null
                        ),
                        contentDescription = null,
                        modifier = Modifier
                                .fillMaxSize()
                                .fillMaxHeight(fraction = 0.6f)
                                .size(400.dp),
                        contentScale = ContentScale.Crop
                )
                
                
            }
            
            Spacer(
                    modifier =
                        Modifier.height(15.dp)
            )
            
            Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                        text = news?.source_name ?: "No content available",
                        fontFamily = font,
                        color = MaterialTheme.colorScheme.background
                )
                
                
                
                Text(
                        text = news?.pubDate ?: "No content available",
                        fontFamily = font,
                        color = MaterialTheme.colorScheme.background
                )
            }
            Spacer(
                    modifier =
                        Modifier.height(20.dp)
            )
            
            
            Divider(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(horizontal = 70.dp)
            )
            
            Spacer(
                    modifier =
                        Modifier.height(20.dp)
            )
            
            
            Text(
                    modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 8.dp),
                    text = news?.title ?: "",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = font,
                    //     textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
            )
            
            
            Text(
                    modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 8.dp),
                    text = news?.description ?: "",
                    fontFamily = font,
                    fontSize = 20.sp,
                    //  textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background
            )
            
            Spacer(
                    modifier =
                        Modifier.height(20.dp)
            )
            
            Divider(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(horizontal = 70.dp)
            )
            
            Spacer(
                    modifier =
                        Modifier.height(20.dp)
            )
            
            
            TextButton(
                    onClick = {
                        news?.link?.let { url ->
                            if (url.isNotEmpty()) {
                                OpenFullNewsDetail(context, url)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                        text = if (!news?.link.isNullOrEmpty()) "Read Full Article..." else "No link available",
                        fontFamily = font,
                        fontSize = 16.sp,
                        color = Color(0xFF498FFF),
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center
                )
            }
            
        }
    }
}

fun OpenFullNewsDetail(context: android.content.Context, url: String) {
    try {
        val customTabeInstant = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build()
        customTabeInstant.launchUrl(context, Uri.parse(url))
    } catch (e: Exception) {
        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}
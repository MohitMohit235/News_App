package com.example.news.presentation.onbodingscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news.R
import com.example.news.presentation.components.NewsCard
import com.example.news.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkScreen(
        navController: NavController,
        viewModel: NewsViewModel = hiltViewModel()
){
val Font = FontFamily(Font(R.font.jrfonts))
    
    val bookmarks by viewModel.bookmarkedNews.collectAsState()
    
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(
                                    text = "Saved News",
                                    fontFamily = Font,
                                    fontSize = 18.sp
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                )
                            }
                        },
                        actions = {
                            
                            if (bookmarks.isNotEmpty()) {
                                IconButton(onClick = {
                                    bookmarks.forEach { viewModel.deleteNews(it) }
                                }) {
                                    Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Clear all",
                                            tint = Color(0xFFE24B4A)
                                    )
                                }
                            }
                        }
                )
            }
    ) { paddingValues ->
        
        if (bookmarks.isEmpty()) {
            
            Box(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center
            ) {
                Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "📌", fontSize = 48.sp)
                    Text(
                            text = "Koi saved news nahi hai",
                            fontFamily = Font,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                            text = "News pe bookmark tap karo save karne ke liye",
                            fontFamily = Font,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            Column(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
            ) {
                
                Text(
                        text = "${bookmarks.size} articles saved",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                
                
                LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(
                                horizontal = 16.dp,
                                vertical = 8.dp
                        )
                ) {
                    items(
                            items = bookmarks,
                            key = { it.link}
                    ) { news ->
                        NewsCard(
                                news = news,
                                onclick = {
                                    navController.currentBackStackEntry
                                            ?.savedStateHandle
                                            ?.set("news", news)
                                    navController.navigate("detail_screen")
                                },
                                onBookMark ={
                                    viewModel.toggleBookmark(news)
                                }
                        )
                    }
                }
            }
        }
    }
}
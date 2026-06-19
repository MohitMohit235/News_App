package com.example.news.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.presentation.navigation.route.Screen
import com.example.news.presentation.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MianSearchScreen(
        navController: NavController,
        viewModel: NewsViewModel = hiltViewModel(),
) {
    var query by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    var isSearchExecuted by remember { mutableStateOf(false) }
    val searchResult = viewModel.news.collectAsLazyPagingItems()
    val suggestions =
        listOf("Sports", "Technology", "Business", "Health", "Entertainment", "Science")
    val filteredSuggestions = suggestions.filter { it.contains(query, ignoreCase = true) }
    
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
    ) {
        
        SearchBar(
                query = query,
                onQueryChange = {
                    query = it
                    if (it.isEmpty()) {
                        isSearchExecuted = false
                    }
                },
                onSearch = { finalQuery ->
                    if (finalQuery.isNotEmpty()) {
                        viewModel.searchQuery(finalQuery)
                        isSearchExecuted = true
                        isActive = false
                    }
                },
                active = isActive,
                onActiveChange = { isActive = it },
                placeholder = { Text("Search news...") },
                leadingIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                            viewModel.searchQuery(query = query)
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = SearchBarDefaults.colors(
                        containerColor = Color.White
                ),
        ) {
            
            LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredSuggestions) { suggestion ->
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        query = suggestion
                                        viewModel.searchQuery(suggestion)
                                        isSearchExecuted = true
                                        isActive = false
                                    }
                                    .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                                Icons.Outlined.Timelapse,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(text = suggestion)
                    }
                }
            }
        }
        if (isSearchExecuted && !isActive) {
            Spacer(modifier = Modifier.height(10.dp))
            
            if (searchResult.itemCount > 0) {
                LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(searchResult.itemCount) { index ->
                        val newsItem = searchResult[index]
                        newsItem?.let { item ->
                            NewsCard(
                                    news = item,
                                    onclick = {
                                        navController.currentBackStackEntry
                                                ?.savedStateHandle
                                                ?.set("news", item)
                                        navController.navigate(Screen.DetailScreen.route)
                                    },
                                    onBookMark = { news ->
                                        viewModel.saveNews(news)
                                        viewModel.toggleBookmark(news)
                                    }
                            )
                        }
                    }
                }
            } else {
                Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}
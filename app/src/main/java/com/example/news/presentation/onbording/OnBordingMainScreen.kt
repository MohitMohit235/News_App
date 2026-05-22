package com.example.news.presentation.onbording

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.domain.viewmodel.NewsViewModel
import com.example.news.presentation.animationEffects.ShimmerEffect
import com.example.news.presentation.commentes.DrawerContent
import com.example.news.presentation.commentes.commen.categories
import com.example.news.presentation.common.NewsCard
import com.example.news.presentation.common.NewsSearchBar
import com.example.news.presentation.common.NewsTabsBar
import com.example.news.presentation.common.NewsTopBar
import kotlinx.coroutines.launch


@Composable
fun MainNewsScreen(
        viewModel: NewsViewModel = hiltViewModel(),
        OndetailClick: () -> Unit,
) {
    
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val navController = rememberNavController()
    
    val PagerState = rememberPagerState {
        17
    }
    
    val currentCategory =
        categories[PagerState.currentPage]
    
    
    val newsItems = remember(currentCategory) {
        viewModel.news
    }.collectAsLazyPagingItems()
    
    LaunchedEffect(PagerState.currentPage) {
        Log.d("NEWS_UI", newsItems.itemCount.toString())
        val cetegory = categories[PagerState.currentPage]
        viewModel.changeCategory(cetegory)
    }
    
    ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(
                        onItemClick = { },
                        onClose = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
            }
    ) {
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                
                ) {
            
            NewsTopBar(
                    pagerState = PagerState,
                    onclick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
            )
            
            
            NewsTabsBar(
                    viewModel = viewModel,
                    pagerState = PagerState
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                NewsSearchBar()
            }
            
            
            
            Spacer(modifier = Modifier.height(20.dp))
            
            when {
                
                newsItems.loadState.refresh
                        is LoadState.Loading -> {
                    
                    Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment =
                                Alignment.Center
                    ) {
                        Column() {
                            repeat(8) {
                                ShimmerEffect(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .height(150.dp)
                                                .padding(16.dp)
                                                .background(
                                                        Color.LightGray,
                                                        shape = RoundedCornerShape(10.dp)
                                                )
                                )
                            }
                        }
                    }
                }
                
                newsItems.loadState.refresh
                        is LoadState.Error -> {
                    
                    val error =
                        newsItems.loadState.refresh
                                as LoadState.Error
                    
                    Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment =
                                Alignment.Center
                    ) {
                        
                        
                        Text(
                                text =
                                    "Error: ${error.error.message}"
                        )
                    }
                }
                
                else -> {
                    
                    HorizontalPager(
                            state = PagerState,
                            key = { page -> categories[page] }
                    ) { page ->
                        
                        LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement =
                                    Arrangement.spacedBy(16.dp)
                        ) {
                            
                            items(newsItems.itemCount) { index ->
                                
                                val newsItem =
                                    newsItems[index]
                                
                                newsItem?.let {
                                    NewsCard(
                                            it,
                                            onclick = OndetailClick,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



package com.example.news.presentation.onbording

import android.R.attr.category
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.domain.NewsViewModel
import com.example.news.presentation.animationEffects.ShimmerEffect
import com.example.news.presentation.common.NewsCard
import com.example.news.presentation.common.NewsSearchBar
import com.example.news.presentation.common.NewsTabsBar
import com.example.news.presentation.common.NewsTopBar

@Composable
fun MainNewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState {
        17
    }

    val categories = listOf(
        "top",
        "business",
       "crime",
        "domestic",
        "education",
        "entertainment",
        "environment",
        "food",
        "health",
       "lifestyle",
        "politics",
       "science",
        "sports",
        "technology",
        "tourism",
        "world",
       "other"
    )

    val currentCategory =
        categories[pagerState.currentPage]


    val newsItems = remember(currentCategory){
        viewModel.news
    }.collectAsLazyPagingItems()

    LaunchedEffect(pagerState.currentPage){
        Log.d("NEWS_UI", newsItems.itemCount.toString())
        val cetegory = categories[pagerState.currentPage]
        viewModel.changeCategory(cetegory)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NewsTopBar()

        NewsTabsBar(
            viewModel = viewModel,
            pagerState = pagerState
        )

        NewsSearchBar()

        Spacer(modifier = Modifier.height(20.dp))

        when{

            newsItems.loadState.refresh
                    is androidx.paging.LoadState.Loading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Column(){
                        repeat(8){
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
                    is androidx.paging.LoadState.Error -> {

                val error =
                    newsItems.loadState.refresh
                            as androidx.paging.LoadState.Error

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
                    state = pagerState,
                    key = {page-> categories[page]}
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
                                NewsCard(it)
                            }

                        }
                    }
                }
            }
        }
    }
}
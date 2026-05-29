package com.example.news.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.R
import com.example.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch


@Composable
fun NewsTabsBar(viewModel: NewsViewModel,pagerState: PagerState){
    val tabs = listOf(
        "All" to "top",
        "Business" to "business",
        "Domestic" to "domestic",
        "Education" to "education",
        "Entertainment" to "entertainment",
        "Environment" to "environment",
        "Food" to "food",
        "Health" to "health",
        "Lifestyle" to "lifestyle",
        "Politics" to "politics",
        "Science" to "science",
        "Sports" to "sports",
        "Technology" to "technology",
        "Tourism" to "tourism",
        "World" to "world",
        "Other" to "other"
    )

    val category = tabs[pagerState.currentPage]

    viewModel.getNewsData(category = category.second)

    val font= FontFamily(Font(R.font.jrfonts))
    var scop = rememberCoroutineScope()



    Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .drawBehind {
                drawRect(
                        color = Color.Black.copy(alpha = 0.15f),
                        topLeft = Offset(0f, size.height),
                        size = Size(size.width, 3.dp.toPx())
                )
            }
    ) {
      ScrollableTabRow (
            modifier = Modifier.fillMaxWidth()
                    .drawBehind {
                        drawRect(
                                brush = Brush.verticalGradient(
                                        colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.12f)
                                        )
                                ),
                                topLeft = Offset(0f, size.height - 10.dp.toPx()),
                                size = Size(size.width, 10.dp.toPx())
                        )
                    },
            selectedTabIndex = pagerState.currentPage,
              edgePadding = 0.dp,
           contentColor = MaterialTheme.colorScheme.background,
          containerColor = MaterialTheme.colorScheme.background,
          divider = {},
              indicator = { tabPositions ->

                  Box(
                      modifier = Modifier
                          .tabIndicatorOffset(
                              tabPositions[pagerState.currentPage]
                          )
                          .height(4.dp)
                          .background(Color(0xFF2C5553))
                  )
          }
        ) {
            tabs.forEachIndexed { index, string ->
                Tab(
                    modifier = Modifier.height(40.dp),
                    selected = pagerState.currentPage == index,
                    unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                    selectedContentColor = MaterialTheme.colorScheme.onBackground,
                    onClick = {
                        scop.launch {
                        pagerState.animateScrollToPage(index)
                        //val category = tabs[pagerState.currentPage].second
                        Log.d("NEWS_TAB","Selected: $category")
                            }
                        },
                    text = {
                        Text(text = string.first,
                            fontSize = if (pagerState.currentPage==index) 15.sp else 11.sp,
                            fontFamily = font,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}
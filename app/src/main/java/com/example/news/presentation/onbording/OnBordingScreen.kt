package com.example.news.presentation.onbording

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news.presentation.common.NewsButton
import com.example.news.presentation.common.NewsTextButton
import com.example.news.presentation.common.PageIndicator
import com.example.news.presentation.onbording.component.OnBordPage
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

@SuppressLint("RememberReturnType")
@Composable
fun OnBordScreen() {

    Column() {

        val PagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (PagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = PagerState) { index ->
            OnBordPage(page = pages[index])

        }

        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pages.size,
                selectedPage = PagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically){

                val scop = rememberCoroutineScope()

                if(buttonState.value[0].isNotEmpty()){
                    NewsTextButton(text = buttonState.value[0],
                        onClick = {
                            scop.launch {
                               PagerState.animateScrollToPage(page = PagerState.currentPage-1)
                            }
                        }
                    )
                }

                NewsButton(text = buttonState.value[1], onClick = {
                    scop.launch {
                        if (PagerState.currentPage==3){

                        }else {
                            PagerState.animateScrollToPage(page = PagerState.currentPage+1)
                        }
                    }
                }
              )
            }
        }
    }
}
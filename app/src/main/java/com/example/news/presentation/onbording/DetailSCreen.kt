package com.example.news.presentation.onbording

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.news.data.model.News

@Composable
fun NewsDetailScreen(
    navController: NavController
) {

    val news =
        navController
            .previousBackStackEntry
            ?.savedStateHandle
            ?.get<News>("news")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        AsyncImage(
            model = news?.image_url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Text(
            text = news?.title ?: "",
            style = MaterialTheme
                .typography
                .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                news?.description
                    ?: ""
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Text(
            text =
                news?.source_name
                    ?: "No content available"
        )
    }
}
package com.example.news.presentation.components


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.news.ui.theme.NewsTheme
import com.example.news.R
@Composable
fun OnBordPage(
    page: Page
) {
    val Fonts = FontFamily(
        Font(R.font.jrfonts)
    )


    Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ){


        AsyncImage(
            model = page.image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.6f),
            contentScale = ContentScale.Crop
        )
        
        Text(
            text = page.title,
            fontFamily = Fonts,
            fontSize = 25.sp,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 30.dp),
            color = MaterialTheme.colorScheme.background
        )

        Text(
            text = page.discription,
            fontSize = 15.sp,
            fontFamily = Fonts,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 30.dp),
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun mu() {
    NewsTheme{
            OnBordPage(
                page = pages[0]
        )
    }
}
package com.example.news.presentation.common

import android.graphics.BlurMaskFilter
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.example.news.presentation.onbording.Page

@Composable
fun PageIndicator(
    modifier: Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color(0xFF336578),
    unSelectedColor: Color = Gray
) {
    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(5.dp)){
        repeat(pageSize){ page ->
            Box(modifier = Modifier.size(12.dp).clip(MaterialTheme.shapes.extraLarge).background(if(page==selectedPage)selectedColor else unSelectedColor))
        }
    }
}
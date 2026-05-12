package com.example.news.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun NewsSearchBar() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        value = text,
        placeholder = { Text("Search", color = MaterialTheme.colorScheme.background) },
        onValueChange = {text=it},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = MaterialTheme.shapes.medium
    )
}
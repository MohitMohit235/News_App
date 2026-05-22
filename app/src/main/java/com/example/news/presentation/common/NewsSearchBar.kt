package com.example.news.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.R
import com.example.news.domain.viewmodel.NewsViewModel
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSearchBar(viewModel: NewsViewModel = hiltViewModel()) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val suggestions = listOf(
        "Sports",
        "Technology",
        "Business",
        "Health",
        "Entertainment",
        "Science"
    )

    val filteredSuggestions = suggestions.filter {
        it.contains(query, ignoreCase = true)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,

    ){
        TextField(
            value = query,
            onValueChange = {
                query = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(56.dp),

            placeholder = {
                Text("Search News...", color = MaterialTheme.colorScheme.background)
            },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            },

            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { query = "" }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            },

            singleLine = true,

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchQuery(query)
                    query = ""
                }
            ),

            shape = RoundedCornerShape(18.dp),

            colors = TextFieldDefaults.colors(
                focusedContainerColor =
                    MaterialTheme.colorScheme.onBackground,

                unfocusedContainerColor =
                    MaterialTheme.colorScheme.onBackground,

                focusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = MaterialTheme.colorScheme.background
            )
        )
    }
}

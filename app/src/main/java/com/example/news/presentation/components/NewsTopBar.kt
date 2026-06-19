package com.example.news.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.news.R
import com.example.news.presentation.components.commen.categories
import com.example.news.presentation.components.commen.countryCodeMap
import com.example.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(
        viewModel: NewsViewModel = hiltViewModel(),
        pagerState: PagerState,
        onclick: () -> Unit,
        onSearchClick: () -> Unit,
) {
    var showMenuItems by remember { mutableStateOf(false) }
    var showSearchBarPage by remember { mutableStateOf(false) }
    var showCountryBarPage by remember { mutableStateOf(false) }
    var selectedCountry by rememberSaveable { mutableStateOf("India") }
    val allIndex = categories.indexOf("top")
    val coroutineScope = rememberCoroutineScope()
    val font = FontFamily(Font(R.font.cinze))
    val iconFonts = FontFamily(Font(R.font.jrfonts))
    val draerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    
    
    Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
    ) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(top = 50.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                
                
                Box {
                    IconButton(
                            onClick = onclick
                    ) {
                        Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Book icon",
                                modifier = Modifier.size(30.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                
                
                Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontFamily = font, fontSize = 28.sp)) {
                                append("K")
                            }
                            append("HABAR")
                        },
                        fontFamily = font,
                        fontSize = 18.sp,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                        color = MaterialTheme.colorScheme.onBackground,
                )
            }
            
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.wrapContentWidth()
            ) {
                
                IconButton(
                        onClick = {
                            onSearchClick()
                        }
                ) {
                    Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

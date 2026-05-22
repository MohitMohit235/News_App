package com.example.news.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.wrapMappedColumns
import com.example.news.R
import com.example.news.domain.viewmodel.NewsViewModel
import com.example.news.presentation.commentes.commen
import com.example.news.presentation.commentes.commen.categories
import com.example.news.presentation.commentes.commen.countryCodeMap
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(
        viewModel: NewsViewModel = hiltViewModel(),
        pagerState: PagerState,
        onclick: () -> Unit,
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
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                
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
                
                Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.CenterEnd
                ) {
                    IconButton(
                            onClick = { showCountryBarPage = !showCountryBarPage },
                            modifier = Modifier
                                    .width(100.dp)
                            //   .padding(horizontal = 5.dp)
                    ) {
                        
                        Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Icon(
                                    imageVector = Icons.Outlined.Language,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                            )
                            
                            Text(
                                    text = selectedCountry,
                                    fontFamily = iconFonts,
                                    fontSize = 15.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    
                    DropdownMenu(
                            expanded = showCountryBarPage,
                            containerColor = MaterialTheme.colorScheme.onBackground,
                            onDismissRequest = { showCountryBarPage = false }
                    ) {
                        repeat(commen.cuntryList.size) { index ->
                            DropdownMenuItem(
                                    text = {
                                        Text(
                                                text = commen.cuntryList[index],
                                                color = MaterialTheme.colorScheme.background,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                modifier = Modifier
                                                        .padding(horizontal = 8.dp)
                                        )
                                    },
                                    onClick = {
                                        
                                        val selected =
                                            commen.cuntryList[index]
                                        
                                        selectedCountry =
                                            selected
                                        
                                        showCountryBarPage = false
                                        
                                        viewModel.changeCountry(
                                                countryCodeMap[selected]
                                                    ?: "in"
                                        )
                                    }
                            )
                        }
                    }
                    
                }
                
                
                Box {
                    IconButton(
                            onClick = onclick
                    ) {
                        Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Book icon",
                                tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

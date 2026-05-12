package com.example.news.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewsTopBar(){
    var showSearchBarPage by remember { mutableStateOf(false) }
    val font = FontFamily(Font(R.font.cinze))
    val color = Color(0xFF2C5553)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 50.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){

                Icon(
                    painter = painterResource(R.drawable.topbarlogo),
                    contentDescription = "TopBar Logo",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(40.dp)
                )

            Text(
                text = "KHABAR",
                fontFamily = font ,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = TextDecoration.Underline
            )
         }


//            Row(
//                horizontalArrangement = Arrangement.spacedBy(10.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ){
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(shape = CircleShape)
//                    .onBackground(Color.LightGray),
//                contentAlignment = Alignment.Center
//            ){
//            Icon(
//                imageVector = Icons.Default.Person,
//                contentDescription = null
//                )
//            }
//                Text(text = "User name", fontFamily = font, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
//          }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground
                )
                    Text("en", color = MaterialTheme.colorScheme.onBackground)

                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier.size(10.dp),
                        tint =  MaterialTheme.colorScheme.onBackground
                    )

                    Box{

                        IconButton(onClick = {showSearchBarPage =!showSearchBarPage}){
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "menu icon",
                                tint = MaterialTheme.colorScheme.onBackground)
                        }

                    DropdownMenu(
                        expanded = showSearchBarPage,
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        onDismissRequest = {showSearchBarPage = false}
                    ){
                        DropdownMenuItem(
                            text = {Text("Option:- 1", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 2", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 3", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 4", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 5", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 6", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 7", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 8", color = MaterialTheme.colorScheme.background)},
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = {Text("Option:- 9")},
                            onClick = {}
                        )
                    }
                 }


            }
        }
    }
}
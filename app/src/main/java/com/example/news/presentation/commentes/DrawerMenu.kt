package com.example.news.presentation.commentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessibilityNew
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
        onItemClick: (String) -> Unit,
        onClose: () -> Unit,
) {
    val items = listOf(
            menuitems(
                    icon = Icons.Outlined.Home,
                    label = "Home",
                    secondryLabel = null
            ),
            menuitems(
                    icon = Icons.Outlined.TrendingUp,
                    label = "Trending",
                    secondryLabel = null
            ),
            menuitems(
                    icon = Icons.Outlined.Settings,
                    label = "Setting",
                    secondryLabel = null
            ),
         
            menuitems(
                    icon = Icons.Outlined.Help,
                    label = "Help & Support",
                    secondryLabel = null
            ),
            menuitems(
                    icon = Icons.Outlined.AccessibilityNew,
                    label = "Privacy Policy",
                    secondryLabel = null
            )
    )
    
    
    ModalDrawerSheet {
        Text(
                "Khabar Menu",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
        )
        
        items.forEach { item ->
            NavigationDrawerItem(
                    label = { Text(item.label) },
                    selected = false,
                    onClick = {
                        onItemClick("settings")
                        onClose()
                    },
                    icon = {Icon(item.icon,contentDescription = null)}
            )
        }
        
        TextButton(
                onClick = {},
                modifier = Modifier.padding(top = 500.dp)
        ){
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
            ){
            Text(
                    text = "Logout",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Monospace,
                    
            )
                Icon(
                        imageVector = Icons.Outlined.ArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(20.dp)
                )
            }
        }
        
    }
}

data class menuitems(
        val icon: ImageVector,
        val label: String,
        val secondryLabel: String?,
)
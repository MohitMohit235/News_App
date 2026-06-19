package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.navigation.navGraph.NavGraph
import com.example.news.presentation.ui.theme.NewsTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            NewsTheme {
                val navController = rememberNavController()
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)){
                  // MainNewsScreen(navController = navController)
                   // NewsSearchBar()
                  //  SinghUpScreen()
                    NavGraph()
                }
            }
        }
    }
}


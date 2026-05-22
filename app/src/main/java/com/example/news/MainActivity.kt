package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.news.presentation.animationEffects.ShimmerEffect
import com.example.news.presentation.authentication.SinghUpScreen
import com.example.news.presentation.common.NewsCard
import com.example.news.presentation.common.NewsSearchBar
import com.example.news.presentation.navigation.navGraph.NavGraph
import com.example.news.presentation.onbording.MainNewsScreen
import com.example.news.presentation.onbording.OnBordScreen
import com.example.news.ui.theme.NewsTheme
import com.google.firebase.Firebase
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


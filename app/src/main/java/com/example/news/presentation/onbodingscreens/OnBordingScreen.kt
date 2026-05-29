package com.example.news.presentation.onbodingscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.news.R
import com.example.news.presentation.viewmodel.AuthenticationViewModel

@SuppressLint("RememberReturnType")
@Composable
fun OnBordScreen(
        onNextClick: () -> Unit,
        viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    
    val Backgroundcomposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.back2))
    
    
    val progress by animateLottieCompositionAsState(
            composition = Backgroundcomposition,
            iterations = LottieConstants.IterateForever
    )
    
    Box(
            modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
    ) {
        
        LottieAnimation(
                composition = Backgroundcomposition,
                modifier = Modifier
                        .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                progress = { progress }
        )
        
        
        Column(
                modifier = Modifier
                        .padding(bottom = 100.dp)
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            
            Text(
                    text = "KHABAR",
                    fontFamily = viewModel.font2,
                    color = Color.White,
                    fontSize = 40.sp,
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                    text = "Stay connected to the world with " +
                            "the latest news, anytime, anywhere.",
                    maxLines = 3,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(horizontal = 10.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Button(
                    onClick = onNextClick,
                    modifier = Modifier
                            .width(200.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                    )
            ) {
                Text(
                        text = "Get Start",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = viewModel.font1
                )
            }
        }
    }
}
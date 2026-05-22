package com.example.news.presentation.viewmodel

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import com.example.news.R

class AuthenticationViewModel: ViewModel() {
    var email by  mutableStateOf("")
    var password by  mutableStateOf("")
    var passwordHide by  mutableStateOf(true)
    val font = FontFamily(Font(R.font.jrfonts))
    
    @Composable
    fun buttonClor() = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
    )
}


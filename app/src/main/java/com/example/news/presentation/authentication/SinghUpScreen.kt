package com.example.news.presentation.authentication

import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.presentation.viewmodel.AuthenticationViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun SinghUpScreen(
        onSuccessSignup: () -> Unit,
        authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
        navController: NavController,
) {
    
    val keybordController = LocalSoftwareKeyboardController.current
    
    var startingAnimation by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true){
        startingAnimation = true
    }
    
    val animationProgress by animateFloatAsState(
            targetValue = if (startingAnimation) 0f else 1f,
            animationSpec = tween(
                    durationMillis = 800,
                    easing = FastOutSlowInEasing
            ),
            label = "animation"
    )
    
    Box(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(Color(0xFFF7655A)),
            contentAlignment = Alignment.Center
    ) {
        
        Canvas(
                modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
        ) {
            
            val screenSize = size.height
            
            val position = screenSize * animationProgress
            
            withTransform({
                translate(left  = 0F)
                rotate(degrees = 50F)
            }) {
                drawRect(
                        color = Color.White,
                        topLeft = Offset(x = 0F, y = position),
                        size = size*1.1F,
                )
            }
        }
        
        Column(
                modifier = Modifier
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                    modifier = Modifier
                            .fillMaxWidth(),
                    contentAlignment = Alignment.Center
            ) {
                Column(
                        modifier = Modifier
                                .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                            text = "SignUp",
                            fontSize = 30.sp,
                            fontFamily = authenticationViewModel.font1,
                            modifier = Modifier.padding(12.dp)
                    )
                    
                    Spacer(Modifier.height(20.dp))
                    
                    OutlinedTextField(
                            modifier = Modifier,
                            value = authenticationViewModel.email,
                            onValueChange = { authenticationViewModel.email = it },
                            label = {
                                Text(
                                        text = "Enter email",
                                        style = TextStyle(
                                                fontFamily = authenticationViewModel.font1
                                        ),
                                        color = if (authenticationViewModel.email.isBlank()) Color.Black else Color.Black
                                )
                            },
                            trailingIcon = if (authenticationViewModel.email.isNotEmpty()) {
                                {
                                    IconButton(onClick = { authenticationViewModel.email = " " }) {
                                        Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = null
                                        )
                                    }
                                }
                            } else null,
                            supportingText = {
                                if (authenticationViewModel.email.isNotEmpty())
                                    Text(
                                            text = "please enter your valid email",
                                            style = MaterialTheme.typography.bodySmall,
                                            fontFamily = authenticationViewModel.font1
                                    ) else null
                            },
                            
                            colors = authenticationViewModel.buttonClor(),
                            keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                    onDone = { keybordController?.hide() }
                            ),
                            singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(25.dp))
                    
                    OutlinedTextField(
                            modifier = Modifier,
                            value = authenticationViewModel.password,
                            onValueChange = { authenticationViewModel.password = it },
                            label = {
                                Text(
                                        text = "Enter password",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontFamily = authenticationViewModel.font1,
                                        color = if (authenticationViewModel.password.isBlank()) Color.Black else Color.Black
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Black,
                                    unfocusedBorderColor = Color.Black,
                            ),
                            keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Done
                            ),
                            singleLine = true,
                            visualTransformation = if (authenticationViewModel.passwordHide) {
                                PasswordVisualTransformation()
                            } else VisualTransformation.None,
                            trailingIcon = {
                                val visibleIcon = if (authenticationViewModel.passwordHide) {
                                    painterResource(id = R.drawable.hide)
                                } else painterResource(R.drawable.show)
                                IconButton(onClick = {
                                    authenticationViewModel.passwordHide =
                                        !authenticationViewModel.passwordHide
                                }
                                ) {
                                    Icon(
                                            painter = visibleIcon,
                                            contentDescription = null,
                                            modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                    )
                    
                    Spacer(modifier = Modifier.height(25.dp))
                    val context = LocalContext.current
                    
                    Button(
                            onClick = {
                                
                                when {
                                    authenticationViewModel.email.isBlank() && authenticationViewModel.password.isBlank() -> {
                                        Toast.makeText(
                                                context,
                                                "Please enter email and password",
                                                Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    
                                    authenticationViewModel.email.isBlank() -> {
                                        Toast.makeText(
                                                context,
                                                "Please enter email ",
                                                Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    
                                    
                                    authenticationViewModel.password.isBlank() -> {
                                        Toast.makeText(
                                                context,
                                                "Please enter password",
                                                Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    
                                    else -> {
                                        Firebase.auth.createUserWithEmailAndPassword(
                                                authenticationViewModel.email.trim(),
                                                authenticationViewModel.password.trim()
                                        )
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        Toast.makeText(
                                                                context,
                                                                "Signup Successfully! Please log in.",
                                                                Toast.LENGTH_SHORT
                                                        ).show()
                                                        onSuccessSignup()
                                                    } else {
                                                        Toast.makeText(
                                                                context,
                                                                task.exception?.message ?: "signup",
                                                                Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                    }
                                }
                            },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF7655A)
                            )
                    ) {
                        Text(
                                text = "Sign-Up",
                                fontSize = 15.sp,
                                fontFamily = authenticationViewModel.font1
                        )
                    }
                    
                    TextButton(
                            onClick = {
                                onSuccessSignup()
                            }
                    ) {
                        Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                            style = SpanStyle(
                                                    color = Color.Black,
                                                    fontFamily = authenticationViewModel.font1
                                            )
                                    ) {
                                        append("Already have an account? ")
                                    }
                                    withStyle(
                                            style = SpanStyle(
                                                    color = Color(0xFFF7655A),
                                                    textDecoration = TextDecoration.Underline,
                                                    fontFamily = authenticationViewModel.font1
                                            )
                                    ) {
                                        append("Log in")
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}
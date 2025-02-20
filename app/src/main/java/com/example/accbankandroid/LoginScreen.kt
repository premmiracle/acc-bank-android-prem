package com.example.accbankandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.example.accbankandroid.ui.theme.logintheme
import com.example.accbankandroid.ui.theme.loginlight

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var keepLoggedIn by remember { mutableStateOf(false) }

    // Detect Screen Orientation
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    //  Remember scroll state for vertical scrolling
    val scrollState = rememberScrollState()

    //  Use BoxWithConstraints to dynamically adjust UI
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .padding(if (isLandscape) 32.dp else 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = if (isLandscape) 100.dp else 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  QUOTE SECTION
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "QUOTE OF THE DAY",
                    fontSize = if (isLandscape) 16.sp else 18.sp,
                    letterSpacing = 5.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Money isn’t everything,\n" +
                            "but everything needs\n" +
                            "money.",
                    fontSize = if (isLandscape) 24.sp else 28.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = if (isLandscape) 28.sp else 30.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                //  Underline Below the Quote
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(3.dp)
                        .background(Color.White.copy(alpha = 0.5f))
                )
            }

            //  Adjust Spacing Based on Orientation
            Spacer(modifier = Modifier.height(if (isLandscape) 50.dp else 100.dp))

            //  Glassmorphic Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedBorderColor = Color.White.copy(alpha = 0.9f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    containerColor = Color.White.copy(alpha = 0.1f)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Glassmorphic Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedBorderColor = Color.White.copy(alpha = 0.9f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    containerColor = Color.White.copy(alpha = 0.1f)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Left-Aligned Toggle Switch (Keep me logged in)
            Row(
                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = keepLoggedIn,
                    onCheckedChange = { keepLoggedIn = it },
                    modifier = Modifier.scale(if (isLandscape) 0.7f else 0.8f),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = loginlight.copy(alpha = 0.4f),
                        uncheckedTrackColor = Color.Transparent,
                        disabledCheckedTrackColor = Color.Transparent,
                        disabledUncheckedTrackColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Keep me logged in",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign In Button (Responsive)
            Button(
                onClick = { /* Handle Login */ },
                modifier = Modifier
                    .fillMaxWidth(if (isLandscape) 0.7f else 1f)
                    .height(if (isLandscape) 45.dp else 50.dp),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(if (isLandscape) 50.dp else 100.dp))

            //  Bottom Line
            Box(
                modifier = Modifier
                    .width(if (isLandscape) 200.dp else 300.dp)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.5f))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}

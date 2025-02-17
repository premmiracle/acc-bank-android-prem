package com.example.newtempactivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newtempactivity.ui.theme.getGradientBrush
import com.example.newtempactivity.ui.theme.logintheme
import com.example.newtempactivity.ui.theme.loginlight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var keepLoggedIn by remember { mutableStateOf(false) }
    // ✅ Remember scroll state for vertical scrolling
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush()) // Apply new gradient
            .padding(16.dp),
            contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState) // ✅ Enables scrolling
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  QUOTE SECTION
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // "QUOTE OF THE DAY" (Smaller & Lighter)
                Text(
                    text = "QUOTE OF THE DAY",
                    fontSize = 18.sp,
                    letterSpacing = 5.sp, // Adds better spacing
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                //  Quote with Proper Alignment
                Text(
                    text = "Money isn’t everything,\n" +
                            "but everything needs\n" +
                            "money.",
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp, // Ensures correct line spacing
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp) // Adds margin for better centering
                )

                //  Underline Below the Quote
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .width(50.dp) // Underline length
                        .height(3.dp) // Thickness
                        .background(Color.White.copy(alpha = 0.5f))
                )
            }


            Spacer(modifier = Modifier.height(150.dp))
//comment
            //  Glassmorphic Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    ,
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
                modifier = Modifier
                .fillMaxWidth()
                    ,
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

            // Left-Aligned Toggle Switch (Keep me logged in)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = keepLoggedIn,
                    onCheckedChange = { keepLoggedIn = it },
                    modifier = Modifier
                        .scale(0.8f), // Increases switch size
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White, // Large White Thumb
                        uncheckedThumbColor = Color.White, // Thumb remains white when OFF
                        checkedTrackColor = loginlight.copy(alpha = 0.4f), //  Soft glow effect when ON
                        uncheckedTrackColor = Color.Transparent, //  No gray border when OFF
                        disabledCheckedTrackColor = Color.Transparent, //  No border when disabled & ON
                        disabledUncheckedTrackColor = Color.Transparent //  No border when disabled & OFF
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

            // ✅ Sign In Button (White, Rounded, Elevation)
            Button(
                onClick = { /* Handle Login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp), // Fully rounded
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Sign In", fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(100.dp))
            Box(
                modifier = Modifier
                    .width(300.dp) // Underline length
                    .height(2.dp) // Thickness
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

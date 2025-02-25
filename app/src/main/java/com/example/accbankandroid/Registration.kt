package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.accbankandroid.ui.theme.getGradientBrush
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isRegistered by remember { mutableStateOf(false) }
    var isRegistering by remember { mutableStateOf(false) } // State for showing loading indicator

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
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registration",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // User ID/username TextField
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("User ID/username") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* Action to remember username */ }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_dialog_info), contentDescription = "Info", tint = Color.White)
                    }
                },
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

            // Password TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_view), contentDescription = "Toggle Password Visibility", tint = Color.White)
                    }
                },
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

            // Confirm Password TextField
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_view), contentDescription = "Toggle Password Visibility", tint = Color.White)
                    }
                },
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
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Login!",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.clickable {
                        // Navigate to the Registration screen
                        navController.navigate(NavigationRoutes.Login   .route)
                    }
                )
            }

            // Error Message Display
            if (errorMessage.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Register Button
            Button(
                onClick = {
                    if (username.isEmpty()) {
                        errorMessage = "Email cannot be empty"
                    } else if (password.isEmpty() || confirmPassword.isEmpty()) {
                        errorMessage = "Password cannot be empty"
                    } else if (password != confirmPassword) {
                        errorMessage = "Password and Confirm password do not match"
                    } else if (!isValidPassword(password)) {
                        errorMessage = "Password must be at least 10 characters, with 1 letter, 1 special character, 1 number, 1 uppercase, and 1 lowercase"
                    } else {
                        errorMessage = "" // Clear error when valid
                        isRegistering = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = "Register", fontSize = 18.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(100.dp))

            // Show loading spinner when registering
            if (isRegistering) {
                CircularProgressIndicator(color = Color.White)
            }

            // Handle navigation after registration
            LaunchedEffect(isRegistering) {
                if (isRegistering) {
                    delay(2000) // Simulate delay for loading spinner
                    isRegistering = false
                    navController.navigate(NavigationRoutes.PhoneNumberInputScreen.route)
                }
            }
        }
    }
}

// Password validation function
fun isValidPassword(password: String): Boolean {
    val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])[A-Za-z\\d!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{10,}\$".toRegex()
    return password.matches(regex)
}
@Preview(showBackground = true)
@Composable
fun Registerview() {
    val navController = rememberNavController() // Mock NavController for preview
    RegistrationScreen(navController)
}

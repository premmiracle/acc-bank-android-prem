package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.accbankandroid.ui.theme.getGradientBrush
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailOTPValidationScreen(navController: NavController) {
    var emailOTP by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isValidOTP by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) } // Loading state

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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter OTP sent to your Email",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = emailOTP,
                onValueChange = { emailOTP = it },
                label = { Text("OTP") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* Action to remember username */ }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_dialog_email), contentDescription = "Info", tint = Color.White)
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

            Button(
                onClick = {
                    if (emailOTP == "123456") { // Assuming OTP validation logic here
                        errorMessage = ""
                        isLoading = true // Show loading before navigating
                        // Simulate a delay to show the loading spinner before navigation
                        isValidOTP = true
                    } else {
                        errorMessage = "Invalid OTP"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp), // Fully rounded
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = if (isValidOTP) "Resend OTP" else "Send OTP",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            // Show loading indicator while validating OTP
            if (isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(color = Color.White)
            }

            // Navigate to the login screen after OTP validation with delay to prevent flickering
            LaunchedEffect(isValidOTP) {
                if (isValidOTP) {
                    delay(1500) // Slight delay to show the loading state
                    navController.navigate(NavigationRoutes.Login.route)
                }
            }

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, fontSize = 16.sp)
            }
        }
    }
}

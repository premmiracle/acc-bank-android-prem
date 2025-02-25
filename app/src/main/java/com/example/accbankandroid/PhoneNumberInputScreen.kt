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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.CommonComponents.BottomNavigation
import com.example.accbankandroid.ui.theme.getGradientBrush
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun PhoneNumberInputScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("+1") }  // Default country code (e.g., USA)
    var otpSent by remember { mutableStateOf(false) }
    var otp by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var Isvarified by remember { mutableStateOf(false) }
    // ✅ Remember scroll state for vertical scrolling
    //val scrollState = rememberScrollState()
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
                //.verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter PhoneNumber",
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
            // Phone number and country code input
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone), // Set numeric keyboard
                trailingIcon = {
                    IconButton(onClick = { /* Action to remember username */ }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_call), contentDescription = "Info", tint = Color.White)
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

            // Send OTP Button
            Button(
                onClick = {
                    if (phoneNumber.isEmpty()) {
                        errorMessage = "Phone number cannot be empty"
                    } else {
                        // Logic to send OTP
                        otpSent = true
                        errorMessage = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp), // Fully rounded
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = if (otpSent) "Resend OTP" else "Send OTP",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            if (otpSent) {
                Spacer(modifier = Modifier.height(16.dp))

                // OTP Input Field
                OutlinedTextField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = { Text("Enter OTP") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (otp == "123456") {  // Assuming OTP validation logic here
                            errorMessage = ""
                           Isvarified=true
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
                    Text(text = "Verify OTP", fontSize = 18.sp, color = Color.Black)
                }



                if (Isvarified) {
                    CircularProgressIndicator(color = Color.White)
                }

                // Handle navigation after registration
                LaunchedEffect(Isvarified) {
                    if (Isvarified) {
                        delay(2000) // Simulate delay for loading spinner
                        Isvarified = false
                        navController.navigate(NavigationRoutes.EmailOTPValidationScreen.route)
                    }
                }
//                if(Isvarified){
//                    navController.navigate(NavigationRoutes.EmailOTPValidationScreen.route)
//                }
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red, fontSize = 16.sp)
                }
            }
        }
    }
}


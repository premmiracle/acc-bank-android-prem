package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.getGradientBrush
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailOTPValidationScreen(navController: NavController) {
//    var otpValues by remember { mutableStateOf(Array(6) { "" }) }
    val otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    var errorMessage by remember { mutableStateOf("") }
    var isValidOTP by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) } // Loading state

    // LocalFocusManager to handle focus transitions
    val focusManager = LocalFocusManager.current

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            // OTP input fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(1.dp), // Adjust the spacing between boxes
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in otpValues.indices) {
                    OutlinedTextField(
                        value = otpValues[i],
//                        onValueChange = { newValue ->
//                            if (newValue.length <= 1) {
//                                otpValues[i] = newValue
//                                // Move focus to the next field if the OTP digit is entered
//                                if (newValue.isNotEmpty() && i < otpValues.size - 1) {
//                                    focusManager.moveFocus(FocusDirection.Next)
//                                }
//                            }
//                        },
                        onValueChange = { newValue ->
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                if (newValue.isEmpty() && otpValues[i].isNotEmpty()) {
                                    otpValues[i] = ""
                                    if (i > 0) focusManager.moveFocus(FocusDirection.Previous)
                                } else {
                                    otpValues[i] = newValue
                                    if (i < otpValues.size - 1) {
                                        focusManager.moveFocus(FocusDirection.Next)
                                    }

                                }
                            }
                        },
                                label = { Text("") },
                        modifier = Modifier
                            .weight(1f) // Makes each box flexible and fit in the screen
                            .height(70.dp) // Increase height for better visibility
                            .padding(3.dp), // Add padding for better layout
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone), // Set numeric keyboard
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.6f),
                            containerColor = Color.White.copy(alpha = 0.1f),
                            focusedTextColor = Color.White,  // Ensure text color is white
                            unfocusedTextColor = Color.White // Ensure text color is white when unfocused
                        ),
                        textStyle = LocalTextStyle.current.copy(fontSize = 24.sp, color = Color.White) // Larger font size
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val enteredOTP = otpValues.joinToString("")
                    if (enteredOTP == "123456") { // Assuming OTP validation logic here
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


@Preview(showBackground = true)
@Composable
fun EmailOTPValidationScreenPreview() {
    val navController = rememberNavController()
    EmailOTPValidationScreen(navController)
}

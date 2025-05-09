package com.example.accbankandroid

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.CommonComponents.TopBarLogo
import com.example.accbankandroid.ui.theme.getGradientBrush
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.accbankandroid.network.ApiClient
import retrofit2.http.Header

// API Models and Interface
data class OtpVerifyRequest(val otp: String, val token: String)
data class OtpVerifyResponse(
    val token: String,
    val name: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val status: String,
    val message: String,
    val statusCode: Int
)
interface OtpApi {
    @POST("auth/otp-verify")
    suspend fun verifyOtp(
        @Body request: OtpVerifyRequest,
        @Header("Authorization") bearerToken: String
    ): Response<OtpVerifyResponse>
}


//val otpApi = Retrofit.Builder()
//    .baseUrl("https://acceinfoapi-cga0hmcdazb5hjbs.eastus2-01.azurewebsites.net/api/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//    .create(OtpApi::class.java)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginOtpScreen(token: String, navController: NavHostController) {
    val context = LocalContext.current
    val focusRequesters = List(6) { remember { FocusRequester() } }

    var otpValues by remember { mutableStateOf(List(6) { "" }) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var startVerification by remember { mutableStateOf(false) }

    // UI Layout
Box(
    modifier = Modifier
        .fillMaxSize()
        .background(getGradientBrush()) // Apply new gradient
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarLogo()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getGradientBrush())
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Enter OTP", fontSize = 20.sp, color = Color.White)
            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { new ->
                            if (new.length <= 1 && new.all { it.isDigit() }) {
                                otpValues = otpValues.toMutableList().also { it[index] = new }
                                if (new.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(48.dp)
                            .focusRequester(focusRequesters[index]),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 18.sp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                            containerColor = Color.White.copy(alpha = 0.1f)
                        )
                    )
                }
            }

            LaunchedEffect(Unit) {
                focusRequesters[0].requestFocus()
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (otpValues.all { it.isNotBlank() }) {
                        errorMessage = null
                        startVerification = true
                    } else {
                        errorMessage = "Please enter all 6 digits"
                    }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(20.dp))
                } else {
                    Text("Verify OTP", color = Color.Black)
                }
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = Color.Red, fontSize = 14.sp)
            }
        }
    }
}


    // ✅ Triggered only when startVerification = true
    LaunchedEffect(startVerification) {
        if (startVerification) {
            isLoading = true
            try {
                val fullOtp = otpValues.joinToString("")
                val response = ApiClient.otpApi.verifyOtp(
                    OtpVerifyRequest(fullOtp, token),
                    bearerToken = "Bearer $token"
                )

                if (response.isSuccessful && response.body()?.statusCode == 200) {
                    // Navigate only on confirmed login status
                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
                        popUpTo(NavigationRoutes.LoginOtpScreen.route) { inclusive = true }
                    }
                }
                else {
                    errorMessage = response.body()?.message ?: "Invalid OTP"
                }
            } catch (e: Exception) {
                errorMessage = "Network error: ${e.localizedMessage}"
            } finally {
                isLoading = false
                startVerification = false
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginOtpScreenPreview() {
    val navController = rememberNavController()
    val mockToken = "mock_token_sample"
    LoginOtpScreen(token = mockToken, navController = navController)
}

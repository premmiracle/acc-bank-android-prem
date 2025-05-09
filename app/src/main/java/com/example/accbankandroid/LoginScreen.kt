////////////////////new api consumed for the login//////////////////////
//package com.example.accbankandroid
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.biometric.BiometricManager
//import androidx.biometric.BiometricPrompt
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.fragment.app.FragmentActivity
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.ui.theme.getGradientBrush
//import com.example.accbankandroid.ui.theme.loginlight
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.concurrent.Executor
//import java.util.concurrent.Executors
//
//@SuppressLint("UnusedBoxWithConstraintsScope")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreen(navController: NavHostController) {
//    val context = LocalContext.current
//    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//
//    // Detect Screen Orientation
//    val configuration = LocalConfiguration.current
//    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
//    //  Remember scroll state for vertical scrolling
//    val scrollState = rememberScrollState()
//    //   User Login State
//    var isFirstTimeLogin by remember { mutableStateOf(sharedPreferences.getBoolean("isFirstTimeLogin", true)) }
//    var isBiometricEnabled by remember { mutableStateOf(sharedPreferences.getBoolean("isBiometricEnabled", false)) }
//    var showEnableBiometricDialog by remember { mutableStateOf(false) }
//
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var keepLoggedIn by remember { mutableStateOf(false) }
//    var isAuthenticated by remember { mutableStateOf(false) }
//
//    val biometricManager = BiometricManager.from(context)
//    val executor: Executor = Executors.newSingleThreadExecutor()
//
//    //   Biometric Authentication Setup
//    val biometricPrompt = BiometricPrompt(
//        context as FragmentActivity,
//        executor,
//        object : BiometricPrompt.AuthenticationCallback() {
//            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                super.onAuthenticationSucceeded(result)
//                CoroutineScope(Dispatchers.Main).launch {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
//                }
//            }
//
//            override fun onAuthenticationFailed() {
//                super.onAuthenticationFailed()
//            }
//
//            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                super.onAuthenticationError(errorCode, errString)
//            }
//        }
//    )
//
//    val promptInfo = BiometricPrompt.PromptInfo.Builder()
//        .setTitle("Biometric Login")
//        .setSubtitle("Log in using your fingerprint")
//        .setNegativeButtonText("Cancel")
//        .build()
//
//    //   Auto trigger biometric login if enabled (but only if not first time)
//    LaunchedEffect(Unit) {
//        if (!isFirstTimeLogin && isBiometricEnabled && biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
//            biometricPrompt.authenticate(promptInfo)
//        }
//    }
//
//    //   Navigate after successful authentication
//    if (isAuthenticated) {
//        navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
//    }
//
//    //   UI Layout
//    BoxWithConstraints(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(getGradientBrush())
//            .padding(if (isLandscape) 32.dp else 16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(scrollState)
//                .padding(horizontal = if (isLandscape) 100.dp else 20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            //  QUOTE SECTION
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "QUOTE OF THE DAY",
//                    fontSize = if (isLandscape) 16.sp else 18.sp,
//                    letterSpacing = 5.sp,
//                    color = Color.White.copy(alpha = 0.9f),
//                    style = MaterialTheme.typography.labelMedium
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = "Money isn’t everything,\n" +
//                            "but everything needs\n" +
//                            "money.",
//                    fontSize = if (isLandscape) 24.sp else 28.sp,
//                    textAlign = TextAlign.Center,
//                    lineHeight = if (isLandscape) 28.sp else 30.sp,
//                    color = Color.White,
//                    style = MaterialTheme.typography.headlineSmall,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//
//                //  Underline Below the Quote
//                Spacer(modifier = Modifier.height(12.dp))
//                Box(
//                    modifier = Modifier
//                        .width(50.dp)
//                        .height(3.dp)
//                        .background(Color.White.copy(alpha = 0.5f))
//                )
//            }
//            //   Show biometric login option if biometrics are enabled
//            if (!isFirstTimeLogin && isBiometricEnabled) {
//                Text(
//                    text = "Welcome Back, $email",
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    textAlign = TextAlign.Center
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Button(
//                    onClick = { biometricPrompt.authenticate(promptInfo) },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(50.dp)
//                ) {
//                    Text(text = "Continue with Biometrics", fontSize = 18.sp)
//                }
//                return@Column
//            }
//            //  Adjust Spacing Based on Orientation
//            Spacer(modifier = Modifier.height(if (isLandscape) 50.dp else 100.dp))
//
//            //   Email & Password Fields
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                singleLine = true,
//                shape = RoundedCornerShape(24.dp),
//                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedTextColor = Color.White,
//                    unfocusedTextColor = Color.White,
//                    unfocusedLabelColor = Color.White,
//                    focusedLabelColor = Color.White,
//                    focusedBorderColor = Color.White.copy(alpha = 0.9f),
//                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
//                    containerColor = Color.White.copy(alpha = 0.1f)
//                )
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                singleLine = true,
//                shape = RoundedCornerShape(24.dp),
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedTextColor = Color.White,
//                    unfocusedTextColor = Color.White,
//                    unfocusedLabelColor = Color.White,
//                    focusedLabelColor = Color.White,
//                    focusedBorderColor = Color.White.copy(alpha = 0.9f),
//                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
//                    containerColor = Color.White.copy(alpha = 0.1f)
//                )
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            //Left-Aligned Toggle Switch (Keep me logged in)
//            Row(
//                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Switch(
//                    checked = keepLoggedIn,
//                    onCheckedChange = { keepLoggedIn = it },
//                    modifier = Modifier.scale(if (isLandscape) 0.7f else 0.8f),
//                    colors = SwitchDefaults.colors(
//                        checkedThumbColor = Color.White,
//                        uncheckedThumbColor = Color.White,
//                        checkedTrackColor = loginlight.copy(alpha = 0.4f),
//                        uncheckedTrackColor = Color.Transparent,
//                        disabledCheckedTrackColor = Color.Transparent,
//                        disabledUncheckedTrackColor = Color.Transparent
//                    )
//                )
//
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = "Keep me logged in",
//                    fontSize = 14.sp,
//                    color = Color.White.copy(alpha = 0.8f)
//                )
//            }
//
////            Spacer(modifier = Modifier.height(5.dp))
//            Row(
//                modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Need to open an account?",
//                    fontSize = 14.sp,
//                    color = Color.White.copy(alpha = 0.8f)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = "Register!",
//                    fontSize = 14.sp,
//                    color = Color.White.copy(alpha = 0.8f),
//                    modifier = Modifier.clickable {
//                        // Navigate to the Registration screen
//                        navController.navigate(NavigationRoutes.Registration.route)
//                    }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            //   Sign-In Button
//            Button(
//                onClick = {
//                    if (isFirstTimeLogin) {
//                        sharedPreferences.edit().putBoolean("isFirstTimeLogin", false).apply()
//                        showEnableBiometricDialog = true
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth(if (isLandscape) 0.7f else 1f)
//                    .height(if (isLandscape) 45.dp else 50.dp),
//                shape = RoundedCornerShape(50.dp),
//                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//            ) {
//                Text(
//                    text = "Sign In",
//                    fontSize = 18.sp,
//                    color = Color.Black
//                )
//            }
//            Spacer(modifier = Modifier.height(if (isLandscape) 50.dp else 100.dp))
//
//            //  Bottom Line
//            Box(
//                modifier = Modifier
//                    .width(if (isLandscape) 200.dp else 300.dp)
//                    .height(2.dp)
//                    .background(Color.White.copy(alpha = 0.5f))
//            )
//        }
//    }
//
//    //   Enable Biometric Popup
//    // ✅ Ensure the dialog remains visible until dismissed
//    if (showEnableBiometricDialog) {
//        LaunchedEffect(Unit) {
//            // Ensure the state remains unchanged while the dialog is open
//            showEnableBiometricDialog = true
//        }
//
//        AlertDialog(
//            onDismissRequest = { showEnableBiometricDialog = false },
//            title = { Text("Enable Biometrics?") },
//            text = { Text("Would you like to enable biometric login for faster access next time?") },
//            confirmButton = {
//                Button(onClick = {
//                    sharedPreferences.edit().putBoolean("isBiometricEnabled", true).apply()
//                    showEnableBiometricDialog = false
//
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
//                }) {
//                    Text("Enable")
//                }
//            },
//            dismissButton = {
//                Button(onClick = { showEnableBiometricDialog = false }) {
//                    Text("No, Thanks")
//                }
//            }
//        )
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    val navController = rememberNavController()
//    LoginScreen(navController)
//}
package com.example.accbankandroid

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.example.accbankandroid.ui.theme.loginlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import androidx.compose.runtime.SideEffect
import com.example.accbankandroid.CommonComponents.TopBarLogo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

///////////////
import com.example.accbankandroid.network.ApiService
import com.example.accbankandroid.network.LoginRequest

// API Models and Service

data class LoginRequest(val username: String, val password: String, val type: String = "customer", val refreshToken: String = "")
data class LoginResponse(
    val token: String,
    val name: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val status: String,
    val message: String,
    val statusCode: Int
)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

object ApiClient {
    val authApi: AuthApi = Retrofit.Builder()
        .baseUrl("https://acceinfoapi-cga0hmcdazb5hjbs.eastus2-01.azurewebsites.net/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf(sharedPreferences.getString("username", "") ?: "") }
    var password by remember { mutableStateOf("") }
    var keepLoggedIn by remember { mutableStateOf(sharedPreferences.getBoolean("keepLoggedIn", false)) }
    var isBiometricEnabled by remember { mutableStateOf(sharedPreferences.getBoolean("isBiometricEnabled", false)) }
    var isFirstTimeLogin by remember { mutableStateOf(sharedPreferences.getBoolean("isFirstTimeLogin", true)) }
    var showEnableBiometricDialog by remember { mutableStateOf(false) }
    var dontAskBiometricAgain by remember { mutableStateOf(sharedPreferences.getBoolean("dontAskBiometricAgain", false)) }

    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }

    val biometricManager = BiometricManager.from(context)
    val executor: Executor = Executors.newSingleThreadExecutor()

    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                CoroutineScope(Dispatchers.Main).launch {
                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
                }
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Login")
        .setSubtitle("Log in using your fingerprint")
        .setNegativeButtonText("Cancel")
        .build()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        // Set status bar color to match TopBarLogo (white)
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true // Text and icons will be dark for visibility
        )

        // Set navigation bar transparent
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = true // Change to false if your app background is dark at bottom
        )
    }


    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .windowInsetsPadding(WindowInsets.statusBars), // ensures space for status bar
    ) {
        Column {
        TopBarLogo()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(if (isLandscape) 32.dp else 16.dp)
                    .padding(horizontal = if (isLandscape) 100.dp else 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Spacer(modifier = Modifier.height(if (isLandscape) 50.dp else 100.dp))
                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        loginError = null
                    },
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

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        loginError = null
                    },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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

                // Keep me logged in
                Row(
                    modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = keepLoggedIn,
                        onCheckedChange = {
                            keepLoggedIn = it
                            sharedPreferences.edit().putBoolean("keepLoggedIn", it).apply()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Keep me logged in", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                }

                // Register
                Row(
                    modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Need to open an account?",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Register!",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.clickable {
                            navController.navigate(NavigationRoutes.Registration.route)
                        })
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sign In
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val loginRequest = LoginRequest(username = email, password = password)
                                val response = ApiClient.authApi.login(loginRequest)

                                if (response.status == "Success") {
                                    if (keepLoggedIn) {
                                        sharedPreferences.edit().putString("username", response.name)
                                            .apply()
                                    }
                                    sharedPreferences.edit().putBoolean("isFirstTimeLogin", false)
                                        .apply()
                                    sharedPreferences.edit().putString("token", response.token).apply()
                                    loginError = null
                                    showEnableBiometricDialog = true

                                    withContext(Dispatchers.Main) {
                                        // 👇 Redirect to OTP screen after login via password
    //                                    navController.navigate("loginOtpScreen/${response.token}")
                                        navController.navigate(
                                            NavigationRoutes.LoginOtpScreen.createRoute(
                                                response.token
                                            )
                                        )
                                    }
                                } else {
                                    withContext(Dispatchers.Main) {
                                        loginError = "Invalid login details"
                                    }
                                }
                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    loginError = "Network error: ${e.localizedMessage}"
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f).height(50.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Sign In", fontSize = 18.sp, color = Color.Black)
                }

                // Error
                if (loginError != null) {
                    Text(
                        text = loginError ?: "",
                        color = Color(0xFFFF6B6B),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Biometric Button
                if (isBiometricEnabled && biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { biometricPrompt.authenticate(promptInfo) },
                        modifier = Modifier.fillMaxWidth(if (isLandscape) 0.7f else 1f),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
                    ) {
                        Text("Continue with Biometrics", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .width(if (isLandscape) 200.dp else 300.dp)
                        .height(2.dp)
                        .background(Color.White.copy(alpha = 0.5f))
                )
            }
        }
    }

    // Biometric Dialog
    if (showEnableBiometricDialog && !dontAskBiometricAgain) {
        var dontAskAgainChecked by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { showEnableBiometricDialog = false },
            title = { Text("Enable Biometrics?") },
            text = {
                Column {
                    Text("Would you like to enable biometric login for faster access next time?")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = dontAskAgainChecked,
                            onCheckedChange = { dontAskAgainChecked = it }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Don't ask again")
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    sharedPreferences.edit().putBoolean("isBiometricEnabled", true).apply()
                    if (dontAskAgainChecked) {
                        sharedPreferences.edit().putBoolean("dontAskBiometricAgain", true).apply()
                    }
                    showEnableBiometricDialog = false
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
                }) {
                    Text("Enable")
                }
            },
            dismissButton = {
                Button(onClick = {
                    sharedPreferences.edit().putBoolean("isBiometricEnabled", false).apply()
                    if (dontAskAgainChecked) {
                        sharedPreferences.edit().putBoolean("dontAskBiometricAgain", true).apply()
                    }
                    showEnableBiometricDialog = false
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route)
                }) {
                    Text("No, Thanks")
                }
            }
        )
    }
}
@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    // Put only layout-related code here
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        email = "preview@example.com",
        password = "••••••••",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}



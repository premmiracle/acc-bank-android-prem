package com.example.accbankandroid

//import android.hardware.biometrics.BiometricPrompt
import androidx.biometric.BiometricPrompt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.accbankandroid.SendMoney

import com.example.accbankandroid.ui.theme.AccBankAndroidTheme
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//class MainActivity : FragmentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AccBankAndroidTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//
//                    // Define the NavHost with a start destination
//                    NavHost(
//                        navController = navController,
//                        startDestination = NavigationRoutes.Login.route, // Initial screen
//                    ) {
//                        // Sign-In Screen
//                        composable(NavigationRoutes.Login.route) {
//                            LoginScreen(navController)
//                        }
//
//
//
//                        composable(NavigationRoutes.MainScreenWithBottomNav.route) {
//                            MainScreenWithBottomNav(navController)
//                        }
//
//                        composable(NavigationRoutes.MoveMoney.route) {
//                            MoveMoney(navController)
//                        }
//                        composable(NavigationRoutes.SendMoney.route) {
//                            SendMoney(navController)
//                        }
//                        // Account Overview Screen
//                        composable(NavigationRoutes.AccountOverview.route) {
//                            AccountOverview(navController)
//                        }
//                        composable(NavigationRoutes.EmailOTPValidationScreen.route){
//                            EmailOTPValidationScreen(navController)
//                        }
//                        composable(NavigationRoutes.PhoneNumberInputScreen.route){
//                            PhoneNumberInputScreen(navController)
//                        }
//                        composable(NavigationRoutes.Registration.route){
//                            RegistrationScreen(navController)
//                        }
//                    }
//                }
//            }
//        }
////
////        val executor: Executor = Executors.newSingleThreadExecutor()
////
////        val biometricPrompt = BiometricPrompt(
////            this, // 'this' is now FragmentActivity, so BiometricPrompt can work
////            executor,
////            object : BiometricPrompt.AuthenticationCallback() {
////                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
////                    super.onAuthenticationSucceeded(result)
////                    // Handle successful authentication
////                }
////
////                override fun onAuthenticationFailed() {
////                    super.onAuthenticationFailed()
////                    // Handle failed authentication
////                }
////            })
////
////        val promptInfo = BiometricPrompt.PromptInfo.Builder()
////            .setTitle("Biometric Login")
////            .setSubtitle("Log in using your fingerprint")
////            .setNegativeButtonText("Cancel")
////            .build()
////
////        biometricPrompt.authenticate(promptInfo)  // Trigger biometric authentication
//
//    }
//}
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccBankAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()  // Single NavController for everything

                    Column(modifier = Modifier.fillMaxSize()) {
                        // This NavHost will handle both bottom navigation screens and full-screen pages
                        NavHost(
                            navController = navController,
                            startDestination = NavigationRoutes.Login.route // Start screen
                        ) {
                            // Sign-In Screen
                            composable(NavigationRoutes.Login.route) {
                                LoginScreen(navController)
                            }

                            // Main Screen with Bottom Nav
                            composable(NavigationRoutes.MainScreenWithBottomNav.route) {
                                MainScreenWithBottomNav(navController)
                            }
                            // MoveMoney Screen (Bottom Navigation)
                            composable(NavigationRoutes.MoveMoney.route) {
                                MoveMoney(navController)
                            }

                            // Account Overview Screen (Bottom Navigation)
                            composable(NavigationRoutes.AccountOverview.route) {
                                AccountOverview(navController)
                            }

                            // Full-screen page like SendMoney
                            composable(NavigationRoutes.SendMoney.route) {
                                SendMoney()  // Full-screen page
                            }


                            // Other bottom nav screens
                            composable(NavigationRoutes.EmailOTPValidationScreen.route) {
                                EmailOTPValidationScreen(navController)
                            }

                            composable(NavigationRoutes.PhoneNumberInputScreen.route) {
                                PhoneNumberInputScreen(navController)
                            }
                            composable(NavigationRoutes.Registration.route) {
                                RegistrationScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

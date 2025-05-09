//package com.example.accbankandroid
//
////import android.hardware.biometrics.BiometricPrompt
//import androidx.biometric.BiometricPrompt
//
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.ui.Modifier
//import androidx.core.view.WindowCompat
//import androidx.fragment.app.FragmentActivity
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.Navigation
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.accbankandroid.ConformationSheets.Screen.SendMoneyConfirmationScreen
//
//import com.example.accbankandroid.SendMoney
//
//import com.example.accbankandroid.ui.theme.AccBankAndroidTheme
//import java.util.concurrent.Executor
//import java.util.concurrent.Executors
//
//class MainActivity : FragmentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            AccBankAndroidTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Initialize the NavController here
//                    val navController = rememberNavController()
//// If you passed "go_to" from the Intent:
//                    val startDestination = if (intent?.getStringExtra("go_to") == "MainScreenWithBottomNav") {
//                        NavigationRoutes.MainScreenWithBottomNav.route
//                    } else {
//                        NavigationRoutes.Login.route
//                    }
//                    Column(modifier = Modifier.fillMaxSize()) {
//                        // This NavHost will handle both bottom navigation screens and full-screen pages
//                        NavHost(
//                            navController = navController,
//                            startDestination = startDestination
//                        ) {
//                            // Define composables for each screen here
//
//                            composable(NavigationRoutes.Login.route) {
//                                LoginScreen(navController)
//                            }
//
//                            composable("loginOtpScreen/{token}") { backStackEntry ->
//                                val token = backStackEntry.arguments?.getString("token") ?: ""
//                                LoginOtpScreen(token = token, navController = navController)
//                            }
//
//                            // SendMoneyConfirmation composable with arguments
//                            composable("sendMoneyConfirmation/{contactName}/{contactEmail}/{accountName}/{accountNumber}/{transferAmount}/{message}/{securityQuestion}/{securityAnswer}") { backStackEntry ->
//                                val contactName = backStackEntry.arguments?.getString("contactName") ?: ""
//                                val contactEmail = backStackEntry.arguments?.getString("contactEmail") ?: ""
//                                val accountName = backStackEntry.arguments?.getString("accountName") ?: ""
//                                val accountNumber = backStackEntry.arguments?.getString("accountNumber") ?: ""
//                                val transferAmount = backStackEntry.arguments?.getString("transferAmount") ?: ""
//                                val message = backStackEntry.arguments?.getString("message") ?: ""
//                                val securityQuestion = backStackEntry.arguments?.getString("securityQuestion") ?: ""
//                                val securityAnswer = backStackEntry.arguments?.getString("securityAnswer") ?: ""
//
//                                SendMoneyConfirmationScreen(
//                                    navController = navController,
//                                    contactName = contactName,
//                                    contactEmail = contactEmail,
//                                    accountName = accountName,
//                                    accountNumber = accountNumber,
//                                    transferAmount = transferAmount,
//                                    message = message,
//                                    securityQuestion = securityQuestion,
//                                    securityAnswer = securityAnswer
//                                )
//                            }
//
//                            // Main Screen with Bottom Navigation
//                            composable(NavigationRoutes.MainScreenWithBottomNav.route) {
//                                MainScreenWithBottomNav(navController)
//                            }
//
//                            // Add other routes as needed
//                            composable(NavigationRoutes.SendMoney.route) {
//                                SendMoney(navController)
//                            }
//
//                            composable(NavigationRoutes.MoveMoney.route) {
//                                MoveMoney(navController)
//                            }
//
//                            composable(NavigationRoutes.AccountOverview.route) {
//                                AccountOverview(navController)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//package com.example.accbankandroid
//
////import android.hardware.biometrics.BiometricPrompt
//import androidx.biometric.BiometricPrompt
//
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Modifier
//import androidx.core.view.WindowCompat
//import androidx.fragment.app.FragmentActivity
//import androidx.navigation.NavHostController
//import androidx.navigation.NavType
//import androidx.navigation.Navigation
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.accbankandroid.ConformationSheets .SendMoneyConfirmationScreen
//
//import com.example.accbankandroid.SendMoney
//import com.example.accbankandroid.NavigationRoutes // Import NavigationRoutes
//
//import com.example.accbankandroid.ui.theme.AccBankAndroidTheme
//import java.net.URLEncoder
//import java.nio.charset.StandardCharsets
//import java.util.concurrent.Executor
//import java.util.concurrent.Executors
//
//class MainActivity : FragmentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            AccBankAndroidTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Initialize the NavController here
//                    val navController = rememberNavController()
//
//                    // Check for intent extras to determine the start destination or navigate
//                    val startDestination = if (intent?.getBooleanExtra("navigate_to_confirmation", false) == true) {
//                        // If we are navigating to confirmation from an Intent,
//                        // the start destination can still be Login or MainScreenWithBottomNav
//                        // depending on your app's overall flow when not coming from SendMoney.
//                        // For this example, we'll assume the default start, but immediately navigate
//                        if (intent?.getStringExtra("go_to") == "MainScreenWithBottomNav") {
//                            NavigationRoutes.MainScreenWithBottomNav.route
//                        } else {
//                            NavigationRoutes.Login.route
//                        }
//                    } else {
//                        // Default start destination if no specific navigation is requested by intent
//                        if (intent?.getStringExtra("go_to") == "MainScreenWithBottomNav") {
//                            NavigationRoutes.MainScreenWithBottomNav.route
//                        } else {
//                            NavigationRoutes.Login.route
//                        }
//                    }
//
//
//                    Column(modifier = Modifier.fillMaxSize()) {
//                        // This NavHost will handle both bottom navigation screens and full-screen pages
//                        NavHost(
//                            navController = navController,
//                            startDestination = startDestination
//                        ) {
//                            // Define composables for each screen here
//
//                            composable(NavigationRoutes.Login.route) {
//                                LoginScreen(navController)
//                            }
//
//                            composable("loginOtpScreen/{token}") { backStackEntry ->
//                                val token = backStackEntry.arguments?.getString("token") ?: ""
//                                LoginOtpScreen(token = token, navController = navController)
//                            }
//
//                            // SendMoneyConfirmation composable with arguments
//                            composable("sendMoneyConfirmation/{contactName}/{contactEmail}/{accountName}/{accountNumber}/{transferAmount}/{message}/{securityQuestion}/{securityAnswer}") { backStackEntry ->
//                                val contactName = backStackEntry.arguments?.getString("contactName") ?: ""
//                                val contactEmail = backStackEntry.arguments?.getString("contactEmail") ?: ""
//                                val accountName = backStackEntry.arguments?.getString("accountName") ?: ""
//                                val accountNumber = backStackEntry.arguments?.getString("accountNumber") ?: ""
//                                val transferAmount = backStackEntry.arguments?.getString("transferAmount") ?: ""
//                                val message = backStackEntry.arguments?.getString("message") ?: ""
//                                val securityQuestion = backStackEntry.arguments?.getString("securityQuestion") ?: ""
//                                val securityAnswer = backStackEntry.arguments?.getString("securityAnswer") ?: ""
//
//                                SendMoneyConfirmationScreen(
//                                    navController = navController,
//                                    contactName = contactName,
//                                    contactEmail = contactEmail,
//                                    accountName = accountName,
//                                    accountNumber = accountNumber,
//                                    transferAmount = transferAmount,
//                                    message = message,
//                                    securityQuestion = securityQuestion,
//                                    securityAnswer = securityAnswer
//                                )
//                            }
//
//                            // Main Screen with Bottom Navigation
//                            composable(NavigationRoutes.MainScreenWithBottomNav.route) {
//                                MainScreenWithBottomNav(navController)
//                            }
//
//                            // Add other routes as needed
//                            // Assuming SendMoney is still a destination if navigating within MainActivity
//                            composable(NavigationRoutes.SendMoney.route) {
//                                SendMoney(navController)
//                            }
//
//                            composable(NavigationRoutes.MoveMoney.route) {
//                                MoveMoney(navController)
//                            }
//
//                            composable(NavigationRoutes.AccountOverview.route) {
//                                AccountOverview(navController)
//                            }
//
//                            // Add composable for AddContact if it's part of this NavHost
//                            composable(NavigationRoutes.AddContact.route) {
//                                // Replace with your actual AddContact composable
//                                // AddContactScreen(navController)
//                            }
//
//                            // Add composable for ConfirmContactScreen if it's part of this NavHost
//                            composable(NavigationRoutes.ConfirmContactScreen.route) {
//                                // Replace with your actual ConfirmContactScreen composable
//                                // ConfirmContactScreen(navController)
//                            }
//                        }
//                    }
//
//                    // Use LaunchedEffect to perform the navigation after the composables are in place
//                    LaunchedEffect(key1 = intent) {
//                        if (intent?.getBooleanExtra("navigate_to_confirmation", false) == true) {
//                            val contactName = intent.getStringExtra("contactName") ?: ""
//                            val contactEmail = intent.getStringExtra("contactEmail") ?: ""
//                            val accountName = intent.getStringExtra("accountName") ?: ""
//                            val accountNumber = intent.getStringExtra("accountNumber") ?: ""
//                            val transferAmount = intent.getStringExtra("transferAmount") ?: ""
//                            val message = intent.getStringExtra("message") ?: ""
//                            val securityQuestion = intent.getStringExtra("securityQuestion") ?: ""
//                            val securityAnswer = intent.getStringExtra("securityAnswer") ?: ""
//
//                            // Use the buildRoute function from NavigationRoutes for consistency and encoding
//                            val route = NavigationRoutes.SendMoneyConfirmation.buildRoute(
//                                contactName = contactName,
//                                contactEmail = contactEmail,
//                                accountName = accountName,
//                                accountNumber = accountNumber,
//                                transferAmount = transferAmount,
//                                message = message,
//                                securityQuestion = securityQuestion,
//                                securityAnswer = securityAnswer
//                            )
//
//                            // Navigate to the confirmation screen
//                            navController.navigate(route) {
//                                // Optional: Configure popUpTo and launchSingleTop depending on desired back stack behavior
//                                // popUpTo(navController.graph.startDestinationId) { saveState = true }
//                                // launchSingleTop = true
//                            }
//
//                            // Consume the intent extra so navigation doesn't happen again on recreation
//                            intent.removeExtra("navigate_to_confirmation")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
package com.example.accbankandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.`ConfirmationSheets`.TransferSuccessScreen
import com.example.accbankandroid.ConformationSheets.SendMoneyConfirmationScreen
import com.example.accbankandroid.ConformationSheets.TransferMyAccConfirmationScreen
import com.example.accbankandroid.ConformationSheets.TransferToMemberConfirmationScreen
import com.example.accbankandroid.ui.theme.AccBankAndroidTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
////////////////send money conformation/////////////////////////////////////////////
        val contactName = intent.getStringExtra("contactName") ?: ""
        val contactEmail = intent.getStringExtra("contactEmail") ?: ""
        val accountName = intent.getStringExtra("accountName") ?: ""
        val accountNumber = intent.getStringExtra("accountNumber") ?: ""
        val transferAmount = intent.getStringExtra("transferAmount") ?: ""
        val message = intent.getStringExtra("message") ?: ""
        val securityQuestion = intent.getStringExtra("securityQuestion") ?: ""
        val securityAnswer = intent.getStringExtra("securityAnswer") ?: ""
/////////////////////////send money conforamation////////////////////////////////////

////////////////////////contact conforamation///////////////////////////////////////
        val name = intent.getStringExtra("name") ?: ""
        val nickname = intent.getStringExtra("nickname") ?: ""
        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val countryCode = intent.getStringExtra("countryCode") ?: ""
        val mobilePhone = intent.getStringExtra("mobilePhone") ?: ""
        val sendByEmail = intent.getBooleanExtra("sendByEmail", false)
        val sendByPhone = intent.getBooleanExtra("sendByPhone", false)

//////////////// Transfer My Account Confirmation ///////////////////////////////
        val fromAccountName = intent.getStringExtra("fromAccountName") ?: ""
        val fromAccountNumber = intent.getStringExtra("fromAccountNumber") ?: ""
        val toAccountName = intent.getStringExtra("toAccountName") ?: ""
        val toAccountNumber = intent.getStringExtra("toAccountNumber") ?: ""
        val amount = intent.getStringExtra("amount") ?: ""
        val paymentType = intent.getStringExtra("paymentType") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val memo = intent.getStringExtra("memo") ?: ""
        val frequency = intent.getStringExtra("frequency")?.takeIf { it != "null" } ?: ""
        val endDate = intent.getStringExtra("endDate")?.takeIf { it != "null" } ?: ""
//////////////// Transfer My Account Confirmation ///////////////////////////////
        //////////////// Transfer To Member Confirmation ///////////////////////////////
        val toMemberFromAccountName = intent.getStringExtra("fromAccountName") ?: ""
        val toMemberFromAccountNumber = intent.getStringExtra("fromAccountNumber") ?: ""
        val toMemberName = intent.getStringExtra("toMemberName") ?: ""
        val toMemberEmail = intent.getStringExtra("toMemberEmail") ?: ""
        val toMemberAmount = intent.getStringExtra("amount") ?: ""
        val toMemberPaymentType = intent.getStringExtra("paymentType") ?: ""
        val toMemberDate = intent.getStringExtra("date") ?: ""
        val toMemberMemo = intent.getStringExtra("memo") ?: ""
        val toMemberFrequency = intent.getStringExtra("frequency")?.takeIf { it != "null" } ?: ""
        val toMemberEndDate = intent.getStringExtra("endDate")?.takeIf { it != "null" } ?: ""
//////////////// Transfer To Member Confirmation ///////////////////////////////

        val fromAccount = "$fromAccountName - $fromAccountNumber"
        val toAccount = "$toAccountName - $toAccountNumber"

        val startDestination = when {
            intent?.getBooleanExtra("navigate_to_confirmation", false) == true -> {
                NavigationRoutes.SendMoneyConfirmation.buildRoute(
                    contactName = contactName,
                    contactEmail = contactEmail,
                    accountName = accountName,
                    accountNumber = accountNumber,
                    transferAmount = transferAmount,
                    message = message,
                    securityQuestion = securityQuestion,
                    securityAnswer = securityAnswer
                )
            }
            intent?.getBooleanExtra("navigate_contact_confirmation", false) == true -> {
                NavigationRoutes.ConfirmContactScreen.buildRoute(
                    name = name,
                    nickname = nickname,
                    phoneNumber = phoneNumber,
                    email = email,
                    countryCode = countryCode,
                    mobilePhone = mobilePhone,
                    sendByEmail = sendByEmail,
                    sendByPhone = sendByPhone
                )
            }
            intent?.getBooleanExtra("navigate_to_send_money", false) == true -> {
                NavigationRoutes.SendMoney.route // ✅ This is what was missing
            }

            intent?.getBooleanExtra("navigate_to_transfer_money", false) == true -> {
                NavigationRoutes.TransferMoney.route

            }

            intent?.getBooleanExtra("navigate_to_myacc_confirmation", false) == true -> {
                NavigationRoutes.TransferMyAccConfirmation.buildRoute(
                    fromAccountName = fromAccountName,
                    fromAccountNumber = fromAccountNumber,
                    toAccountName = toAccountName,
                    toAccountNumber = toAccountNumber,
                    amount = amount,
                    paymentType = paymentType,
                    date = date,
                    memo = memo,
                    frequency = frequency,
                    endDate = endDate
                )
            }


            // Use this to build the route:
            intent?.getBooleanExtra("navigate_to_success_screen", false) == true -> {
                NavigationRoutes.TransferSuccess.buildRoute(
                    fromAccountName = fromAccountName,
                    fromAccountNumber = fromAccountNumber,
                    toAccountName = toAccountName,
                    toAccountNumber = toAccountNumber,
                    amount = amount,
                    paymentType = paymentType,
                    date = date,
                    memo = memo,
                    frequency = frequency,
                    endDate = endDate
                )
            }





            intent?.getBooleanExtra("navigate_to_member_confirmation", false) == true -> {
                NavigationRoutes.TransferToMemberConfirmation.buildRoute(
                    fromAccountName = fromAccountName,
                    fromAccountNumber = fromAccountNumber,
                    toMemberName = toMemberName,
                    toMemberEmail = toMemberEmail,
                    amount = amount,
                    paymentType = paymentType,
                    date = date,
                    memo = memo,
                    frequency = frequency,
                    endDate = endDate
                )
            }


            else -> NavigationRoutes.Login.route
        }

        setContent {
            AccBankAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Column(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = startDestination
                        ) {
                            composable(NavigationRoutes.Login.route) {
                                LoginScreen(navController)
                            }

                            composable("loginOtpScreen/{token}") { backStackEntry ->
                                val token = backStackEntry.arguments?.getString("token") ?: ""
                                LoginOtpScreen(token = token, navController = navController)
                            }

                            composable("sendMoneyConfirmation/{contactName}/{contactEmail}/{accountName}/{accountNumber}/{transferAmount}/{message}/{securityQuestion}/{securityAnswer}") { backStackEntry ->
                                SendMoneyConfirmationScreen(
                                    navController = navController,
                                    contactName = backStackEntry.arguments?.getString("contactName") ?: "",
                                    contactEmail = backStackEntry.arguments?.getString("contactEmail") ?: "",
                                    accountName = backStackEntry.arguments?.getString("accountName") ?: "",
                                    accountNumber = backStackEntry.arguments?.getString("accountNumber") ?: "",
                                    transferAmount = backStackEntry.arguments?.getString("transferAmount") ?: "",
                                    message = backStackEntry.arguments?.getString("message") ?: "",
                                    securityQuestion = backStackEntry.arguments?.getString("securityQuestion") ?: "",
                                    securityAnswer = backStackEntry.arguments?.getString("securityAnswer") ?: ""
                                )
                            }



                            composable("confirmContact/{name}/{nickname}/{phoneNumber}/{email}/{countryCode}/{mobilePhone}/{sendByEmail}/{sendByPhone}") { backStackEntry ->
                                ConfirmContactScreen(
                                    name = backStackEntry.arguments?.getString("name") ?: "",
                                    nickname = backStackEntry.arguments?.getString("nickname") ?: "",
                                    phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: "",
                                    email = backStackEntry.arguments?.getString("email") ?: "",
                                    countryCode = backStackEntry.arguments?.getString("countryCode") ?: "",
                                    mobilePhone = backStackEntry.arguments?.getString("mobilePhone") ?: "",
                                    sendByEmail = backStackEntry.arguments?.getBoolean("sendByEmail") ?: false,
                                    sendByPhone = backStackEntry.arguments?.getBoolean("sendByPhone") ?: false
                                )
                            }


                            composable(NavigationRoutes.MainScreenWithBottomNav.route) {
                                MainScreenWithBottomNav(navController)
                            }

                            composable(NavigationRoutes.SendMoney.route) {
                                SendMoney(navController)
                            }


                            composable(NavigationRoutes.MoveMoney.route) {
                                MoveMoney(navController)
                            }

                            composable(NavigationRoutes.AccountOverview.route) {
                                AccountOverview(navController)
                            }


                            composable(NavigationRoutes.Registration.route) {
                                RegistrationScreen(navController)
                            }
                            composable(NavigationRoutes.AddContact.route) {
                                AddContactScreen(context = this@MainActivity)
                            }
                            composable(NavigationRoutes.TransferMoney.route) {
                                TransferMoneyScreen()
                            }
                            composable("transferMyAccConfirmation/{fromAccountName}/{fromAccountNumber}/{toAccountName}/{toAccountNumber}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}") { backStackEntry ->

                            TransferMyAccConfirmationScreen(
                                    navController = navController,
                                    fromAccountName = fromAccountName,
                                    fromAccountNumber = fromAccountNumber,
                                    toAccountName = toAccountName,
                                    toAccountNumber = toAccountNumber,
                                    amount = amount,
                                    paymentType = paymentType,
                                    date = date,
                                    memo = memo,
                                    frequency = frequency,
                                    endDate = endDate
                                )
                            }


                            composable("transferToMemberConfirmation/{fromAccountName}/{fromAccountNumber}/{toMemberName}/{toMemberEmail}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}") { backStackEntry ->

                                val fromAccountName = backStackEntry.arguments?.getString("fromAccountName") ?: ""
                                val fromAccountNumber = backStackEntry.arguments?.getString("fromAccountNumber") ?: ""
                                val toMemberName = backStackEntry.arguments?.getString("toMemberName") ?: ""
                                val toMemberEmail = backStackEntry.arguments?.getString("toMemberEmail") ?: ""
                                val amount = backStackEntry.arguments?.getString("amount") ?: ""
                                val paymentType = backStackEntry.arguments?.getString("paymentType") ?: ""
                                val date = backStackEntry.arguments?.getString("date") ?: ""
                                val memo = backStackEntry.arguments?.getString("memo") ?: ""
                                val frequency = backStackEntry.arguments?.getString("frequency") ?: ""
                                val endDate = backStackEntry.arguments?.getString("endDate") ?: ""

                                TransferToMemberConfirmationScreen(
                                    navController = navController,
                                    fromAccountName = fromAccountName,
                                    fromAccountNumber = fromAccountNumber,
                                    toMemberName = toMemberName,
                                    toMemberEmail = toMemberEmail,
                                    amount = amount,
                                    paymentType = paymentType,
                                    date = date,
                                    memo = memo,
                                    frequency = frequency,
                                    endDate = endDate
                                )
                            }

                            composable("transferSuccess/{fromAccountName}/{fromAccountNumber}/{toAccountName}/{toAccountNumber}/{amount}/{paymentType}/{date}/{memo}/{frequency}/{endDate}") { backStackEntry ->
                                TransferSuccessScreen(
                                    navController = navController,
                                    fromAccountName = backStackEntry.arguments?.getString("fromAccountName") ?: "",
                                    fromAccountNumber = backStackEntry.arguments?.getString("fromAccountNumber") ?: "",
                                    toAccountName = backStackEntry.arguments?.getString("toAccountName") ?: "",
                                    toAccountNumber = backStackEntry.arguments?.getString("toAccountNumber") ?: "",
                                    amount = backStackEntry.arguments?.getString("amount") ?: "",
                                    paymentType = backStackEntry.arguments?.getString("paymentType") ?: "",
                                    date = backStackEntry.arguments?.getString("date") ?: "",
                                    memo = backStackEntry.arguments?.getString("memo") ?: "",
                                    frequency = backStackEntry.arguments?.getString("frequency")?.takeIf { it != "null" },
                                    endDate = backStackEntry.arguments?.getString("endDate")?.takeIf { it != "null" }
                                )
                            }




                        }
                    }
                }
            }
        }
    }
}

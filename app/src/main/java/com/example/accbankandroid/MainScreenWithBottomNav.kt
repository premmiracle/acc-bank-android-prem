package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.CommonComponents.BottomNavigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.CommonComponents.TopBarLogo
import com.example.accbankandroid.ui.theme.getGradientBrush
//import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect


@Composable
fun MainScreenWithBottomNav(navController: NavController) {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    // Set white system bar colors
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White, // match your top bar or screen background
            darkIcons = true     // use dark icons for light background
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush()) // ✅ Same Background as AccountOverview
            .systemBarsPadding() // ✅ Prevents overlapping with system bars
    ) {
        TopBarLogo()
        // ✅ Content Section (Dynamic based on navigation)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // ✅ Allow it to take remaining space
            contentAlignment = Alignment.Center
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.AccountOverview.route
            ) {
                composable(NavigationRoutes.Login.route) { LoginScreen(navController) }
                composable("loginOtpScreen/{token}") { backStackEntry ->
                    val token = backStackEntry.arguments?.getString("token") ?: ""
                    LoginOtpScreen(token, navController)
                }
                composable(NavigationRoutes.AccountOverview.route) { AccountOverview(navController) }
                composable(NavigationRoutes.EmailOTPValidationScreen.route) { EmailOTPValidationScreen(navController) }
                composable(NavigationRoutes.PhoneNumberInputScreen.route) { PhoneNumberInputScreen(navController) }
                composable(NavigationRoutes.Registration.route) { RegistrationScreen(navController) }
                composable(NavigationRoutes.MoveMoney.route) { MoveMoney(navController) }

            }
        }

        // ✅ Bottom Navigation Bar - Similar to AccountOverview
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .imePadding(), // ✅ Ensures it doesn’t overlap with keyboard
            contentAlignment = Alignment.Center
        ) {
            BottomNavigation(navController)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController() // Mock NavController for preview
    MainScreenWithBottomNav(navController)
}
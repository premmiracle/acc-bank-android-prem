package com.example.accbankandroid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.CommonComponents.BottomNavigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreenWithBottomNav(navController: NavController){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navController) } // ✅ Persistent Bottom Navigation
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.AccountOverview.route // ✅ Set AccountOverview as default
            ) {
                composable(NavigationRoutes.Login.route) { LoginScreen(navController) }
                composable(NavigationRoutes.AccountOverview.route) { AccountOverview(navController) }
                composable(NavigationRoutes.EmailOTPValidationScreen.route) { EmailOTPValidationScreen(navController) }
                composable(NavigationRoutes.PhoneNumberInputScreen.route) { PhoneNumberInputScreen(navController) }
                composable(NavigationRoutes.Registration.route) { RegistrationScreen(navController) }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController() // Mock NavController for preview
    MainScreenWithBottomNav(navController)
}
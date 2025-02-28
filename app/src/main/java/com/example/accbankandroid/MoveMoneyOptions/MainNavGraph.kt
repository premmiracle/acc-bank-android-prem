package com.example.accbankandroid.MoveMoneyOptions

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.accbankandroid.MoveMoney
import com.example.accbankandroid.MoveMoneyOptions.Interac_E_Transfer.*
import com.example.accbankandroid.NavigationRoutes

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.MoveMoney.route
    ) {
        composable(NavigationRoutes.MoveMoney.route) { MoveMoney(navController) }
//        composable(NavigationRoutes.InteracETransfer.route) { InteracETransfer(navController) }
//        composable(NavigationRoutes.SendMoney.route) { SendMoney(navController) }
    }
}

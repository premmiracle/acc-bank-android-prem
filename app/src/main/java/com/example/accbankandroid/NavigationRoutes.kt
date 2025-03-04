package com.example.accbankandroid

import androidx.navigation.Navigation


sealed class NavigationRoutes(val route: String) {
    object Login : NavigationRoutes("login")
    object AccountOverview : NavigationRoutes("AccountOverview")
    object EmailOTPValidationScreen : NavigationRoutes("EmailOTPValidationScreen")
    object PhoneNumberInputScreen : NavigationRoutes("PhoneNumberInputScreen")
    object Registration : NavigationRoutes("Registration")
    object MainScreenWithBottomNav : NavigationRoutes("MainScreenWithBottomNav")
    object MoveMoney : NavigationRoutes("MoveMoney")
    object SendMoney : NavigationRoutes("send_money")//sure this is unique
//    object SendMoney : NavigationRoutes("MoveMoneyOptions/Interac_E_Transfer/SendMoney")

}


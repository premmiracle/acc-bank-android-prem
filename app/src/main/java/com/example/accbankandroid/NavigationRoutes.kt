package com.example.accbankandroid

import androidx.navigation.Navigation

//object NavigationRoutes {
//    const val Login = "sign_in"
//    const val ACCOUNT_OVERVIEW = "account_overview"
//}


sealed class NavigationRoutes(val route: String) {
    object Login : NavigationRoutes("login")
    object AccountOverview : NavigationRoutes("AccountOverview")
    object EmailOTPValidationScreen : NavigationRoutes("EmailOTPValidationScreen")
    object PhoneNumberInputScreen : NavigationRoutes("PhoneNumberInputScreen")
    object Registration : NavigationRoutes("Registration")
    object MainScreenWithBottomNav : NavigationRoutes("MainScreenWithBottomNav")
    object MoveMoney : NavigationRoutes("MoveMoney")



    // Interac e-Transfer Routes
    object InteracETransfer : NavigationRoutes("interac_e_transfer")
    object SendMoney : NavigationRoutes("send_money")
}

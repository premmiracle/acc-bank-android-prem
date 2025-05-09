package com.example.accbankandroid.ConformationSheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.NavigationRoutes
import com.example.accbankandroid.ui.theme.getGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferToMemberConfirmationScreen(
    navController: NavHostController,
    fromAccountName: String,
    fromAccountNumber: String,
    toMemberName: String,
    toMemberEmail: String,
    amount: String,
    paymentType: String,
    date: String,
    memo: String?,
    frequency: String?,
    endDate: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmation") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack(NavigationRoutes.MainScreenWithBottomNav.route, false)
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                InfoRow("Transfer from", "$fromAccountName - $fromAccountNumber")
                InfoRow("Recipient Name", toMemberName)
                InfoRow("Recipient Email", toMemberEmail)
                InfoRow("Amount", "$$amount")
                InfoRow("Payment type", paymentType)
                InfoRow("Date", date)

                if (paymentType == "Recurring") {
                    InfoRow("Frequency", frequency ?: "")
                    InfoRow("End Date", endDate ?: "")
                }

                InfoRow("Memo", memo?.ifBlank { "N/A" } ?: "N/A")
            }

            Button(
                onClick = {
                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferToMemberConfirmationPreview() {
    val dummyNavController = rememberNavController()

    TransferToMemberConfirmationScreen(
        navController = dummyNavController,
        fromAccountName = "Spend & Save",
        fromAccountNumber = "100108226954",
        toMemberName = "Yashri Mehta",
        toMemberEmail = "yashri@monkeybank.com",
        amount = "500",
        paymentType = "Recurring",
        date = "8 May 2025",
        memo = "Monthly support",
        frequency = "Monthly",
        endDate = "8 Dec 2025"
    )
}

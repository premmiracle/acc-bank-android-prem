package com.example.accbankandroid.ConformationSheets


import android.content.Intent
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
import com.example.accbankandroid.MainActivity
import com.example.accbankandroid.NavigationRoutes
import com.example.accbankandroid.ui.theme.getGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferMyAccConfirmationScreen(
    navController: NavHostController,
    fromAccountName: String,
    fromAccountNumber: String,
    toAccountName: String,
    toAccountNumber: String,
    amount: String,
    paymentType: String, // One-Time or Recurring
    date: String,
    memo: String?,
    frequency: String?,       // nullable: show only for recurring
    endDate: String?          // nullable: show only for recurring
) {
    val context = LocalContext.current

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
                InfoRow("Transfer to", "$toAccountName - $toAccountNumber")
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
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("navigate_to_success_screen", true)
                        putExtra("fromAccountName", fromAccountName)
                        putExtra("fromAccountNumber", fromAccountNumber)
                        putExtra("toAccountName", toAccountName)
                        putExtra("toAccountNumber", toAccountNumber)
                        putExtra("amount", amount)
                        putExtra("paymentType", paymentType)
                        putExtra("date", date)
                        putExtra("memo", memo ?: "")
                        putExtra("frequency", frequency ?: "")
                        putExtra("endDate", endDate ?: "")
                    }


                    context.startActivity(intent)
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
fun TransferMyAccConfirmationPreview() {
    val dummyNavController = rememberNavController()

    TransferMyAccConfirmationScreen(
        navController = dummyNavController,
        fromAccountName = "Car Loan",
        fromAccountNumber = "9507327932767",
        toAccountName = "Business",
        toAccountNumber = "1067016459398",
        amount = "250",
        paymentType = "One-Time Payment",
        date = "7 May 2025",
        memo = "N/A",
        frequency = null,
        endDate = null
    )
}

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
fun SendMoneyConfirmationScreen(

    navController: NavHostController,
    contactName: String,
    contactEmail: String,
    accountName: String,
    accountNumber: String,
    transferAmount: String,
    message: String,
    securityQuestion: String,
    securityAnswer: String
) {val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirm Transfer") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Pop back to MainScreenWithBottomNav
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
                InfoRow("Recipient Name:", contactName)
                InfoRow("Recipient Email:", contactEmail)
                InfoRow("From Account:", accountName)
                InfoRow("Account Number:", accountNumber)
                InfoRow("Amount:", "$$transferAmount")
                if (message.isNotBlank()) {
                    InfoRow("Message:", message)
                }
                if (securityQuestion.isNotBlank()) {
                    InfoRow("Security Question:", securityQuestion)
                    InfoRow("Security Answer:", securityAnswer)
                }
            }


            Button(
                onClick = {
                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
                        popUpTo(0) { inclusive = true } // clear entire back stack
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Confirm and Send",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }
}


@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SendMoneyConfirmationScreenPreview() {
    val dummyNavController = rememberNavController() // Create a dummy navController for preview

    SendMoneyConfirmationScreen(
        navController = dummyNavController,
        contactName = "John Doe",
        contactEmail = "john.doe@example.com",
        accountName = "No Fee Chequing",
        accountNumber = "100108226953",
        transferAmount = "250.00",
        message = "Happy Birthday!",
        securityQuestion = "Your pet's name?",
        securityAnswer = "Fluffy"
    )
}

package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.getGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveMoney(navController: NavHostController) {
    val backgroundColor = Color(0xFFB3E5FC) // Light blue background
    val cardColor = Color(0xFF222222) // Dark card color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Move money",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // List of Money Transfer Options
        MoneyOptionCard("Transfers", navController, cardColor)
        MoneyOptionCard("Interac e-Transfer®", navController, cardColor, isItalic = true)
        MoneyOptionCard("Payments", navController, cardColor)
        MoneyOptionCard("Scheduled transfers & payments", navController, cardColor)
    }
}

@Composable
fun MoneyOptionCard(title: String, navController: NavHostController, cardColor: Color, isItalic: Boolean = false) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(50.dp)
            .clickable {
                // Handle navigation or action here
                // Example: navController.navigate("transfer_screen")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Text
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal
                ),
                modifier = Modifier.weight(1f)
            )

            // Arrow Icon (→)
            Text(
                text = "›",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MoveMoneyPreview() {
    val navController = rememberNavController()
    MoveMoney(navController)
}

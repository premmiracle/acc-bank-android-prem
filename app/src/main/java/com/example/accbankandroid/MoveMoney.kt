//package com.example.accbankandroid
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.ui.theme.getGradientBrush
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MoveMoney(navController: NavHostController) {
//    val backgroundColor = Color(0xFFB3E5FC) // Light blue background
//    val cardColor = Color(0xFF222222) // Dark card color
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(getGradientBrush())
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Title
//        Text(
//            text = "Move money",
//            fontSize = 22.sp,
//            color = Color.White,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 20.dp)
//        )
//
//        // List of Money Transfer Options
//        MoneyOptionCard("Transfers", navController, cardColor)
//        MoneyOptionCard("Interac e-Transfer®", navController, cardColor, isItalic = true)
//        MoneyOptionCard("Payments", navController, cardColor)
//        MoneyOptionCard("Scheduled transfers & payments", navController, cardColor)
//    }
//}
//
//@Composable
//fun MoneyOptionCard(title: String, navController: NavHostController, cardColor: Color, isItalic: Boolean = false) {
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        colors = CardDefaults.cardColors(containerColor = cardColor),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 6.dp)
//            .height(50.dp)
//            .clickable {
//                // Handle navigation or action here
//                // Example: navController.navigate("transfer_screen")
//            }
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Text
//            Text(
//                text = title,
//                fontSize = 16.sp,
//                color = Color.White,
//                style = TextStyle(
//                    fontWeight = FontWeight.Medium,
//                    fontStyle = if (isItalic) FontStyle.Italic else FontStyle.Normal
//                ),
//                modifier = Modifier.weight(1f)
//            )
//
//            // Arrow Icon (→)
//            Text(
//                text = "›",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun MoveMoneyPreview() {
//    val navController = rememberNavController()
//    MoveMoney(navController)
//}

package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // ✅ State to show Bottom Sheet
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ Title
        Text(
            text = "Move money",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // ✅ List of Money Transfer Options
        MoneyOptionCard("Transfers", navController, cardColor) { showSheet = false }
        MoneyOptionCard("Interac e-Transfer®", navController, cardColor, isItalic = true) { showSheet = true } // ✅ Open Sheet
        MoneyOptionCard("Payments", navController, cardColor) { showSheet = false }
        MoneyOptionCard("Scheduled transfers & payments", navController, cardColor) { showSheet = false }
    }

    // ✅ Bottom Sheet for Transfer Options
    if (showSheet) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // ✅ Adds left & right padding to the bottom sheet
        ) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier.padding(horizontal = 16.dp) // ✅ Adds internal padding (if needed)
            ) {
                TransferOptionsContent()
            }
        }
    }

}

// ✅ Money Option Card (Now Accepts Click Actions)
@Composable
fun MoneyOptionCard(title: String, navController: NavHostController, cardColor: Color, isItalic: Boolean = false, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(50.dp)
            .clickable { onClick() } // ✅ Call the function on click
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ Text
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

            // ✅ Arrow Icon (→)
            Text(
                text = "›",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

// ✅ Bottom Sheet Content
@Composable
fun TransferOptionsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Interac e-Transfer®",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val options = listOf(
            "Send money" to Icons.Default.Add,
            "Request money" to Icons.Default.Add,
            "Manage contacts" to Icons.Default.Add,
            "Pending" to Icons.Default.Add,
            "History" to Icons.Default.Add,
            "Autodeposit settings" to Icons.Default.Add,
            "Profile settings" to Icons.Default.Add
        )

        options.forEach { (label, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle Click */ }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF9C27B0), // ✅ Purple Icons
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = label,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

// ✅ Preview
@Preview(showBackground = true)
@Composable
fun MoveMoneyPreview() {
    val navController = rememberNavController()
    MoveMoney(navController)
}

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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.getGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveMoney(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .padding(16.dp)
    ) {
        // ✅ Title
        Text(
            text = "Move Money",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        // ✅ Scrollable Column for all options
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            item {
                TransferOptionsCard("Transfers", listOf(
                    "Bank Transfer" to Icons.Default.ArrowForward,
                    "Wire Transfer" to Icons.Default.Sync,
                    "International" to Icons.Default.Public,
                    "Instant Transfer" to Icons.Default.Bolt,
                    "Quick Pay" to Icons.Default.Payment,
                    "Local Transfer" to Icons.Default.Home,
                    "Mobile Wallet" to Icons.Default.Smartphone,
                    "Crypto Transfer" to Icons.Default.CurrencyBitcoin
                ))
            }

            item {
                TransferOptionsCard("Interac e-Transfer®", listOf(
                    "Send Money" to Icons.Default.Send,
                    "Request Money" to Icons.Default.RequestPage,
                    "Manage Contacts" to Icons.Default.Person,
                    "Pending Transfers" to Icons.Default.Schedule,
                    "Transaction History" to Icons.Default.History,
                    "Autodeposit Settings" to Icons.Default.Settings,
                    "Profile Settings" to Icons.Default.AccountCircle,
                    "Security Settings" to Icons.Default.Security
                ))
            }

            item {
                TransferOptionsCard("Payments", listOf(
                    "Utility Bills" to Icons.Default.Receipt,
                    "Credit Card Payments" to Icons.Default.CreditCard,
                    "Loan Repayments" to Icons.Default.Money,
                    "Insurance Payments" to Icons.Default.Home,
                    "Education Fees" to Icons.Default.School,
                    "Online Shopping" to Icons.Default.ShoppingCart,
                    "Subscription Services" to Icons.Default.Subscriptions,
                    "Government Taxes" to Icons.Default.AccountBalance
                ))
            }

            item {
                TransferOptionsCard("Scheduled Transfers", listOf(
                    "Recurring Payments" to Icons.Default.Repeat,
                    "Future Transfers" to Icons.Default.DateRange,
                    "Auto Payments" to Icons.Default.Done,
                    "Subscription Management" to Icons.Default.Subscriptions,
                    "Investment Plans" to Icons.Default.TrendingUp,
                    "Budget Planning" to Icons.Default.Assessment,
                    "Fixed Deposits" to Icons.Default.Savings,
                    "Pension Plans" to Icons.Default.AccountBalanceWallet
                ))
            }
        }
    }
}

// ✅ Fixed 4 Columns, 2 Rows Layout Inside Cards Without Lazy Grid
@Composable
fun TransferOptionsCard(title: String, options: List<Pair<String, androidx.compose.ui.graphics.vector.ImageVector>>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // ✅ Heading
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // ✅ Ensure Exactly 2 Rows and 4 Columns
            val columnCount = 4
            val rowCount = 2
            val visibleOptions = options.take(columnCount * rowCount) // ✅ Ensure max 8 icons (4x2 layout)

            for (rowItems in visibleOptions.chunked(columnCount)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for ((label, icon) in rowItems) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            // ✅ Simple Icon
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp) // ✅ Smaller icon size
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            // ✅ Wrapping Text Label
                            Text(
                                text = label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                maxLines = 2 // ✅ Ensures text wraps properly
                            )
                        }
                    }
                }
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

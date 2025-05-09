package com.example.accbankandroid

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoMode
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.cardgraylight
import com.example.accbankandroid.ui.theme.getGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoveMoney(navController: NavHostController) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    var selectedSheetType by remember { mutableStateOf<BottomSheetContentType?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
    ) {
        if (showSheet) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .blur(10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Move money",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = cardgraylight)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    MoneyOptionCard("Transfers", Icons.Filled.AccountBalance, "Own bank accounts", navController) {
                        selectedSheetType = BottomSheetContentType.Transfers
                        showSheet = true
                    }
                    MoneyOptionCard("Interac e-Transfer®", Icons.Filled.Send, "Send or request money", navController) {
                        selectedSheetType = BottomSheetContentType.InteracETransfer
                        showSheet = true
                    }
                    MoneyOptionCard("Payments", Icons.Filled.Payment, "Transfer funds between Canadian banks", navController) {
                        selectedSheetType = BottomSheetContentType.Payments
                        showSheet = true
                    }
                    MoneyOptionCard("Scheduled Transfers", Icons.Filled.Schedule, "Send money to your client", navController) {
                        selectedSheetType = BottomSheetContentType.ScheduledTransfers
                        showSheet = true
                    }
                }
            }
        }
    }

    if (showSheet && selectedSheetType != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // ✅ 100% inside the column, not outside!
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp) // extra space if you want
            ) {
                // Drag Handle (Optional UI improvement)
//                Box(
//                    modifier = Modifier
//                        .padding(top = 8.dp, bottom = 16.dp)
//                        .width(40.dp)
//                        .height(4.dp)
//                        .background(Color.Gray, shape = RoundedCornerShape(50))
//                        .align(Alignment.CenterHorizontally)
//                )

                // Bottom Sheet content depending on selected option
                when (selectedSheetType) {
                    is BottomSheetContentType.Transfers -> TransfersSheetContent()
                    is BottomSheetContentType.InteracETransfer -> InteracETransferSheetContent(navController)
                    is BottomSheetContentType.Payments -> PaymentsSheetContent()
                    is BottomSheetContentType.ScheduledTransfers -> ScheduledTransfersSheetContent()
                    else -> {}
                }
            }
        }
    }

}
@Composable
fun TransfersSheetContent() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Transfers", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//        Spacer(modifier = Modifier.height(8.dp))
//        Text("Transfer between your own accounts instantly or schedule them for later.")
//    }

    val context = LocalContext.current // Get the context for creating the Intent
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Transfers",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val options = listOf(
            "Transfer Money" to Icons.Default.CompareArrows,
            "Add Contact" to Icons.Filled.AccountBalance,
            "Manage Accounts" to Icons.Default.ManageAccounts
        )

        options.forEach { (label, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (label == "Transfer Money") {
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("navigate_to_transfer_money", true)
                            context.startActivity(intent)
                        }
                    }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF525252),
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
//@Composable
//fun InteracETransferSheetContent() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Interac e-Transfer®", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//        Spacer(modifier = Modifier.height(8.dp))
//        Text("Send or request money quickly via email or phone number.")
//    }
//}
@Composable
fun PaymentsSheetContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Payments", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Make payments between Canadian banks easily and securely.")
    }
}
@Composable
fun ScheduledTransfersSheetContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Scheduled Transfers", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Plan and manage your recurring money transfers conveniently.")
    }
}


//   Reusable Money Option Card
@Composable
fun MoneyOptionCard(title: String, icon: ImageVector, description: String, navController: NavHostController, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
            .height(80.dp) ,
        colors = CardDefaults.cardColors(containerColor = cardgraylight)
        ,

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(50.dp) // Set the size of the circular background
                    .background(Color.White, shape = RoundedCornerShape(50.dp)) // Gray circular background
                    .padding(8.dp), // Padding to give space between icon and background
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.Black, // Set the icon color to white (or any color you prefer)
                    modifier = Modifier
                        .fillMaxSize() // The icon will fill the box size
                        .align(Alignment.Center) // Center the icon inside the box
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Column for Title and Description
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

//            // Arrow Icon
//            Icon(
//                imageVector = Icons.Filled.ArrowForward,
//                contentDescription = "Arrow",
//                tint = Color.Black,
//                modifier = Modifier.size(24.dp)
//            )
        }
    }
}

sealed class BottomSheetContentType {
    object Transfers : BottomSheetContentType()
    object InteracETransfer : BottomSheetContentType()
    object Payments : BottomSheetContentType()
    object ScheduledTransfers : BottomSheetContentType()
}


//   Bottom Sheet Content
@Composable
fun InteracETransferSheetContent(navController:NavHostController) {
    val context = LocalContext.current // Get the context for creating the Intent
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
            "Send money" to Icons.Default.Send,
            "Request money" to Icons.Default.RequestPage,
            "Manage contacts" to Icons.Default.PersonAddAlt1,
            "Pending" to Icons.Default.PendingActions,
            "History" to Icons.Default.History,
            "Autodeposit settings" to Icons.Default.AutoMode,
            "Profile settings" to Icons.Default.ManageAccounts
        )

        options.forEach { (label, icon) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (label == "Send money") {
                            val intent = Intent(context, MainActivity::class.java)
                            intent.putExtra("navigate_to_send_money", true)
                            context.startActivity(intent)
                        }

                    }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF525252),
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

//   Preview
@Preview(showBackground = true)
@Composable
fun MoveMoneyPreview() {
    val navController = rememberNavController()
    MoveMoney(navController)
}

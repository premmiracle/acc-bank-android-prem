package com.example.accbankandroid.ConfirmationSheets
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.NavigationRoutes
//import com.example.accbankandroid.ui.theme.getGradientBrush
//
//@Composable
//fun TransferSuccessScreen(
//    navController: NavHostController,
//    fromAccountName: String,
//    fromAccountNumber: String,
//    toAccountName: String,
//    toAccountNumber: String,
//    amount: String,
//    paymentType: String,
//    date: String,
//    memo: String,
//    frequency: String?,
//    endDate: String?
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.CheckCircle,
//                    contentDescription = null,
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                elevation = CardDefaults.cardElevation(4.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
//                    SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
//                    SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
//                    SummaryRow("Amount", "$$amount")
//                    SummaryRow("Payment Type", paymentType)
//                    SummaryRow("Date", date)
//                    if (paymentType == "Recurring") {
//                        SummaryRow("Frequency", frequency ?: "")
//                        SummaryRow("End Date", endDate ?: "")
//                    }
//                    SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)}
//            }
//        }
//
//        Column {
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Done", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.TransferMoney.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Continue with new transfer", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//@Composable
//fun SummaryRow(label: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 6.dp)) {
//        Text(label, fontSize = 14.sp, color = Color.Gray)
//        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun TransferSuccessScreenPreview() {
//    val dummyNavController = rememberNavController()
//
//    TransferSuccessScreen(
//        navController = dummyNavController,
//        fromAccountName="Car Loan",
//        fromAccountNumber= "9507327932767",
//        toAccountName="Business",
//        toAccountNumber="1067016459398",
//        amount = "25",
//        paymentType = "One-Time Payment",
//        date = "7 May 2025",
//        memo = "N/A",
//        frequency ="",
//        endDate = ""
//    )
//}
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.GenericShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.NavigationRoutes
//
//@Composable
//fun TransferSuccessScreen(
//    navController: NavHostController,
//    fromAccountName: String,
//    fromAccountNumber: String,
//    toAccountName: String,
//    toAccountNumber: String,
//    amount: String,
//    paymentType: String,
//    date: String,
//    memo: String,
//    frequency: String?,
//    endDate: String?
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.CheckCircle,
//                    contentDescription = null,
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(receiptZigZagShape)
//                    .background(Color.Black) // Border color
//                    .padding(2.dp)           // Border thickness
//            ) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(receiptZigZagShape),
//                    shape = receiptZigZagShape,
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
//                        SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
//                        SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
//                        SummaryRow("Amount", "$$amount")
//                        SummaryRow("Payment Type", paymentType)
//                        SummaryRow("Date", date)
//                        if (paymentType == "Recurring") {
//                            SummaryRow("Frequency", frequency ?: "")
//                            SummaryRow("End Date", endDate ?: "")
//                        }
//                        SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)
//                    }
//                }
//            }
//
//        }
//
//        Column {
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Done", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.TransferMoney.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Continue with new transfer", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//@Composable
//fun SummaryRow(label: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 6.dp)) {
//        Text(label, fontSize = 14.sp, color = Color.Gray)
//        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//
//// Realistic zig-zag receipt paper shape
//val receiptZigZagShape: Shape = GenericShape { size, _ ->
//    val zigzagHeight = 10f
//    val zigzagCount = 20
//    val zigzagWidth = size.width / zigzagCount
//
//    moveTo(0f, zigzagHeight)
//    for (i in 0 until zigzagCount) {
//        val x = i * zigzagWidth
//        lineTo(x + zigzagWidth / 2, 0f)
//        lineTo(x + zigzagWidth, zigzagHeight)
//    }
//
//    lineTo(size.width, size.height - zigzagHeight)
//
//    for (i in zigzagCount downTo 1) {
//        val x = i * zigzagWidth
//        lineTo(x - zigzagWidth / 2, size.height)
//        lineTo(x - zigzagWidth, size.height - zigzagHeight)
//    }
//    close()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TransferSuccessScreenPreview() {
//    val dummyNavController = rememberNavController()
//
//    TransferSuccessScreen(
//        navController = dummyNavController,
//        fromAccountName = "Car Loan",
//        fromAccountNumber = "9507327932767",
//        toAccountName = "Business",
//        toAccountNumber = "1067016459398",
//        amount = "25",
//        paymentType = "Recurring",
//        date = "7 May 2025",
//        memo = "Rent payment",
//        frequency = "Monthly",
//        endDate = "7 Sep 2025"
//    )
//}

//package com.example.accbankandroid.ConfirmationSheets
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.slideInVertically
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.GenericShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.NavigationRoutes
//
//@Composable
//fun TransferSuccessScreen(
//    navController: NavHostController,
//    fromAccountName: String,
//    fromAccountNumber: String,
//    toAccountName: String,
//    toAccountNumber: String,
//    amount: String,
//    paymentType: String,
//    date: String,
//    memo: String,
//    frequency: String?,
//    endDate: String?
//) {
//    var receiptVisible by remember { mutableStateOf(false) }
//
//    LaunchedEffect(Unit) {
//        receiptVisible = true
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.CheckCircle,
//                    contentDescription = null,
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            AnimatedVisibility(
//                visible = receiptVisible,
//                enter = slideInVertically(
//                    initialOffsetY = { fullHeight -> -fullHeight },
//                    animationSpec = tween(durationMillis = 700)
//                )
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(receiptZigZagShape)
//                        .background(Color.Black)
//                        .padding(2.dp)
//                ) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(receiptZigZagShape),
//                        shape = receiptZigZagShape,
//                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                    ) {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
//                            SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
//                            SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
//                            SummaryRow("Amount", "$$amount")
//                            SummaryRow("Payment Type", paymentType)
//                            SummaryRow("Date", date)
//                            if (paymentType == "Recurring") {
//                                SummaryRow("Frequency", frequency ?: "")
//                                SummaryRow("End Date", endDate ?: "")
//                            }
//                            SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)
//                        }
//                    }
//                }
//            }
//        }
//
//        Column {
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Done", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.TransferMoney.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Continue with new transfer", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//@Composable
//fun SummaryRow(label: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 6.dp)) {
//        Text(label, fontSize = 14.sp, color = Color.Gray)
//        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//
//val receiptZigZagShape: Shape = GenericShape { size, _ ->
//    val zigzagHeight = 10f
//    val zigzagCount = 20
//    val zigzagWidth = size.width / zigzagCount
//
//    moveTo(0f, zigzagHeight)
//    for (i in 0 until zigzagCount) {
//        val x = i * zigzagWidth
//        lineTo(x + zigzagWidth / 2, 0f)
//        lineTo(x + zigzagWidth, zigzagHeight)
//    }
//
//    lineTo(size.width, size.height - zigzagHeight)
//
//    for (i in zigzagCount downTo 1) {
//        val x = i * zigzagWidth
//        lineTo(x - zigzagWidth / 2, size.height)
//        lineTo(x - zigzagWidth, size.height - zigzagHeight)
//    }
//
//    close()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TransferSuccessScreenPreview() {
//    val dummyNavController = rememberNavController()
//
//    TransferSuccessScreen(
//        navController = dummyNavController,
//        fromAccountName = "Car Loan",
//        fromAccountNumber = "9507327932767",
//        toAccountName = "Business",
//        toAccountNumber = "1067016459398",
//        amount = "25",
//        paymentType = "Recurring",
//        date = "7 May 2025",
//        memo = "Rent payment",
//        frequency = "Monthly",
//        endDate = "7 Sep 2025"
//    )
//}


//package com.example.accbankandroid.ConfirmationSheets

//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.GenericShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.NavigationRoutes
//import kotlinx.coroutines.delay
//
//@Composable
//fun TransferSuccessScreen(
//    navController: NavHostController,
//    fromAccountName: String,
//    fromAccountNumber: String,
//    toAccountName: String,
//    toAccountNumber: String,
//    amount: String,
//    paymentType: String,
//    date: String,
//    memo: String,
//    frequency: String?,
//    endDate: String?
//) {
//    var showReceipt by remember { mutableStateOf(false) }
//    val animatedHeight by animateDpAsState(
//        targetValue = if (showReceipt) 500.dp else 0.dp,
//        animationSpec = tween(durationMillis = 2200)
//    )
//
//    LaunchedEffect(Unit) {
//        delay(500)
//        showReceipt = true
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(animatedHeight)
//                    .clip(receiptShape)
//                    .background(Color.Black)
//                    .padding(2.dp)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .clip(receiptShape)
//                        .background(Color.White)
//                        .padding(16.dp)
//                ) {
//                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                        SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
//                        SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
//                        SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
//                        SummaryRow("Amount", "$$amount")
//                        SummaryRow("Payment Type", paymentType)
//                        SummaryRow("Date", date)
//                        if (paymentType == "Recurring") {
//                            SummaryRow("Frequency", frequency ?: "")
//                            SummaryRow("End Date", endDate ?: "")
//                        }
//                        SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)
//                    }
//                }
//            }
//        }
//
//        Column {
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Done", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.TransferMoney.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Continue with new transfer", color = Color.White)
//            }
//        }
//    }
//}
//
//@Composable
//fun SummaryRow(label: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 6.dp)) {
//        Text(label, fontSize = 14.sp, color = Color.Gray)
//        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//
//// Realistic Zig-Zag Top + Curved Bottom Shape
////val receiptShape: Shape = GenericShape { size, _ ->
////    val width = size.width
////    val height = size.height
////    val zigzagHeight = 10f
////    val zigzagCount = 20
////    val zigzagWidth = width / zigzagCount
////
////    moveTo(0f, zigzagHeight)
////
////    for (i in 0 until zigzagCount) {
////        val x = i * zigzagWidth
////        lineTo(x + zigzagWidth / 2, 0f)
////        lineTo(x + zigzagWidth, zigzagHeight)
////    }
////
////    lineTo(width, height - 30f)
////    quadraticBezierTo(
////        width, height - 5f,
////        width - 30f, height - 5f
////    )
////    lineTo(30f, height - 5f)
////    quadraticBezierTo(0f, height - 5f, 0f, height - 30f)
////    close()
////}
//
//
//val receiptShape: Shape = GenericShape { size, _ ->
//    val width = size.width
//    val height = size.height
//
//    val zigzagCount = 20
//    val zigzagHeight = 10f
//    val rollCurveHeight = 30f
//    val rollDepth = 15f
//    val zigzagWidth = width / zigzagCount
//
//    // ⬆️ Zigzag Top
//    moveTo(0f, zigzagHeight)
//    for (i in 0 until zigzagCount) {
//        val x = i * zigzagWidth
//        lineTo(x + zigzagWidth / 2, 0f)
//        lineTo(x + zigzagWidth, zigzagHeight)
//    }
//
//    // ⬇️ Right edge down to just before curve
//    lineTo(width, height - rollCurveHeight)
//
//    // ⬇️ Curved bottom from right to center
//    cubicTo(
//        width, height + rollDepth,
//        width / 2, height + rollDepth,
//        width / 2, height
//    )
//
//    // ⬆️ Curved bottom from center to left
//    cubicTo(
//        width / 2, height + rollDepth,
//        0f, height + rollDepth,
//        0f, height - rollCurveHeight
//    )
//
//    // ⬅️ Left edge up to top
//    close()
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun TransferSuccessScreenPreview() {
//    val dummyNavController = rememberNavController()
//    TransferSuccessScreen(
//        navController = dummyNavController,
//        fromAccountName = "Car Loan",
//        fromAccountNumber = "9507327932767",
//        toAccountName = "Business",
//        toAccountNumber = "1067016459398",
//        amount = "25",
//        paymentType = "Recurring",
//        date = "7 May 2025",
//        memo = "Rent payment",
//        frequency = "Monthly",
//        endDate = "7 Sep 2025"
//    )
//}

//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.GenericShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.CheckCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.NavigationRoutes
//import kotlinx.coroutines.delay

//@Composable
//fun TransferSuccessScreen(
//    navController: NavHostController,
//    fromAccountName: String,
//    fromAccountNumber: String,
//    toAccountName: String,
//    toAccountNumber: String,
//    amount: String,
//    paymentType: String,
//    date: String,
//    memo: String,
//    frequency: String?,
//    endDate: String?
//) {
//    var showReceipt by remember { mutableStateOf(false) }
//    val animatedHeight by animateDpAsState(
//        targetValue = if (showReceipt) 520.dp else 0.dp,
//        animationSpec = tween(durationMillis = 2500)
//    )
//
//    LaunchedEffect(Unit) {
//        delay(600)
//        showReceipt = true
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp)
//                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
//                    .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//            Spacer(modifier = Modifier.height(12.dp))
//
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(animatedHeight)
//                    .clip(receiptShape)
//                    .shadow(6.dp, receiptShape)
//                    .background(Color.White)
//                    .padding(16.dp)
//            ) {
//                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                    SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
//                    SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
//                    SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
//                    SummaryRow("Amount", "$$amount")
//                    SummaryRow("Payment Type", paymentType)
//                    SummaryRow("Date", date)
//                    if (paymentType == "Recurring") {
//                        SummaryRow("Frequency", frequency ?: "")
//                        SummaryRow("End Date", endDate ?: "")
//                    }
//                    SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)
//                }
//            }
//
//        }
//
//        Column {
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Done", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(
//                onClick = {
//                    navController.navigate(NavigationRoutes.TransferMoney.route) {
//                        popUpTo(0) { inclusive = true }
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//                shape = RoundedCornerShape(10.dp)
//            ) {
//                Text("Continue with new transfer", color = Color.White)
//            }
//        }
//    }
//}
//
//@Composable
//fun SummaryRow(label: String, value: String) {
//    Column(modifier = Modifier.padding(vertical = 6.dp)) {
//        Text(label, fontSize = 14.sp, color = Color.Gray)
//        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
//    }
//}
//
//val receiptShape: Shape = GenericShape { size, _ ->
//    val w = size.width
//    val h = size.height
//    val zigzags = 20
//    val zigzagH = 10f
//    val curve = 30f
//    val zigzagW = w / zigzags
//
//    moveTo(0f, zigzagH)
//    repeat(zigzags) { i ->
//        val x = i * zigzagW
//        lineTo(x + zigzagW / 2, 0f)
//        lineTo(x + zigzagW, zigzagH)
//    }
//
//    lineTo(w, h - curve)
//    quadraticBezierTo(w / 2, h + curve, 0f, h - curve)
//    close()
//}
//
//
//
//@Composable
//fun PrinterSlot(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .width(220.dp)
//            .height(30.dp)
//            .background(Color.Black, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TransferSuccessScreenPreview() {
//    val dummyNavController = rememberNavController()
//    TransferSuccessScreen(
//        navController = dummyNavController,
//        fromAccountName = "Car Loan",
//        fromAccountNumber = "9507327932767",
//        toAccountName = "Business",
//        toAccountNumber = "1067016459398",
//        amount = "25",
//        paymentType = "Recurring",
//        date = "7 May 2025",
//        memo = "Rent payment",
//        frequency = "Monthly",
//        endDate = "7 Sep 2025"
//    )
//}
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.NavigationRoutes
import kotlinx.coroutines.delay

@Composable
fun TransferSuccessScreen(
    navController: NavHostController,
    fromAccountName: String,
    fromAccountNumber: String,
    toAccountName: String,
    toAccountNumber: String,
    amount: String,
    paymentType: String,
    date: String,
    memo: String,
    frequency: String?,
    endDate: String?
) {
    var showReceipt by remember { mutableStateOf(false) }
    val animatedHeight by animateDpAsState(
        targetValue = if (showReceipt) 480.dp else 0.dp,
        animationSpec = tween(durationMillis = 2000), label = ""
    )

    LaunchedEffect(Unit) {
        delay(400)
        showReceipt = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50), shape = RoundedCornerShape(10.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("Payment Sent", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // ▼ START of Printer + Receipt Section
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Payment Summary", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // Shared container to match widths
            Box(
                modifier = Modifier
                    .height(450.dp) // outer mask area
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(Color.White)
            ) {
                // Printer slot
                PrinterSlot(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .zIndex(1f)
                )

                // Animated receipt
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(animatedHeight) // animate this only!
                        .clip(receiptShape)
                        .background(Color(0xFFF3F3F3))
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) { Spacer(Modifier.height(12.dp))
                        SummaryRow("Transaction ID", "TRX-TRF-20250507-82C914")
                        SummaryRow("Transfer from", "$fromAccountName - $fromAccountNumber")
                        SummaryRow("Transfer to", "$toAccountName - $toAccountNumber")
                        SummaryRow("Amount", "$$amount")
                        SummaryRow("Payment Type", paymentType)
                        SummaryRow("Date", date)
                        if (paymentType == "Recurring") {
                            SummaryRow("Frequency", frequency ?: "")
                            SummaryRow("End Date", endDate ?: "")
                        }
                        SummaryRow("Memo", if (memo.isBlank()) "N/A" else memo)
                    }
                }
            }
        }
// ▲ END of Printer + Receipt Section


        Spacer(modifier = Modifier.weight(1f))

        // Bottom Buttons
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    navController.navigate(NavigationRoutes.MainScreenWithBottomNav.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Done", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate(NavigationRoutes.TransferMoney.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Continue with new transfer", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(label, fontSize = 14.sp, color = Color.Gray)
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun PrinterSlot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(32.dp)
            .background(Color.Black, RoundedCornerShape(8.dp))
    )
}

val receiptShape: Shape = GenericShape { size, _ ->
    val w = size.width
    val h = size.height
    val zigzags = 20
    val zigzagH = 10f
    val zigzagW = w / zigzags

    // 🔽 Top zigzag
    moveTo(0f, zigzagH)
    repeat(zigzags) { i ->
        val x = i * zigzagW
        lineTo(x + zigzagW / 2, 0f)
        lineTo(x + zigzagW, zigzagH)
    }

    // ⬇️ Right side down
    lineTo(w, h - zigzagH)

    // 🔼 Bottom zigzag (reverse)
    for (i in zigzags downTo 1) {
        val x = i * zigzagW
        lineTo(x - zigzagW / 2, h)
        lineTo(x - zigzagW, h - zigzagH)
    }

    // ⬅️ Close left side
    close()
}

@Preview(showBackground = true)
@Composable
fun TransferSuccessScreenPreview() {
    val dummyNavController = rememberNavController()
    TransferSuccessScreen(
        navController = dummyNavController,
        fromAccountName = "Car Loan",
        fromAccountNumber = "9507327932767",
        toAccountName = "Business",
        toAccountNumber = "1067016459398",
        amount = "25",
        paymentType = "Recurring",
        date = "7 May 2025",
        memo = "Rent payment",
        frequency = "Monthly",
        endDate = "7 Sep 2025"
    )
}
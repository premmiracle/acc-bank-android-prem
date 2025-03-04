package com.example.accbankandroid

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.example.accbankandroid.ui.theme.loginlight
import com.example.accbankandroid.ui.theme.logintheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferOptionBox(
    label: String,
    amount: String,
    account: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFD6D8F1) else Color.White

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$$amount",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = account,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoney() {
    val context = LocalContext.current // Get the context
    // States to track selected account data
//    var selectedAccount by remember { mutableStateOf("Chequing") }
    var amount by remember { mutableStateOf("51,494.78") }
    var accountNumber by remember { mutableStateOf("100108226953") }
    var isAccountSelected by remember { mutableStateOf(false) } // Track if an account is selected

    // Dialog to show options on click
    var showOptionsDialog by remember { mutableStateOf(false) }
// Selected Account State
    var selectedAccount by remember { mutableStateOf("No Fee") }
    var selectedAmount by remember { mutableStateOf("51,494.78") }
    var selectedAccountNumber by remember { mutableStateOf("100108226953") }
//
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFFAF9F6)), // background color for the screen
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (context is Activity) {
                    context.finish() // Close the current activity and return to the previous one
                }
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }

            Text(
                text = "Send Money",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f), // This makes the text take the remaining space and align it to the center
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // "Transfer from" dropdown option
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)), // Light Grey Background
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showBottomSheet = true }
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Transfer from",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = selectedAccount,
                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
//                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = selectedAccountNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Row for aligning selectedAmount & Dropdown Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$$selectedAmount", // Keep the original variable
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // **Send To Box (Placeholder)**
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Select Contact",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Add Contact button
        Button(
            onClick = { /* Handle add contact action */ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add contact", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add Contact", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Send transfer to" field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Send transfer to") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "Amount" field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Amount") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "Message (optional)" field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Message (optional)") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue button
        Button(
            onClick = { /* Handle continue action */ },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer Text
        Text(
            text = "*Trade-mark of Interac Corp. Used under license.",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }

    // **Bottom Sheet - Account Selection**
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { showBottomSheet = false },
            modifier = Modifier.padding(horizontal = 20.dp),
            containerColor = Color.White

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Text(
                    text = "Transfer from",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                AccountOption(
                    title = "No Fee",
                    accountType = "Chequing",
                    accountNumber = "100108226953",
                    amount = "51,494.78",
                    isSelected = selectedAccount == "Chequing"
                ) {
                    selectedAccount = "Chequing"
                    selectedAmount = "51,494.78"
                    selectedAccountNumber = "100108226953"
                    showBottomSheet = false
                }

                AccountOption(
                    title = "Spend & Save",
                    accountType = "Savings",
                    accountNumber = "100108226954",
                    amount = "123,598.84",
                    isSelected = selectedAccount == "Savings"
                ) {
                    selectedAccount = "Savings"
                    selectedAmount = "123,598.84"
                    selectedAccountNumber = "100108226954"
                    showBottomSheet = false
                }

                AccountOption(
                    title = "Travel Fund",
                    accountType = "Savings",
                    accountNumber = "100108226955",
                    amount = "3,153.12",
                    isSelected = selectedAccount == "Travel Fund"
                ) {
                    selectedAccount = "Travel Fund"
                    selectedAmount = "3,153.12"
                    selectedAccountNumber = "100108226955"
                    showBottomSheet = false
                }
            }
        }





    }
}
@Composable
fun AccountOption(
    title: String,
    accountType: String,
    accountNumber: String,
    amount: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
    val borderColor = if (isSelected) loginlight else Color.LightGray

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(2.dp, borderColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = accountType,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = accountNumber,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$$amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

//@Composable
//fun AccountOption(label: String, accountType: String, accountNumber: String, onClick: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//            .padding(16.dp)
//            .background(Color.White)
//            .padding(16.dp)
//            .clip(RoundedCornerShape(16.dp))
//    ) {
//        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 18.sp)
//        Text(text = accountType, fontWeight = FontWeight.Normal, fontSize = 14.sp)
//        Text(text = accountNumber, fontWeight = FontWeight.Light, fontSize = 12.sp, color = Color.Gray)
//    }
//}

@Preview(showBackground = true)
@Composable
fun SendMoneyScreenPreview() {
    val navController = rememberNavController() // Mock NavController for preview
    SendMoney()
}
//package com.example.accbankandroid
//
//import android.app.Activity
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.compose.rememberNavController
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SendMoney() {
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showBottomSheet by remember { mutableStateOf(false) }
//
//    // Selected Account State
//    var selectedAccount by remember { mutableStateOf("No Fee") }
//    var selectedAmount by remember { mutableStateOf("51,494.78") }
//    var selectedAccountNumber by remember { mutableStateOf("100108226953") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .background(Color.White), // Background color
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // **Header Row (Back Button & Title)**
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = {
//                if (context is Activity) {
//                    context.finish() // Close Activity
//                }
//            }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//            }
//            Text(
//                text = "Send Money",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier.weight(1f),
//                textAlign = TextAlign.Center
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // **Transfer From Box**
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)), // Light Grey Background
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { showBottomSheet = true }
//                .padding(vertical = 4.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column {
//                    Text(
//                        text = "Transfer from",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = selectedAccount,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = selectedAccountNumber,
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                }
//                Icon(
//                    imageVector = Icons.Filled.KeyboardArrowDown,
//                    contentDescription = "Dropdown",
//                    tint = Color.Gray
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // **Send To Box (Placeholder)**
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 4.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Select Contact",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Blue
//                )
//                Icon(
//                    imageVector = Icons.Filled.KeyboardArrowDown,
//                    contentDescription = "Dropdown",
//                    tint = Color.Gray
//                )
//            }
//        }
//    }
//
//    // **Bottom Sheet - Account Selection**
//    if (showBottomSheet) {
//        ModalBottomSheet(
//            sheetState = bottomSheetState,
//            onDismissRequest = { showBottomSheet = false }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "Transfer from",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(bottom = 12.dp)
//                )
//
//                AccountOption(
//                    title = "No Fee",
//                    accountType = "Chequing",
//                    accountNumber = "100108226953",
//                    amount = "51,494.78",
//                    isSelected = selectedAccount == "No Fee"
//                ) {
//                    selectedAccount = "No Fee"
//                    selectedAmount = "51,494.78"
//                    selectedAccountNumber = "100108226953"
//                    showBottomSheet = false
//                }
//
//                AccountOption(
//                    title = "Spend & Save",
//                    accountType = "Savings",
//                    accountNumber = "100108226954",
//                    amount = "123,598.84",
//                    isSelected = selectedAccount == "Spend & Save"
//                ) {
//                    selectedAccount = "Spend & Save"
//                    selectedAmount = "123,598.84"
//                    selectedAccountNumber = "100108226954"
//                    showBottomSheet = false
//                }
//
//                AccountOption(
//                    title = "Travel Fund",
//                    accountType = "Savings",
//                    accountNumber = "100108226955",
//                    amount = "3,153.12",
//                    isSelected = selectedAccount == "Travel Fund"
//                ) {
//                    selectedAccount = "Travel Fund"
//                    selectedAmount = "3,153.12"
//                    selectedAccountNumber = "100108226955"
//                    showBottomSheet = false
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun AccountOption(
//    title: String,
//    accountType: String,
//    accountNumber: String,
//    amount: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) Color(0xFFEAE6F7) else Color.White
//    val borderColor = if (isSelected) Color(0xFF6200EE) else Color.LightGray
//
//    Card(
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(containerColor = backgroundColor),
//        border = BorderStroke(2.dp, borderColor),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable(onClick = onClick)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = title,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = accountType,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Normal,
//                color = Color.Gray
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = accountNumber,
//                fontSize = 14.sp,
//                color = Color.Gray
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "$$amount",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun SendMoneyScreenPreview() {
//    val navController = rememberNavController() // Mock NavController for preview
//    SendMoney()
//}
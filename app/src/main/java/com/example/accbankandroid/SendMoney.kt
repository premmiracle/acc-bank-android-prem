//package com.example.accbankandroid
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.widget.Toast
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
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
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.ui.theme.cardgraylight
//import com.example.accbankandroid.ui.theme.getGradientBrush
//import com.example.accbankandroid.ui.theme.loginlight
//import com.example.accbankandroid.ui.theme.logintheme
//import androidx.compose.ui.text.TextStyle
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import java.io.File
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import com.example.accbankandroid.ConformationSheets.SendMoneyConfirmationActivity
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TransferOptionBox(
//    label: String,
//    amount: String,
//    account: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) Color(0xFFD6D8F1) else Color.White
//
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = backgroundColor),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable(onClick = onClick)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = label,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "$$amount",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = account,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Light,
//                color = Color.Gray
//            )
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SendMoney(navController: NavHostController) {
//
//
//
//    var securityQuestion by remember { mutableStateOf("") }
//    var securityAnswer by remember { mutableStateOf("") }
//    var confirmSecurityAnswer by remember { mutableStateOf("") }
//    var showSecurityFields by remember { mutableStateOf(false) }
//
//    var isChecked by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current // Get the context
//    // States to track selected account data
////    var selectedAccount by remember { mutableStateOf("Chequing") }
//    var amount by remember { mutableStateOf("51,494.78") }
//    var accountNumber by remember { mutableStateOf("100108226953") }
//    var isAccountSelected by remember { mutableStateOf(false) } // Track if an account is selected
//
//    // Dialog to show options on click
//    var showOptionsDialog by remember { mutableStateOf(false) }
//// Selected Account State
//    var selectedAccount by remember { mutableStateOf("No Fee") }
//    var selectedAmount by remember { mutableStateOf("51,494.78") }
//    var selectedAccountNumber by remember { mutableStateOf("100108226953") }
////
//    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showContactSheet by remember { mutableStateOf(false) }
//    var showAccountSheet by remember { mutableStateOf(false) }
//
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//
//    var searchQuery by remember { mutableStateOf("") }
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedContact by remember { mutableStateOf<TransferContact?>(null) }
//    var contactList by remember { mutableStateOf<List<TransferContact>>(emptyList()) }
//
//
//    var transferAmount by remember { mutableStateOf("") }
//    var message by remember { mutableStateOf("") }
//    val filteredContacts = contactList.filter {
//        it.name.contains(searchQuery, ignoreCase = true)
//    }
//    fun loadContacts(context: Context): List<TransferContact> {
//        val file = File(context.filesDir, "contacts.json")
//        if (!file.exists()) return emptyList()
//
//        val jsonString = file.readText()
//        val type = object : TypeToken<List<TransferContact>>() {}.type
//        return Gson().fromJson(jsonString, type) ?: emptyList()
//    }
//    var showConfirmation by remember { mutableStateOf(false) }
//
//    // **Send To Box (Placeholder)**
//
//    val scrollState = rememberScrollState()
//    val systemUiController = rememberSystemUiController()
//    // Set white system bar colors
//    SideEffect {
//        systemUiController.setSystemBarsColor(
//            color = Color.White, // match your top bar or screen background
//            darkIcons = true     // use dark icons for light background
//        )
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(scrollState)
//            .background(Color(0xFFFAF9F6)), // background color for the screen
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Back button row
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = {
//                if (context is Activity) {
//                    context.finish() // Close the current activity and return to the previous one
//                }
//            }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//            }
//
//            Text(
//                text = "Send Money",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier.weight(1f), // This makes the text take the remaining space and align it to the center
//                textAlign = TextAlign.Center
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(
//            text = "Transfer from",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray, // This defines the text color
//            textAlign = TextAlign.Start, // Align text to the start (left)
//            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
//        )
//
//        // "Transfer from" dropdown option
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)), // Light Grey Background
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { showAccountSheet = true }
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
////                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
////                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = selectedAccountNumber,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                }
//
//                // Row for aligning selectedAmount & Dropdown Icon
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "$$selectedAmount", // Keep the original variable
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowDown,
//                        contentDescription = "Dropdown",
//                        tint = Color.Gray
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "Send To",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray, // This defines the text color
//            textAlign = TextAlign.Start, // Align text to the start (left)
//            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
//        )
//
//        if (showContactSheet) {
//            ModalBottomSheet(
//                onDismissRequest = { showContactSheet = false },
//                sheetState = sheetState,
//                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(500.dp) // ✅ Fixed height
//            ) {
//                val scrollState = rememberScrollState()
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .verticalScroll(scrollState) // ✅ Make it scrollable
//                ) {
//
//                    // 🔸 Header Row
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = "Select Contact",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp
//                        )
//
//                        IconButton(onClick = { showContactSheet = false }) {
//                            Icon(
//                                imageVector = Icons.Default.Close,
//                                contentDescription = "Close"
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    OutlinedTextField(
//                        value = searchQuery,
//                        onValueChange = { searchQuery = it },
//                        placeholder = { Text("Search") },
//                        shape = RoundedCornerShape(12.dp),
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    filteredContacts.forEach { contact ->
//                        val isSelected = contact.id == selectedContact?.id
//
//                        val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
//                        val borderColor = if (isSelected) loginlight else Color.LightGray
//
//                        Card(
//                            shape = RoundedCornerShape(12.dp),
//                            border = BorderStroke(1.dp, borderColor),
//                            colors = CardDefaults.cardColors(containerColor = backgroundColor),
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 4.dp)
//                                .clickable {
//                                    selectedContact = contact
//                                    showContactSheet = false
//
//                                    // Auto-fill security fields
//                                    securityQuestion = contact.securityQuestion ?: ""
//                                    securityAnswer = contact.securityAnswer ?: ""
//                                    confirmSecurityAnswer = contact.confirmSecurityAnswer ?: ""
//
//                                    showSecurityFields = listOf(securityQuestion, securityAnswer, confirmSecurityAnswer).any { it.isNotBlank() }
//                                }
//                        ) {
//                            Text(
//                                text = contact.name,
//                                fontSize = 16.sp,
//                                modifier = Modifier.padding(16.dp)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//
//
//        // Dropdown Composable
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // light gray background
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    contactList = loadContacts(context) // Load contacts on click
//                    showContactSheet = true             // Show bottom sheet
//                }
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
//
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = selectedContact?.name ?: "Select Contact",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = if (selectedContact == null) Color.Gray else Color.Black
//                    )
//                }
//
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = "Dropdown",
//                    tint = Color.Gray
//                )
//            }
//        }
//
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding( vertical = 8.dp)
//                .clickable {
//                    context.startActivity(Intent(context, AddContactActivity::class.java))
//                },
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(36.dp)
//                    .background(color = Color(0xFF1E90FF), shape = CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add Contact",
//                    tint = Color.White,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Text(
//                text = "Add Recipient",
//                color = Color(0xFF1E90FF),
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        if (selectedContact != null) {
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Email - non-editable
//            OutlinedTextField(
//                value = selectedContact?.email ?: "",
//                onValueChange = {},
//                readOnly = true,
//                label = { Text("Email") },
//                shape = RoundedCornerShape(16.dp),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Security Info Section
//            Card(
//                shape = RoundedCornerShape(16.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text("Security Info", fontWeight = FontWeight.Bold)
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Checkbox(
//                                checked = showSecurityFields,
//                                onCheckedChange = { showSecurityFields = it }
//                            )
//                            Text("Update", color = Color(0xFF1E90FF), fontWeight = FontWeight.Medium)
//                        }
//                    }
//
//
//                        OutlinedTextField(
//                            shape = RoundedCornerShape(16.dp),
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                                focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                                cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                            ),
//                            value = securityQuestion,
//                            onValueChange = { securityQuestion = it },
//                            label = { Text("Security Question") },
//                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                        )
//                        OutlinedTextField(
//                            shape = RoundedCornerShape(16.dp),
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                                focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                                cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                            ),
//                            value = securityAnswer,
//                            onValueChange = { securityAnswer = it },
//                            label = { Text("Security Answer") },
//                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                        )
//                        OutlinedTextField(
//                            shape = RoundedCornerShape(16.dp),
//                            colors = TextFieldDefaults.outlinedTextFieldColors(
//                                focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                                focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                                cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                            ),
//                            value = confirmSecurityAnswer,
//                            onValueChange = { confirmSecurityAnswer = it },
//                            label = { Text("Confirm Security Answer") },
//                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                        )
//
//
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        // "Amount" field
//
//        OutlinedTextField(
//            value = transferAmount,
//            onValueChange = { transferAmount = it },
//            label = { Text("Enter transfer amount") },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF1E90FF),
//                focusedLabelColor = Color(0xFF1E90FF),
//                cursorColor = Color(0xFF1E90FF)
//            )
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = message,
//            onValueChange = { message = it },
//            label = { Text("Message (optional)") },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF1E90FF),
//                focusedLabelColor = Color(0xFF1E90FF),
//                cursorColor = Color(0xFF1E90FF)
//            )
//        )
//
//
//
//        Spacer(modifier = Modifier.height(24.dp))
//        // Add a checkbox with label similar to the one in the image
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFCAE8FF), shape = RoundedCornerShape(8.dp)) // Background color with rounded corners
//                .padding(16.dp) // Padding inside the Box
//        ) {
//            Row(
//                verticalAlignment = Alignment.Top
//            ) {
//                // Checkbox to toggle the acknowledgment
//                Checkbox(
//                    checked = isChecked,
//                    onCheckedChange = { isChecked = it },
//                    modifier = Modifier.padding(end = 8.dp),
//                    colors = CheckboxDefaults.colors(
//                        checkedColor = Color(0xFF1E90FF), // Set your desired color for the checked state
//                        uncheckedColor = Color(0xFF1E90FF) // Set your desired color for the unchecked state
//                    )
//                )
//
//                // Text next to the checkbox
//                Text(
//                    text = "I acknowledge that this recipient has auto-deposit enabled. They won't need to answer a security question, and the funds will be deposited automatically.",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                    modifier = Modifier.weight(1f), // Make sure the text takes up available space
//                    style = TextStyle(
//                        lineHeight = 20.sp // Adjust the line height to increase line spacing
//                    )
//                )
//
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//        // Continue button
////        Button(
////            onClick = {
////                if (selectedContact != null) {
////                    showConfirmation = true // 👈 show the confirmation
////                    val existingSecurityPresent = selectedContact!!.securityQuestion?.isNotBlank() == true ||
////                            selectedContact!!.securityAnswer?.isNotBlank() == true ||
////                            selectedContact!!.confirmSecurityAnswer?.isNotBlank() == true
////
////                    val shouldUpdateSecurity = if (existingSecurityPresent) {
////                        showSecurityFields // ✅ Only update if "Update" is checked
////                    } else {
////                        // ✅ No existing security → allow saving new info without checkbox
////                        listOf(securityQuestion, securityAnswer, confirmSecurityAnswer).any { it.isNotBlank() }
////                    }
////
////                    val updatedContact = selectedContact!!.copy(
////                        securityQuestion = if (shouldUpdateSecurity) securityQuestion else selectedContact!!.securityQuestion,
////                        securityAnswer = if (shouldUpdateSecurity) securityAnswer else selectedContact!!.securityAnswer,
////                        confirmSecurityAnswer = if (shouldUpdateSecurity) confirmSecurityAnswer else selectedContact!!.confirmSecurityAnswer
////                    )
////
////                    // 🔄 Save updated contact list
////                    val file = File(context.filesDir, "contacts.json")
////                    val listType = object : TypeToken<MutableList<TransferContact>>() {}.type
////                    val contactList: MutableList<TransferContact> = if (file.exists()) {
////                        Gson().fromJson(file.readText(), listType) ?: mutableListOf()
////                    } else mutableListOf()
////
////                    val updatedList = contactList.map {
////                        if (it.email == updatedContact.email) updatedContact else it
////                    }
////
////                    file.writeText(Gson().toJson(updatedList))
////                    Toast.makeText(context, "Contact updated", Toast.LENGTH_SHORT).show()
////                }
////            },
////                    shape = RoundedCornerShape(16.dp), // Apply corner radius directly here
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp)) // Apply gradient with rounded corners
////                .padding(1.dp), // Optional padding for spacing around the button
////            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Set container color to transparent to show gradient
////        ) {
////            Text(
////                text = "Continue",
////                color = Color.White, // Text color to contrast with the gradient
////                modifier = Modifier.padding(1.dp) // Padding for the text to align inside the button
////            )
////        }
//        Button(
//            onClick = {
//                if (selectedContact != null) {
//                    // Create an Intent to navigate to SendMoneyConfirmationActivity
//                    val intent = Intent(context, SendMoneyConfirmationActivity::class.java).apply {
//                        putExtra("contactName", selectedContact!!.name)
//                        putExtra("contactEmail", selectedContact!!.email)
//                        putExtra("accountName", selectedAccount)
//                        putExtra("accountNumber", selectedAccountNumber)
//                        putExtra("transferAmount", transferAmount)
//                        putExtra("message", message)
//                        putExtra("securityQuestion", securityQuestion)
//                        putExtra("securityAnswer", securityAnswer)
//                    }
//                    context.startActivity(intent) // Start the activity with the intent
//                }
//            },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
//                .padding(1.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
//        ) {
//            Text(
//                text = "Continue",
//                color = Color.White,
//                modifier = Modifier.padding(1.dp)
//            )
//        }
//
//
//
//
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Footer Text
//
//    }
//    if (showConfirmation && selectedContact != null) {
//        SendMoneyConfirmationSheet(
//            contactName = selectedContact!!.name,
//            contactEmail = selectedContact!!.email,
//            accountName = selectedAccount,
//            accountNumber = selectedAccountNumber,
//            transferAmount = transferAmount,
//            message = message,
//            securityQuestion = if (showSecurityFields) securityQuestion else "",
//            securityAnswer = if (showSecurityFields) securityAnswer else "",
//            language = "English", // or make this dynamic
//            onConfirm = {
//                showConfirmation = false
//                // 💸 Trigger transaction or navigate forward
//            },
//            onDismiss = {
//                showConfirmation = false
//            }
//        )
//    }
//
//
//    // **Bottom Sheet - Account Selection**
//    if (showAccountSheet) {
//        ModalBottomSheet(
//            sheetState = bottomSheetState,
//            onDismissRequest = { showAccountSheet = false },
//            modifier = Modifier.padding(horizontal = 20.dp),
//            containerColor = Color.White
//
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//
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
//                    isSelected = selectedAccount == "Chequing"
//                ) {
//                    selectedAccount = "Chequing"
//                    selectedAmount = "51,494.78"
//                    selectedAccountNumber = "100108226953"
//                    showAccountSheet = false
//                }
//
//                AccountOption(
//                    title = "Spend & Save",
//                    accountType = "Savings",
//                    accountNumber = "100108226954",
//                    amount = "123,598.84",
//                    isSelected = selectedAccount == "Savings"
//                ) {
//                    selectedAccount = "Savings"
//                    selectedAmount = "123,598.84"
//                    selectedAccountNumber = "100108226954"
//                    showAccountSheet = false
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
//                    showAccountSheet = false
//                }
//            }
//        }
//
//
//
//
//
//    }
//}
//@Composable
//fun AccountOption(
//    title: String,
//    accountType: String,
//    accountNumber: String,
//    amount: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
//    val borderColor = if (isSelected) loginlight else Color.LightGray
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
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun SendMoneyScreenPreview() {
//    val navController = rememberNavController()
//    SendMoney(navController) // Pass it properly!
//}
//package com.example.accbankandroid
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.widget.Toast
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
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
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.accbankandroid.ui.theme.cardgraylight
//import com.example.accbankandroid.ui.theme.getGradientBrush
//import com.example.accbankandroid.ui.theme.loginlight
//import com.example.accbankandroid.ui.theme.logintheme
//import androidx.compose.ui.text.TextStyle
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import java.io.File
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
////import com.example.accbankandroid.ConformationSheets.SendMoneyConfirmationActivity // This import will be removed later
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//import java.net.URLEncoder
//import java.nio.charset.StandardCharsets
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TransferOptionBox(
//    label: String,
//    amount: String,
//    account: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) Color(0xFFD6D8F1) else Color.White
//
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = backgroundColor),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable(onClick = onClick)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = label,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "$$amount",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = account,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Light,
//                color = Color.Gray
//            )
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SendMoney(navController: NavHostController) {
//
//
//
//    var securityQuestion by remember { mutableStateOf("") }
//    var securityAnswer by remember { mutableStateOf("") }
//    var confirmSecurityAnswer by remember { mutableStateOf("") }
//    var showSecurityFields by remember { mutableStateOf(false) }
//
//    var isChecked by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current // Get the context
//    // States to track selected account data
////    var selectedAccount by remember { mutableStateOf("Chequing") }
//    var amount by remember { mutableStateOf("51,494.78") }
//    var accountNumber by remember { mutableStateOf("100108226953") }
//    var isAccountSelected by remember { mutableStateOf(false) } // Track if an account is selected
//
//    // Dialog to show options on click
//    var showOptionsDialog by remember { mutableStateOf(false) }
//// Selected Account State
//    var selectedAccount by remember { mutableStateOf("No Fee") }
//    var selectedAmount by remember { mutableStateOf("51,494.78") }
//    var selectedAccountNumber by remember { mutableStateOf("100108226953") }
////
//    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//    var showContactSheet by remember { mutableStateOf(false) }
//    var showAccountSheet by remember { mutableStateOf(false) }
//
//    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//
//    var searchQuery by remember { mutableStateOf("") }
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedContact by remember { mutableStateOf<TransferContact?>(null) }
//    var contactList by remember { mutableStateOf<List<TransferContact>>(emptyList()) }
//
//
//    var transferAmount by remember { mutableStateOf("") }
//    var message by remember { mutableStateOf("") }
//    val filteredContacts = contactList.filter {
//        it.name.contains(searchQuery, ignoreCase = true)
//    }
//    fun loadContacts(context: Context): List<TransferContact> {
//        val file = File(context.filesDir, "contacts.json")
//        if (!file.exists()) return emptyList()
//
//        val jsonString = file.readText()
//        val type = object : TypeToken<List<TransferContact>>() {}.type
//        return Gson().fromJson(jsonString, type) ?: emptyList()
//    }
//    var showConfirmation by remember { mutableStateOf(false) }
//
//    // **Send To Box (Placeholder)**
//
//    val scrollState = rememberScrollState()
//    val systemUiController = rememberSystemUiController()
//    // Set white system bar colors
//    SideEffect {
//        systemUiController.setSystemBarsColor(
//            color = Color.White, // match your top bar or screen background
//            darkIcons = true     // use dark icons for light background
//        )
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(scrollState)
//            .background(Color(0xFFFAF9F6)), // background color for the screen
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Back button row
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = {
//                navController.popBackStack() // Navigate back using NavController
//            }) {
//                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//            }
//
//            Text(
//                text = "Send Money",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                modifier = Modifier.weight(1f), // This makes the text take the remaining space and align it to the center
//                textAlign = TextAlign.Center
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//        Text(
//            text = "Transfer from",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray, // This defines the text color
//            textAlign = TextAlign.Start, // Align text to the start (left)
//            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
//        )
//
//        // "Transfer from" dropdown option
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)), // Light Grey Background
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { showAccountSheet = true }
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
////                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
////                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = selectedAccountNumber,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                }
//
//                // Row for aligning selectedAmount & Dropdown Icon
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "$$selectedAmount", // Keep the original variable
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Icon(
//                        imageVector = Icons.Filled.KeyboardArrowDown,
//                        contentDescription = "Dropdown",
//                        tint = Color.Gray
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "Send To",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray, // This defines the text color
//            textAlign = TextAlign.Start, // Align text to the start (left)
//            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
//        )
//
//        if (showContactSheet) {
//            ModalBottomSheet(
//                onDismissRequest = { showContactSheet = false },
//                sheetState = sheetState,
//                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(500.dp) // ✅ Fixed height
//            ) {
//                val scrollState = rememberScrollState()
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .verticalScroll(scrollState) // ✅ Make it scrollable
//                ) {
//
//                    // 🔸 Header Row
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = "Select Contact",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp
//                        )
//
//                        IconButton(onClick = { showContactSheet = false }) {
//                            Icon(
//                                imageVector = Icons.Default.Close,
//                                contentDescription = "Close"
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    OutlinedTextField(
//                        value = searchQuery,
//                        onValueChange = { searchQuery = it },
//                        placeholder = { Text("Search") },
//                        shape = RoundedCornerShape(12.dp),
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    filteredContacts.forEach { contact ->
//                        val isSelected = contact.id == selectedContact?.id
//
//                        val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
//                        val borderColor = if (isSelected) loginlight else Color.LightGray
//
//                        Card(
//                            shape = RoundedCornerShape(12.dp),
//                            border = BorderStroke(1.dp, borderColor),
//                            colors = CardDefaults.cardColors(containerColor = backgroundColor),
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 4.dp)
//                                .clickable {
//                                    selectedContact = contact
//                                    showContactSheet = false
//
//                                    // Auto-fill security fields
//                                    securityQuestion = contact.securityQuestion ?: ""
//                                    securityAnswer = contact.securityAnswer ?: ""
//                                    confirmSecurityAnswer = contact.confirmSecurityAnswer ?: ""
//
//                                    showSecurityFields = listOf(securityQuestion, securityAnswer, confirmSecurityAnswer).any { it.isNotBlank() }
//                                }
//                        ) {
//                            Text(
//                                text = contact.name,
//                                fontSize = 16.sp,
//                                modifier = Modifier.padding(16.dp)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//
//
//        // Dropdown Composable
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // light gray background
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//                    contactList = loadContacts(context) // Load contacts on click
//                    showContactSheet = true             // Show bottom sheet
//                }
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
//
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = selectedContact?.name ?: "Select Contact",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = if (selectedContact == null) Color.Gray else Color.Black
//                    )
//                }
//
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = "Dropdown",
//                    tint = Color.Gray
//                )
//            }
//        }
//
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding( vertical = 8.dp)
//                .clickable {
//                    context.startActivity(Intent(context, AddContactActivity::class.java))
//                },
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(36.dp)
//                    .background(color = Color(0xFF1E90FF), shape = CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add Contact",
//                    tint = Color.White,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Text(
//                text = "Add Recipient",
//                color = Color(0xFF1E90FF),
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        if (selectedContact != null) {
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Email - non-editable
//            OutlinedTextField(
//                value = selectedContact?.email ?: "",
//                onValueChange = {},
//                readOnly = true,
//                label = { Text("Email") },
//                shape = RoundedCornerShape(16.dp),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Security Info Section
//            Card(
//                shape = RoundedCornerShape(16.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text("Security Info", fontWeight = FontWeight.Bold)
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Checkbox(
//                                checked = showSecurityFields,
//                                onCheckedChange = { showSecurityFields = it }
//                            )
//                            Text("Update", color = Color(0xFF1E90FF), fontWeight = FontWeight.Medium)
//                        }
//                    }
//
//
//                    OutlinedTextField(
//                        shape = RoundedCornerShape(16.dp),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                        ),
//                        value = securityQuestion,
//                        onValueChange = { securityQuestion = it },
//                        label = { Text("Security Question") },
//                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                    )
//                    OutlinedTextField(
//                        shape = RoundedCornerShape(16.dp),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                        ),
//                        value = securityAnswer,
//                        onValueChange = { securityAnswer = it },
//                        label = { Text("Security Answer") },
//                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                    )
//                    OutlinedTextField(
//                        shape = RoundedCornerShape(16.dp),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
//                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
//                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
//                        ),
//                        value = confirmSecurityAnswer,
//                        onValueChange = { confirmSecurityAnswer = it },
//                        label = { Text("Confirm Security Answer") },
//                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                    )
//
//
//                }
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        // "Amount" field
//
//        OutlinedTextField(
//            value = transferAmount,
//            onValueChange = { transferAmount = it },
//            label = { Text("Enter transfer amount") },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF1E90FF),
//                focusedLabelColor = Color(0xFF1E90FF),
//                cursorColor = Color(0xFF1E90FF)
//            )
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = message,
//            onValueChange = { message = it },
//            label = { Text("Message (optional)") },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color(0xFF1E90FF),
//                focusedLabelColor = Color(0xFF1E90FF),
//                cursorColor = Color(0xFF1E90FF)
//            )
//        )
//
//
//
//        Spacer(modifier = Modifier.height(24.dp))
//        // Add a checkbox with label similar to the one in the image
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFCAE8FF), shape = RoundedCornerShape(8.dp)) // Background color with rounded corners
//                .padding(16.dp) // Padding inside the Box
//        ) {
//            Row(
//                verticalAlignment = Alignment.Top
//            ) {
//                // Checkbox to toggle the acknowledgment
//                Checkbox(
//                    checked = isChecked,
//                    onCheckedChange = { isChecked = it },
//                    modifier = Modifier.padding(end = 8.dp),
//                    colors = CheckboxDefaults.colors(
//                        checkedColor = Color(0xFF1E90FF), // Set your desired color for the checked state
//                        uncheckedColor = Color(0xFF1E90FF) // Set your desired color for the unchecked state
//                    )
//                )
//
//                // Text next to the checkbox
//                Text(
//                    text = "I acknowledge that this recipient has auto-deposit enabled. They won't need to answer a security question, and the funds will be deposited automatically.",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                    modifier = Modifier.weight(1f), // Make sure the text takes up available space
//                    style = TextStyle(
//                        lineHeight = 20.sp // Adjust the line height to increase line spacing
//                    )
//                )
//
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//        // Continue button
//        Button(
//            onClick = {
//                if (selectedContact != null) {
//                    val encodedContactName = URLEncoder.encode(selectedContact!!.name, StandardCharsets.UTF_8.toString())
//                    val encodedContactEmail = URLEncoder.encode(selectedContact!!.email, StandardCharsets.UTF_8.toString())
//                    val encodedAccountName = URLEncoder.encode(selectedAccount, StandardCharsets.UTF_8.toString())
//                    val encodedAccountNumber = URLEncoder.encode(selectedAccountNumber, StandardCharsets.UTF_8.toString())
//                    val encodedTransferAmount = URLEncoder.encode(transferAmount, StandardCharsets.UTF_8.toString())
//                    val encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
//                    val encodedSecurityQuestion = URLEncoder.encode(securityQuestion, StandardCharsets.UTF_8.toString())
//                    val encodedSecurityAnswer = URLEncoder.encode(securityAnswer, StandardCharsets.UTF_8.toString())
//
//                    navController.navigate("sendMoneyConfirmation/${encodedContactName}/${encodedContactEmail}/${encodedAccountName}/${encodedAccountNumber}/${encodedTransferAmount}/${encodedMessage}/${encodedSecurityQuestion}/${encodedSecurityAnswer}")
//                } else {
//                    Toast.makeText(context, "Please select a contact", Toast.LENGTH_SHORT).show()
//                }
//            },
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
//                .padding(1.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
//        ) {
//            Text(
//                text = "Continue",
//                color = Color.White,
//                modifier = Modifier.padding(1.dp)
//            )
//        }
//
//
//
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Footer Text
//
//    }
//    if (showConfirmation && selectedContact != null) {
//        SendMoneyConfirmationSheet(
//            contactName = selectedContact!!.name,
//            contactEmail = selectedContact!!.email,
//            accountName = selectedAccount,
//            accountNumber = selectedAccountNumber,
//            transferAmount = transferAmount,
//            message = message,
//            securityQuestion = if (showSecurityFields) securityQuestion else "",
//            securityAnswer = if (showSecurityFields) securityAnswer else "",
//            language = "English", // or make this dynamic
//            onConfirm = {
//                showConfirmation = false
//                // 💸 Trigger transaction or navigate forward
//            },
//            onDismiss = {
//                showConfirmation = false
//            }
//        )
//    }
//
//
//    // **Bottom Sheet - Account Selection**
//    if (showAccountSheet) {
//        ModalBottomSheet(
//            sheetState = bottomSheetState,
//            onDismissRequest = { showAccountSheet = false },
//            modifier = Modifier.padding(horizontal = 20.dp),
//            containerColor = Color.White
//
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//
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
//                    isSelected = selectedAccount == "Chequing"
//                ) {
//                    selectedAccount = "Chequing"
//                    selectedAmount = "51,494.78"
//                    selectedAccountNumber = "100108226953"
//                    showAccountSheet = false
//                }
//
//                AccountOption(
//                    title = "Spend & Save",
//                    accountType = "Savings",
//                    accountNumber = "100108226954",
//                    amount = "123,598.84",
//                    isSelected = selectedAccount == "Savings"
//                ) {
//                    selectedAccount = "Savings"
//                    selectedAmount = "123,598.84"
//                    selectedAccountNumber = "100108226954"
//                    showAccountSheet = false
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
//                    showAccountSheet = false
//                }
//            }
//        }
//
//
//
//    }
//}
//@Composable
//fun AccountOption(
//    title: String,
//    accountType: String,
//    accountNumber: String,
//    amount: String,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
//    val borderColor = if (isSelected) loginlight else Color.LightGray
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
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun SendMoneyScreenPreview() {
//    val navController = rememberNavController()
//    SendMoney(navController) // Pass it properly!
//}


package com.example.accbankandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.accbankandroid.ui.theme.cardgraylight
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.example.accbankandroid.ui.theme.loginlight
import com.example.accbankandroid.ui.theme.logintheme
import androidx.compose.ui.text.TextStyle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
fun SendMoney(navController: NavHostController) {



    var securityQuestion by remember { mutableStateOf("") }
    var securityAnswer by remember { mutableStateOf("") }
    var confirmSecurityAnswer by remember { mutableStateOf("") }
    var showSecurityFields by remember { mutableStateOf(false) }

    var isChecked by remember { mutableStateOf(false) }

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
    var showContactSheet by remember { mutableStateOf(false) }
    var showAccountSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var searchQuery by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf<TransferContact?>(null) }
    var contactList by remember { mutableStateOf<List<TransferContact>>(emptyList()) }


    var transferAmount by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val filteredContacts = contactList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }
    fun loadContacts(context: Context): List<TransferContact> {
        val file = File(context.filesDir, "contacts.json")
        if (!file.exists()) return emptyList()

        val jsonString = file.readText()
        val type = object : TypeToken<List<TransferContact>>() {}.type
        return Gson().fromJson(jsonString, type) ?: emptyList()
    }
    var showConfirmation by remember { mutableStateOf(false) }

    // **Send To Box (Placeholder)**

    val scrollState = rememberScrollState()
    val systemUiController = rememberSystemUiController()
    // Set white system bar colors
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White, // match your top bar or screen background
            darkIcons = true     // use dark icons for light background
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
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
        Text(
            text = "Transfer from",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray, // This defines the text color
            textAlign = TextAlign.Start, // Align text to the start (left)
            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
        )

        // "Transfer from" dropdown option
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)), // Light Grey Background
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showAccountSheet = true }
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
        Text(
            text = "Send To",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray, // This defines the text color
            textAlign = TextAlign.Start, // Align text to the start (left)
            modifier = Modifier.fillMaxWidth() // Ensure it takes the full width of its container
        )

        if (showContactSheet) {
            ModalBottomSheet(
                onDismissRequest = { showContactSheet = false },
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp) // ✅ Fixed height
            ) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(scrollState) // ✅ Make it scrollable
                ) {

                    // 🔸 Header Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Select Contact",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        IconButton(onClick = { showContactSheet = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    filteredContacts.forEach { contact ->
                        val isSelected = contact.id == selectedContact?.id

                        val backgroundColor = if (isSelected) loginlight.copy(alpha = 0.2f) else Color.White
                        val borderColor = if (isSelected) loginlight else Color.LightGray

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, borderColor),
                            colors = CardDefaults.cardColors(containerColor = backgroundColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    selectedContact = contact
                                    showContactSheet = false

                                    // Auto-fill security fields
                                    securityQuestion = contact.securityQuestion ?: ""
                                    securityAnswer = contact.securityAnswer ?: ""
                                    confirmSecurityAnswer = contact.confirmSecurityAnswer ?: ""

                                    showSecurityFields = listOf(securityQuestion, securityAnswer, confirmSecurityAnswer).any { it.isNotBlank() }
                                }
                        ) {
                            Text(
                                text = contact.name,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }


        // Dropdown Composable
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // light gray background
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    contactList = loadContacts(context) // Load contacts on click
                    showContactSheet = true             // Show bottom sheet
                }
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

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = selectedContact?.name ?: "Select Contact",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (selectedContact == null) Color.Gray else Color.Black
                    )
                }

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color.Gray
                )
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 8.dp)
                .clickable {
                    // Assuming AddContactActivity exists
                    navController.navigate(NavigationRoutes.AddContact.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color = Color(0xFF1E90FF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Add Recipient",
                color = Color(0xFF1E90FF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (selectedContact != null) {
            Spacer(modifier = Modifier.height(12.dp))

            // Email - non-editable
            OutlinedTextField(
                value = selectedContact?.email ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Email") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Security Info Section
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Security Info", fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = showSecurityFields,
                                onCheckedChange = { showSecurityFields = it }
                            )
                            Text("Update", color = Color(0xFF1E90FF), fontWeight = FontWeight.Medium)
                        }
                    }


                    OutlinedTextField(
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
                        ),
                        value = securityQuestion,
                        onValueChange = { securityQuestion = it },
                        label = { Text("Security Question") },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
                        ),
                        value = securityAnswer,
                        onValueChange = { securityAnswer = it },
                        label = { Text("Security Answer") },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF1E90FF),      // Active border color
                            focusedLabelColor = Color(0xFF1E90FF),       // Active label color
                            cursorColor = Color(0xFF1E90FF)              // Cursor color (optional)
                        ),
                        value = confirmSecurityAnswer,
                        onValueChange = { confirmSecurityAnswer = it },
                        label = { Text("Confirm Security Answer") },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    )


                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // "Amount" field

        OutlinedTextField(
            value = transferAmount,
            onValueChange = { transferAmount = it },
            label = { Text("Enter transfer amount") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1E90FF),
                focusedLabelColor = Color(0xFF1E90FF),
                cursorColor = Color(0xFF1E90FF)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message (optional)") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1E90FF),
                focusedLabelColor = Color(0xFF1E90FF),
                cursorColor = Color(0xFF1E90FF)
            )
        )



        Spacer(modifier = Modifier.height(24.dp))
        // Add a checkbox with label similar to the one in the image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFCAE8FF), shape = RoundedCornerShape(8.dp)) // Background color with rounded corners
                .padding(16.dp) // Padding inside the Box
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                // Checkbox to toggle the acknowledgment
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF1E90FF), // Set your desired color for the checked state
                        uncheckedColor = Color(0xFF1E90FF) // Set your desired color for the unchecked state
                    )
                )

                // Text next to the checkbox
                Text(
                    text = "I acknowledge that this recipient has auto-deposit enabled. They won't need to answer a security question, and the funds will be deposited automatically.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f), // Make sure the text takes up available space
                    style = TextStyle(
                        lineHeight = 20.sp // Adjust the line height to increase line spacing
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        // Continue button
        Button(
            onClick = {
                if (selectedContact != null) {
                    // Create an Intent to navigate to MainActivity
                    val intent = Intent(context, MainActivity::class.java).apply {
                        // Add a flag to indicate navigation to confirmation screen
                        putExtra("navigate_to_confirmation", true)
                        putExtra("contactName", selectedContact!!.name)
                        putExtra("contactEmail", selectedContact!!.email)
                        putExtra("accountName", selectedAccount)
                        putExtra("accountNumber", selectedAccountNumber)
                        putExtra("transferAmount", transferAmount)
                        putExtra("message", message)
                        putExtra("securityQuestion", securityQuestion)
                        putExtra("securityAnswer", securityAnswer)
                        // Add flags to clear the back stack if needed,
                        // so pressing back from MainActivity doesn't go back to SendMoneyActivity
                        // flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    context.startActivity(intent) // Start MainActivity with the intent
                } else {
                    Toast.makeText(context, "Please select a contact", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                .padding(1.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(
                text = "Continue",
                color = Color.White,
                modifier = Modifier.padding(1.dp)
            )
        }





        Spacer(modifier = Modifier.height(16.dp))

        // Footer Text

    }
    // The SendMoneyConfirmationSheet composable should not be directly called here anymore.
    // It will be displayed by the NavHost in MainActivity when the route is navigated to.
    /*
    if (showConfirmation && selectedContact != null) {
        SendMoneyConfirmationSheet(
            contactName = selectedContact!!.name,
            contactEmail = selectedContact!!.email,
            accountName = selectedAccount,
            accountNumber = selectedAccountNumber,
            transferAmount = transferAmount,
            message = message,
            securityQuestion = if (showSecurityFields) securityQuestion else "",
            securityAnswer = if (showSecurityFields) securityAnswer else "",
            language = "English", // or make this dynamic
            onConfirm = {
                showConfirmation = false
                // 💸 Trigger transaction or navigate forward
            },
            onDismiss = {
                showConfirmation = false
            }
        )
    }
    */


    // **Bottom Sheet - Account Selection**
    if (showAccountSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { showAccountSheet = false },
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
                    showAccountSheet = false
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
                    showAccountSheet = false
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
                    showAccountSheet = false
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



@Preview(showBackground = true)
@Composable
fun SendMoneyScreenPreview() {
    val navController = rememberNavController()
    SendMoney(navController) // Pass it properly!
}
// TransferMoney.kt
package com.example.accbankandroid

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accbankandroid.ui.theme.AccBankAndroidTheme
import com.example.accbankandroid.ui.theme.getGradientBrush
import java.text.SimpleDateFormat
import java.util.*

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import com.example.accbankandroid.ui.theme.loginlight
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import com.google.accompanist.systemuicontroller.rememberSystemUiController
data class TransferConfirmationData(
    val fromAccount: String,
    val toAccount: String,
    val amount: String,
    val paymentType: String,
    val startDate: String,
    val endDate: String?, // null for one-time
    val memo: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferMoneyScreen() {
    var selectedTab by remember { mutableStateOf("My accounts") }
    var isOneTime by remember { mutableStateOf(true) }
    var transferAmount by remember { mutableStateOf(TextFieldValue("")) }
    var memo by remember { mutableStateOf(TextFieldValue("")) }

    val currentDate = SimpleDateFormat("d MMM yyyy", Locale.getDefault()).format(Date())
    var selectedFrequency by remember { mutableStateOf("Weekly") }



    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    val todayDate = remember { calendar.time }
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = todayDate.time // Disable past dates
        }
    }
    var endDate by remember { mutableStateOf("") }
    val endDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val endCalendar = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                endDate = dateFormat.format(endCalendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = calendar.timeInMillis // End date can't be before start date
        }
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // Set status bar color to match TopBarLogo (white)
        systemUiController.setStatusBarColor(
            color = Color.White,
            darkIcons = true // Text and icons will be dark for visibility
        )

        // Set navigation bar transparent
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = true // Change to false if your app background is dark at bottom
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
               //click event
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }

            Text(
                text = "Transfer Money",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f), // This makes the text take the remaining space and align it to the center
                textAlign = TextAlign.Center
            )
        }
//        Text("Transfer money", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF1F1F1))
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTab = "My accounts" }
                    .background(
                        brush = if (selectedTab == "My accounts")
                            getGradientBrush()
                        else
                            SolidColor(Color.Transparent),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "My accounts",
                    color = if (selectedTab == "My accounts") Color.White else Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedTab = "Another member" }
                    .background(
                        brush = if (selectedTab == "Another member")
                            getGradientBrush()
                        else
                            SolidColor(Color.Transparent),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Another member",
                    color = if (selectedTab == "Another member") Color.White else Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (selectedTab == "My accounts") {
            MyAccountsTransferForm() // reuse your current form
        } else {
            AnotherMemberTransferForm() // new form from above
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountsTransferForm() {
    data class BankAccountOption(
        val title: String,
        val accountType: String,
        val accountNumber: String,
        val amount: String
    )

    val accounts = listOf(
        BankAccountOption("No Fee", "Chequing", "100108226953", "51,494.78"),
        BankAccountOption("Spend & Save", "Savings", "100108226954", "123,598.84"),
        BankAccountOption("Travel Fund", "Savings", "100108226955", "3,153.12")
    )

    var selectedFromAccount by remember { mutableStateOf(accounts[0]) }
//    var selectedToAccount by remember { mutableStateOf(accounts[]) }




    var selectedToAccount by remember { mutableStateOf<BankAccountOption?>(null) }
    var isSelectingFrom by remember { mutableStateOf(true) }
    var showAccountSheet by remember { mutableStateOf(false) }

    var isOneTime by remember { mutableStateOf(true) }
    var selectedFrequency by remember { mutableStateOf("Weekly") }
    var transferAmount by remember { mutableStateOf(TextFieldValue("")) }
    var memo by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    val todayDate = remember { calendar.time }
    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            calendar.set(y, m, d)
            selectedDate = dateFormat.format(calendar.time)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).apply {
            datePicker.minDate = todayDate.time
        }
    }

    var endDate by remember { mutableStateOf("") }
    val endDatePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            val endCal = Calendar.getInstance().apply { set(y, m, d) }
            endDate = dateFormat.format(endCal.time)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).apply {
            datePicker.minDate = calendar.timeInMillis
        }
    }

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Transfer From
                // Transfer from
                Text("Transfer from", fontSize = 14.sp, color = Color.Gray)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isSelectingFrom = true
                            showAccountSheet = true
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
                            Text("Transfer from", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(selectedFromAccount.title, fontSize = 18.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                selectedFromAccount.accountNumber,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$${selectedFromAccount.amount}",
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

                Spacer(modifier = Modifier.height(12.dp))

// Transfer to
                Text("Transfer to", fontSize = 14.sp, color = Color.Gray)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F7)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isSelectingFrom = false
                            showAccountSheet = true
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
                            Text("Transfer to", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = selectedToAccount?.title ?: "Select account",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = selectedToAccount?.accountNumber ?: "",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = selectedToAccount?.amount?.let { "$$it" } ?: "--",
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


                // Toggle Buttons
                Spacer(modifier = Modifier.height(16.dp))

//        var isOneTime by remember { mutableStateOf(true) }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ToggleSwitchCard(
                            label = "One-Time",
                            checked = isOneTime,
                            onCheckedChange = { isOneTime = true }
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        ToggleSwitchCard(
                            label = "Recurring",
                            checked = !isOneTime,
                            onCheckedChange = { isOneTime = false }
                        )
                    }
                }

                if (!isOneTime) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Select Frequency",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF1F1F1)),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf("Weekly", "Monthly", "Yearly").forEach { option ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { selectedFrequency = option }
                                    .background(
                                        if (selectedFrequency == option) Color(0xFFE5F0FF) else Color.Transparent
                                    )
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    color = if (selectedFrequency == option) Color(0xFF007AFF) else Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        }

                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = transferAmount,
                    onValueChange = { transferAmount = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter transfer amount") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {}, // Disable manual typing
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pick a date",
                            modifier = Modifier.clickable { datePickerDialog.show() }
                        )
                    },
                    label = { Text("Select date") }
                )



                if (!isOneTime) {
                    // existing Select Frequency...

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = endDate,
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { endDatePickerDialog.show() },
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Pick end date",
                                modifier = Modifier.clickable { endDatePickerDialog.show() }
                            )
                        },
                        label = { Text("End date") }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = memo,
                    onValueChange = { memo = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Memo") }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra("navigate_to_myacc_confirmation", true)
                            putExtra("fromAccountName", selectedFromAccount.title)
                            putExtra("fromAccountNumber", selectedFromAccount.accountNumber)
                            putExtra("toAccountName", selectedToAccount?.title ?: "")
                            putExtra("toAccountNumber", selectedToAccount?.accountNumber ?: "")
                            putExtra("amount", transferAmount.text)
                            putExtra("paymentType", if (isOneTime) "One-Time" else "Recurring")
                            putExtra("date", selectedDate)
                            putExtra("memo", memo.text)
                            putExtra("frequency", if (!isOneTime) selectedFrequency else "")
                            putExtra("endDate", if (!isOneTime) endDate else "")
                        }
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                        .padding(1.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Continue")
                }
            }
        }
    }
    // Bottom Sheet — dynamic filtering
    if (showAccountSheet) {
        val filteredAccounts = if (isSelectingFrom)
            accounts.filter { it.accountNumber != selectedToAccount?.accountNumber }
        else
            accounts.filter { it.accountNumber != selectedFromAccount?.accountNumber }


        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { showAccountSheet = false }
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = if (isSelectingFrom) "Select Transfer From" else "Select Transfer To",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                filteredAccounts.forEach { account ->
                    AccountOption(
                        title = account.title,
                        accountType = account.accountType,
                        accountNumber = account.accountNumber,
                        amount = account.amount,
                        isSelected = (if (isSelectingFrom) selectedFromAccount else selectedToAccount)?.accountNumber == account.accountNumber
                    ) {
                        if (isSelectingFrom) {
                            selectedFromAccount = account
                        } else {
                            selectedToAccount = account
                        }
                        showAccountSheet = false
                    }
                }
            }
        }
    }
}

////////////////////////////////////////////////////
@Composable
fun ToggleSwitchCard(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF4F4F4))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1
            )

            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF007AFF), // iOS Blue
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFD1D1D6) // Light gray
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnotherMemberTransferForm() {
///////contact sheet add//////////////////////////////////
    var searchQuery by remember { mutableStateOf("") }
    var contactList by remember { mutableStateOf<List<TransferContact>>(emptyList()) }

    var selectedAccount by remember { mutableStateOf("No Fee") }
    var selectedAmount by remember { mutableStateOf("51,494.78") }
    var selectedAccountNumber by remember { mutableStateOf("100108226953") }
//
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showAccountSheet by remember { mutableStateOf(false) }
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
    var showContactSheet by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf<TransferContact?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ///////////contact sheet content ////////////////////


    var isOneTime by remember { mutableStateOf(true) }
    var memberTransferAmount by remember { mutableStateOf(TextFieldValue("")) }
    var memberMemo by remember { mutableStateOf(TextFieldValue("")) }
    var selectedFrequency by remember { mutableStateOf("Weekly") }

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    val todayDate = remember { calendar.time }
    val context = LocalContext.current

    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = todayDate.time
        }
    }

    var endDate by remember { mutableStateOf("") }
    val endDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val endCalendar = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                endDate = dateFormat.format(endCalendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = calendar.timeInMillis
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Transfer from", fontSize = 14.sp, color = Color.Gray)
                // "Transfer from" dropdown option
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

                Text("Select  Recipient", fontSize = 14.sp, color = Color.Gray)

                if (showContactSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showContactSheet = false },
                        sheetState = sheetState,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp) // Fixed height
                    ) {
                        val scrollState = rememberScrollState()

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .verticalScroll(scrollState) // Make it scrollable
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ToggleSwitchCard(
                            label = "One-Time",
                            checked = isOneTime,
                            onCheckedChange = { isOneTime = true }
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        ToggleSwitchCard(
                            label = "Recurring",
                            checked = !isOneTime,
                            onCheckedChange = { isOneTime = false }
                        )
                    }
                }

                if (!isOneTime) {
                    Text("Select Frequency", fontSize = 14.sp, color = Color.Gray)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF1F1F1)),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf("Weekly", "Monthly", "Yearly").forEach { option ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { selectedFrequency = option }
                                    .background(
                                        if (selectedFrequency == option) Color(0xFFE5F0FF) else Color.Transparent
                                    )
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    color = if (selectedFrequency == option) Color(0xFF007AFF) else Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = memberTransferAmount,
                    onValueChange = { memberTransferAmount = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter transfer amount") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Pick date",
                            modifier = Modifier.clickable { datePickerDialog.show() }
                        )
                    },
                    label = { Text("Select date") }
                )

                if (!isOneTime) {
                    OutlinedTextField(
                        value = endDate,
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { endDatePickerDialog.show() },
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Pick end date",
                                modifier = Modifier.clickable { endDatePickerDialog.show() }
                            )
                        },
                        label = { Text("End date") }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = memberMemo,
                    onValueChange = { memberMemo = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Memo") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra("navigate_to_member_confirmation", true)
                            putExtra("fromAccountName", selectedAccount)
                            putExtra("fromAccountNumber", selectedAccountNumber)
                            putExtra("toMemberName", selectedContact?.name ?: "")
                            putExtra("toMemberEmail", selectedContact?.email ?: "")
                            putExtra("amount", memberTransferAmount.text)
                            putExtra("paymentType", if (isOneTime) "One-Time" else "Recurring")
                            putExtra("date", selectedDate)
                            putExtra("memo", memberMemo.text)
                            putExtra("frequency", if (!isOneTime) selectedFrequency else "")
                            putExtra("endDate", if (!isOneTime) endDate else "")
                        }
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                        .padding(1.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Continue")
                }
            }
        }
    }
}

// Updated ToggleBox
@Composable
fun ToggleBox(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0xFFE5F0FF) else Color(0xFFF1F1F1))
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = if (selected) Color(0xFF007AFF) else Color.Black)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTransferMoneyScreen() {
    AccBankAndroidTheme {
        TransferMoneyScreen()
    }
}

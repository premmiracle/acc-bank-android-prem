package com.example.accbankandroid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyConfirmationSheet(
    contactName: String,
    contactEmail: String,
    accountName: String,
    accountNumber: String,
    transferAmount: String,
    message: String,
    securityQuestion: String?,
    securityAnswer: String?,
    language: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Confirmation", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                IconButton(onClick = { onDismiss() }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Are you sure you want to continue with the transfer?")

            Spacer(modifier = Modifier.height(20.dp))

            ConfirmationItem(label = "Recipient Name", value = contactName)
            ConfirmationItem(label = "Recipient Email", value = contactEmail)
            ConfirmationItem(label = "Transfer From", value = accountName)
            ConfirmationItem(label = "Account Number", value = accountNumber)
            ConfirmationItem(label = "Amount", value = "$$transferAmount")

            if (message.isNotBlank()) {
                ConfirmationItem(label = "Message", value = message)
            }

            if (!securityQuestion.isNullOrBlank()) {
                ConfirmationItem(label = "Security Question", value = securityQuestion)
            }

            if (!securityAnswer.isNullOrBlank()) {
                ConfirmationItem(label = "Security Answer", value = securityAnswer)
            }

            ConfirmationItem(label = "Language", value = language)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onConfirm() },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
            ) {
                Text("Confirm", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ConfirmationItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 13.sp, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}

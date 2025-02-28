package com.example.accbankandroid.MoveMoney

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
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

@Composable
fun SendMoney(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFFAF9F6)), // background color for the screen
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        // Back button
//        IconButton(onClick = { navController.popBackStack() }) {
//            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
//        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start, // Align to the left
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Title Text
        Text(
            text = "Send Money",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // "Transfer from" dropdown
        OutlinedTextField(
            value = "Chequing - 100108226953", // Example value, change it dynamically
            onValueChange = {},
            label = { Text("Transfer from") },
            readOnly = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow", tint = Color.Gray)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "Send to" dropdown
        OutlinedTextField(
            value = "John Doe", // Example value, change it dynamically
            onValueChange = {},
            label = { Text("Send to") },
            readOnly = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow", tint = Color.Gray)
            }
        )

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
}

@Preview(showBackground = true)
@Composable
fun SendMoneyScreenPreview() {
    val navController = rememberNavController() // Mock NavController for preview
    SendMoney(navController)
}

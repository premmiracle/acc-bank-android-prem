package com.example.accbankandroid

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun contactdropdown() {
    var selectedRecipient by remember { mutableStateOf<Contact?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }  // ✅ Initialize selectedIndex properly

    // Mock Contact List (Replace with API or Database Fetch)
    val contacts = listOf(
        Contact("Sam Peter", "sam_test@gmail.com", "(416)555-1234"),
        Contact("John Doe", "john@example.com", "123-456-7890"),
        Contact("Jane Smith", "jane@example.com", "987-654-3210"),
        Contact("Mike Johnson", "mike@example.com", "555-666-7777")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(Color.White)
    ) {
        // SEND TO Label
        Text(
            text = "SEND TO",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(5.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedRecipient?.name?.let { name ->
                    val nameParts = name.split(" ")
                    val firstName = nameParts.getOrNull(0)?.replaceFirstChar { it.uppercaseChar() } ?: ""
                    val remainingName = nameParts.drop(1).joinToString(" ")
                    if (remainingName.isNotEmpty()) "$firstName $remainingName" else firstName
                } ?: "Select Recipient",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.weight(1f) // Pushes icon to the end

            )

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown Icon"
            )
        }
        selectedRecipient?.let {
            Text(it.email, fontSize = 16.sp, color = Color.Gray, fontFamily = FontFamily.SansSerif)
            Text(it.phone, fontSize = 16.sp, color = Color.Gray, fontFamily = FontFamily.SansSerif)
        }

        // Dropdown Menu for Contact Selection
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)

        ) {
            contacts.forEach { contact ->
                DropdownMenuItem(
                    onClick = {
                        selectedRecipient = contact
                        expanded = false
                    },
                    text = {
                        Column {
                            Text(contact.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                            Text(contact.email, fontSize = 16.sp, color = Color.Gray, fontFamily = FontFamily.SansSerif)
                            Text(contact.phone, fontSize = 16.sp, color = Color.Gray, fontFamily = FontFamily.SansSerif)
                        }
                    }
                )
            }
        }
    }
}

data class Contact(val name: String, val email: String, val phone: String)
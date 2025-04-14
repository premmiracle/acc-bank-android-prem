package com.example.accbankandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.accbankandroid.ui.theme.getGradientBrush


data class TransferContact(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val email: String,
    val sendByEmail: Boolean,
    val sendByPhone: Boolean,
    val countryCode: String, // Add the missing countryCode field
    val mobilePhone: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(context: Context) {
    // States for input fields
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("+1") }
    var mobilePhone by remember { mutableStateOf("") }
    var sendByEmail by remember { mutableStateOf(false) }
    var sendByPhone by remember { mutableStateOf(false) }

    val fileName = "contacts.json"
    val file = File(context.filesDir, fileName)

    // Function to load contacts from JSON file
    fun loadContacts(context: Context, file: File): MutableList<TransferContact> {
        if (!file.exists()) {
            file.createNewFile() // If the file doesn't exist, create it
            return mutableListOf() // Return an empty list
        }

        val jsonString = file.readText() // Read the content of the file
        val type: Type = object : TypeToken<MutableList<TransferContact>>() {}.type
        return Gson().fromJson(jsonString, type) ?: mutableListOf() // Deserialize JSON into a list, return empty list if null
    }

    // Function to save contact data to JSON file
    fun saveContactData(contact: TransferContact) {
        val contactList = loadContacts(context, file)

        // Add new contact to the list
        contactList.add(contact)

        // Save to the file
        val jsonString = Gson().toJson(contactList)
        file.writeText(jsonString)

        Toast.makeText(context, "Contact Saved", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize() // Ensure the column fills the whole screen
            .padding(16.dp), // Background color for the screen
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Add Recipient",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Name field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Nickname field (optional)
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Nick name (Optional)") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Phone number field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Country Code and Mobile Phone number
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Country code with increment and decrement functionality
            OutlinedTextField(
                value = countryCode,
                onValueChange = { countryCode = it },
                label = { Text("+ Country") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                trailingIcon = {
                    // Button to increment the country code
                    IconButton(onClick = {
                        // Increment logic (example)
                        if (countryCode.length < 4) {
                            countryCode = "+${countryCode.substring(1).toInt() + 1}"
                        }
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Increase Country Code")
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            // Mobile phone number field
            OutlinedTextField(
                value = mobilePhone,
                onValueChange = { mobilePhone = it },
                label = { Text("Mobile Phone Number") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(2f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

// Send transfers by Email switch
        Row(
            modifier = Modifier.fillMaxWidth(), // Make the Row take up the full width
            horizontalArrangement = Arrangement.SpaceBetween, // Align the text on the left and switch on the right
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Send transfers by Email",
                modifier = Modifier.padding(end = 8.dp) // Add padding to the text for some space from the switch
            )
            Switch(
                checked = sendByEmail,
                onCheckedChange = { sendByEmail = it }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

// Send transfers by Mobile phone switch
        Row(
            modifier = Modifier.fillMaxWidth(), // Make the Row take up the full width
            horizontalArrangement = Arrangement.SpaceBetween, // Align the text on the left and switch on the right
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Send transfers by Mobile phone",
                modifier = Modifier.padding(end = 8.dp) // Add padding to the text for some space from the switch
            )
            Switch(
                checked = sendByPhone,
                onCheckedChange = { sendByPhone = it }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        // Review TransferContact button
        Button(
            onClick = {
                val newContact = TransferContact(
                    name = name,
                    nickname = nickname,
                    phoneNumber = phoneNumber,
                    email = email,
                    countryCode = countryCode,
                    mobilePhone = mobilePhone,
                    sendByEmail = sendByEmail,
                    sendByPhone = sendByPhone
                )
                saveContactData(newContact) // Save the TransferContact
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp)) // Apply gradient with rounded corners
                .padding(1.dp), // Optional padding for spacing around the button
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Set container color to transparent to show gradient
        ) {
            Text("Review Recipient", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddContactScreenPreview() {
    AddContactScreen(context = LocalContext.current)
}

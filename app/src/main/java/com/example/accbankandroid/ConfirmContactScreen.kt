package com.example.accbankandroid

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.UUID

@Composable
fun LabelWithValue(label: String, value: String) {
    Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Gray)
    Text(
        text = value,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun ConfirmContactScreen(
    name: String,
    nickname: String,
    phoneNumber: String,
    email: String,
    countryCode: String,
    mobilePhone: String,
    sendByEmail: Boolean,
    sendByPhone: Boolean
) {
    val context = LocalContext.current

    val sendBy = buildString {
        if (sendByEmail) append("Email")
        if (sendByEmail && sendByPhone) append(" & ")
        if (sendByPhone) append("Phone")
    }
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
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Confirmation",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = {
                if (context is Activity) {
                    context.finish() // This will close the current activity
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Text("Are you sure you want to add this contact ?", fontSize = 15.sp)
        Spacer(modifier = Modifier.height(14.dp))
        LabelWithValue(label = "Name", value = name)
        LabelWithValue(label = "Email", value = email)
        LabelWithValue(label = "Account Number", value = phoneNumber)
        LabelWithValue(label = "Mobile Phone", value = "$countryCode $mobilePhone")
        LabelWithValue(label = "Send transfers by", value = sendBy)
        LabelWithValue(label = "Nick name (Optional)", value = nickname)
        LabelWithValue(label = "Language", value = "Thy") // Replace "Thy" with dynamic if needed

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val newContact = TransferContact(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    nickname = nickname,
                    phoneNumber = phoneNumber,
                    email = email,
                    countryCode = countryCode,
                    mobilePhone = mobilePhone,
                    sendByEmail = sendByEmail,
                    sendByPhone = sendByPhone
                )

                val file = File(context.filesDir, "contacts.json")
                val listType = object : TypeToken<MutableList<TransferContact>>() {}.type
                val contactList: MutableList<TransferContact> = if (file.exists()) {
                    Gson().fromJson(file.readText(), listType) ?: mutableListOf()
                } else {
                    mutableListOf()
                }

                contactList.add(newContact)
                file.writeText(Gson().toJson(contactList))
                Toast.makeText(context, "Contact saved", Toast.LENGTH_SHORT).show()
                (context as? Activity)?.finish()
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(getGradientBrush(), shape = RoundedCornerShape(10.dp))
                .padding(1.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text("Confirm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmContactScreen() {
    ConfirmContactScreen(
        name = "John Doe",
        nickname = "Johnny",
        phoneNumber = "1234567890",
        email = "john@example.com",
        countryCode = "+1",
        mobilePhone = "9876543210",
        sendByEmail = true,
        sendByPhone = false
    )
}

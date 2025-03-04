package com.example.accbankandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SendMoneyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Your composable content for SendMoneyScreen
            SendMoney()
        // Add your SendMoney UI here
        }
    }
    // Handle back button press to go back to the previous activity
    override fun onBackPressed() {
        super.onBackPressed() // This will navigate back to the previous activity in the stack
    }

}

package com.example.accbankandroid.CommonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accbankandroid.R // Adjust if your logo is in a different package

@Composable
fun TopBarLogo() {



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            shape = RectangleShape, // 🔹 removes the curves
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(), // 10dp from bottom of card
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp)) // space from top if needed

            Image(
                painter = painterResource(id = R.drawable.acceinfo_logo), // Replace with your actual logo resource
                contentDescription = "App Logo",
                modifier = Modifier
                    .padding(bottom = 5.dp) // 5dp from logo to bottom
                    .width(180.dp) // Increased width
                    .height(40.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TopBarLogoPreview() {
    Column(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
        TopBarLogo()
    }
}
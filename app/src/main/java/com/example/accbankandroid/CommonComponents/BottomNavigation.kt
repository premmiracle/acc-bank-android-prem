package com.example.accbankandroid.CommonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accbankandroid.ui.theme.loginlight

@Composable
fun BottomNavigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Black), // ✅ Dark Background
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val icons = listOf(
                Icons.Filled.AccountBalance, // ✅ Bank Icon
                Icons.Filled.CreditCard,     // ✅ Credit Card Icon
                Icons.Filled.AttachMoney,    // ✅ Currency Icon
                Icons.Filled.Settings        // ✅ Settings Icon
            )

            icons.forEachIndexed { index, icon ->
                if (index == 2) {
                    // ✅ Space for Floating Action Button (FAB)
                    Spacer(modifier = Modifier.width(50.dp))
                } else {
                    Icon(
                        imageVector = icon,
                        contentDescription = null, // Static UI, no need for accessibility
                        tint = Color.Gray, // ✅ Static gray color for all icons
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // ✅ Floating Action Button (FAB) in the Center
        FloatingActionButton(
            onClick = {}, // No action, since it's static
            containerColor = loginlight, // ✅ Blue Color for FAB
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-30).dp), // ✅ Moves FAB up to create curve effect
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.Add, // ✅ Default "+" icon
                contentDescription = null, // No action, so no need for content description
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigation() {
    BottomNavigation()
}

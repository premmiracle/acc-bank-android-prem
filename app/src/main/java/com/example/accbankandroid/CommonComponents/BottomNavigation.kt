package com.example.accbankandroid.CommonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.accbankandroid.R
import androidx.compose.ui.res.painterResource

@Composable
fun BottomNavigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp) // Increased height for better FAB alignment
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) // ✅ Adds curve
            .background(Color.Transparent), // Transparent for floating effect
        contentAlignment = Alignment.BottomCenter
    ) {
        // ✅ Bottom Bar with Rounded Corners
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) // ✅ Adds curve
                .background(Color.Black), // ✅ Dark Background
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp), // ✅ Adds spacing
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val images = listOf(
                    R.drawable.bankbottom,
                    R.drawable.cardbottom,
                    null, // ✅ Space for FAB
                    R.drawable.currencybottom,
                    R.drawable.settingicon
                )

                images.forEach { image ->
                    if (image == null) {
                        Spacer(modifier = Modifier.width(60.dp)) // ✅ Adds space for FAB
                    } else {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        // ✅ Floating Action Button (FAB) Positioned in the Center of the Row
        FloatingActionButton(
            onClick = {}, // No action, static FAB
            containerColor = loginlight, // ✅ Blue Color for FAB
            modifier = Modifier
                .size(60.dp) // ✅ Bigger for better visibility
                .offset(y = (0).dp) // ✅ Moves FAB up slightly
                .align(Alignment.Center), // ✅ Ensures it aligns within the Row
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
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

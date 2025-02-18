package com.example.accbankandroid

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accbankandroid.CommonComponents.BottomNavigation
import com.example.accbankandroid.ui.theme.getGradientBrush
import com.example.accbankandroid.ui.theme.loginlight
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountOverview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .systemBarsPadding() // ✅ Prevents overlap with system navigation bar
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Welcome Danielle to\nAcceinfo Bank",
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Good Afternoon",
                    color = Color.White,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // ✅ Static Bottom Navigation UI
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.Black)
                .imePadding(), // ✅ Ensures padding if keyboard/system bars are present
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val icons = listOf(
                    Icons.Filled.AccountBalance,
                    Icons.Filled.CreditCard,
                    Icons.Filled.AttachMoney,
                    Icons.Filled.Settings
                )

                icons.forEach { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = null, // Static UI, no need for accessibility
                        tint = Color.Gray, // ✅ Static gray color for all icons
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // ✅ Floating Action Button (FAB)
            FloatingActionButton(
                onClick = {}, // No action, since it's static
                containerColor = loginlight,
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-30).dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null, // No action, so no need for content description
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountOverviewScreen() {
    AccountOverview()
}



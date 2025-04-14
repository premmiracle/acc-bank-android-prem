package com.example.accbankandroid.CommonComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.accbankandroid.NavigationRoutes

@Composable
fun BottomNavigation(navController: NavController) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.Black), // ✅ Slight transparency to blend with the background
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val items = listOf(
                    NavigationRoutes.AccountOverview.route to R.drawable.bankbottom,
                    NavigationRoutes.MoveMoney.route to R.drawable.cardbottom,
                    null to null, // Space for FAB
                    NavigationRoutes.PhoneNumberInputScreen.route to R.drawable.currencybottom,
                    NavigationRoutes.AddContact.route to R.drawable.settingicon
                )

                items.forEach { (route, image) ->
                    if (image == null) {
                        Spacer(modifier = Modifier.width(60.dp)) // Space for FAB
                    } else {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = route ?: "Navigation Icon",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable {
                                    route?.let { navController.navigate(it) }
                                }
                        )
                    }
                }
            }

            // ✅ Floating Action Button (FAB)
            FloatingActionButton(
                onClick = {}, // Define FAB action if needed
                containerColor = loginlight,
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = (0).dp)
                    .align(Alignment.Center),
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
    val navController = rememberNavController() // Mock NavController for preview
    BottomNavigation(navController)
}




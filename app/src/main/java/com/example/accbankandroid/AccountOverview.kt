package com.example.accbankandroid

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.runtime.*
import androidx.compose.ui.zIndex


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountOverview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientBrush())
            .systemBarsPadding() // ✅ Prevent overlap with system bars
    ) {
        // ✅ Profile & Greeting Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Allow it to take appropriate space
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // ✅ Profile Image
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ✅ Welcome Text
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

                // ✅ Greeting Text
                Text(
                    text = "Good Afternoon",
                    color = Color.White,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                AccountAndCardsSection()

            }
        }



        // ✅ Bottom Navigation UI
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.Black)
                .imePadding(),
            contentAlignment = Alignment.Center
        ) {
            BottomNavigation()
        }
    }
}


@Composable
fun AccountAndCardsSection() {
    var selectedAccount by remember { mutableStateOf("Cards(3456)") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween, // ✅ Pushes Row to top & LazyRow to center
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // ✅ Row should be at the **top**
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
//                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AccountItem(
                            title = "Savings(1234)",
                            amount = "USD 1245.45",
                            icon = R.drawable.bankicon,
                            isSelected = selectedAccount == "Savings(1234)"
                        ) { selectedAccount = "Savings(1234)" }

                        AccountItem(
                            title = "Cards(3456)",
                            amount = "USD 2000.45",
                            icon = R.drawable.cardicon,
                            isSelected = selectedAccount == "Cards(3456)"
                        ) { selectedAccount = "Cards(3456)" }

                        AccountItem(
                            title = "Loan(9999)",
                            amount = "USD -555.45",
                            icon = R.drawable.currencyicon,
                            isSelected = selectedAccount == "Loan(9999)"
                        ) { selectedAccount = "Loan(9999)" }
                    }

                    // ✅ Show extra section when an account is selected
                    if (selectedAccount == "Cards(3456)") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f), // ✅ Pushes LazyRow to center
                            contentAlignment = Alignment.Center // ✅ Center align LazyRow
                        ) {
                            // ✅ Scrollable Credit Card Section (now overlaps the top section slightly)
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center, // ✅ Center the cards
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                val cardImages = listOf(
                                    R.drawable.creditcard,
                                    R.drawable.creditcard,
                                    R.drawable.creditcard
                                )

                                items(cardImages) { cardImage ->
                                    CreditCardItem(cardImage)
                                }
                            }
                        }
                    }
                }
            }
        }

}






// ✅ Account Item Component (For Savings, Chequing, Loan)
@Composable
fun AccountItem(
    title: String,
    amount: String,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() } // ✅ Click to select
    ) {
        // ✅ Wrap everything inside a Box
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            // ✅ Main Icon Box
            Box(
                modifier = Modifier
                    .size(60.dp) // ✅ Adjusted size
                    .background(Color.White, shape = CircleShape)
                    .border(2.dp, Color.Gray, CircleShape), // ✅ Grey border
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }

            // ✅ Checkmark Box Below
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(30.dp) // ✅ Background circle size
                        .align(Alignment.BottomEnd) // ✅ Align below the icon box
                        .offset(y = 1.dp), // ✅ Move slightly down
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp) // ✅ Background size
                            .background(
                                brush = Brush.linearGradient(listOf(Color.Cyan, Color.Blue)),
                                shape = CircleShape
                            )
                            .border(2.dp, Color.White, CircleShape) // ✅ White Border
                    )

                    Icon(
                        imageVector = Icons.Default.Check, // ✅ Check icon
                        contentDescription = "Selected",
                        tint = Color.White, // ✅ White check inside
                        modifier = Modifier.size(16.dp) // ✅ Proper size
                    )
                }
            }
        }

        // ✅ Text stays below
        Text(text = title, fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = amount, fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
    }
}







@Composable
fun CreditCardItem(cardImage: Int) {
    Card(
        modifier = Modifier
            .size(width = 300.dp, height = 180.dp) // ✅ Set width and height for visibility
            .offset(y = (-10).dp)  // ✅ Slightly move up
            .padding(end = 20.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp) // ✅ Elevation for shadow effect
    ) {
        Image(
            painter = painterResource(id = cardImage),
            contentDescription = "Credit Card",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // ✅ Ensure image covers the entire card
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAccountOverviewScreen() {
    AccountOverview()
}



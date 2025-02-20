package com.example.accbankandroid

import android.content.res.Configuration
import android.text.style.BackgroundColorSpan
import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Label
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SendMoneyScreen() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(if (isLandscape) 30.dp else 16.dp) // Adjust padding based on orientation
    ) {
        if (isLandscape) {
            Row(modifier = Modifier.fillMaxSize()) {
                SendMoney(modifier = Modifier.weight(4f)) // First Column

            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                SendMoney()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun SendMoney(modifier: Modifier = Modifier){

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            ),
            title = {
                Text("Send Money", fontSize = 20.sp, fontWeight = FontWeight.Bold,fontFamily = FontFamily.SansSerif)
            },
            navigationIcon={
                IconButton(onClick = { /* Handle back */ }){
                    Box(
                        modifier = Modifier
                            .size(40.dp)  // Circle size
                            .background(Color.Black),  // Black background
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White  // White icon color
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = {/* Handle close */}) {
                    Box(modifier = Modifier
                        .size(40.dp)  // Circle size
                        .background(Color.Black),  // Black background
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White  // White icon color
                        )
                    }
                }
            }
        )
        var selectedTabIndex by remember { mutableStateOf(0) }
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.padding(16.dp)
                .background(color = Color.White)

        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color.White),

                indicator = {}
            ) {
                listOf("New", "History").forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .then(
                                if (selectedTabIndex == index) {
                                    Modifier.background(Brush.verticalGradient(listOf(Color.Cyan,Color.Blue)))
                                } else {
                                    Modifier.background(Color.White)
                                }
                            )
                            .padding(vertical = 10.dp, horizontal = 30.dp)
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
        when (selectedTabIndex) {
            0 -> NewTabContent() // Show this when "New" is selected
            1 -> HistoryTabContent() // Show this when "History" is selected
        }
    }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTabContent() {

    var amount by remember { mutableStateOf("$503.66") }
    var notifyMethod by remember { mutableStateOf("Email & SMS") }
    var securityquestion by remember { mutableStateOf("Question") }
    var securityanswer by remember { mutableStateOf("***********") }
    var confirmsecurityanswer by remember { mutableStateOf("***********") }
    var message by remember { mutableStateOf("") } // State to store the input text
    val minLength = 250 // Minimum length for the message

    val scrollState = rememberScrollState()//scroll

        Spacer(modifier = Modifier.padding(3.dp))
        Text(
            text = ("VIA INTERAC E-TRANSFER"),
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth().padding(start = 21.dp) // Ensures alignment works
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Makes content scrollable
                .padding(10.dp) // Adds outer padding
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),  // Adds padding around the card
                shape = RoundedCornerShape(22.dp),  // Rounded corners
                colors = CardDefaults.cardColors(containerColor = Color.White),  // White background
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),  // Adds a slight shadow
                border = BorderStroke(1.dp, Color.LightGray) , // Adds a subtle border
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "FROM",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp  // Adjust this value as needed for spacing
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Chequing", fontSize = 18.sp, fontWeight = FontWeight.Bold,fontFamily = FontFamily.SansSerif)
                            Text(
                                "$1245.45",
                                fontSize = 16.sp,
                                color = Color(0xFF2F8F59),
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                letterSpacing = 1.sp
                            )
                        }
                        Text("******1234", fontSize = 16.sp, color = Color.Gray,fontFamily = FontFamily.SansSerif)
                    }
                    Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "SEND TO ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp  // Adjust this value as needed for spacing
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text("SAM Peter", fontSize = 18.sp, fontWeight = FontWeight.Bold,fontFamily = FontFamily.SansSerif)
                        Text("sam_test@gmail.com", fontSize = 16.sp, color = Color.Gray,fontFamily = FontFamily.SansSerif)
                        Text("(416) 555-1234", fontSize = 16.sp, color = Color.Gray,fontFamily = FontFamily.SansSerif)
                    }
                    Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                    Divider(color = Color.Gray, thickness = 1.dp)
                    //Spacer(modifier = Modifier.height(10.dp))

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = amount,
                        onValueChange = {
                            amount = it.filter { char -> char.isDigit() || char == '.' } // Allow only numbers and decimals
                        },
                        label = { Text("HOW MUCH",fontFamily = FontFamily.SansSerif) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black // Ensure it's readable
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number // Opens numeric keyboard
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Set background to pure white
                            focusedIndicatorColor = Color.Transparent, // Remove underline
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                NotifyRecipientDropdown()

                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = securityquestion,
                        onValueChange = { securityquestion = it },
                        label = { Text("SECURITY QUESTION",fontFamily = FontFamily.SansSerif) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black // Ensure it's readable
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Set background to pure white
                            focusedIndicatorColor = Color.Transparent, // Remove underline
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = securityanswer,
                        onValueChange = { securityanswer = it },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text("SECURITY ANSWER",fontFamily = FontFamily.SansSerif) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black // Ensure it's readable
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Set background to pure white
                            focusedIndicatorColor = Color.Transparent, // Remove underline
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = confirmsecurityanswer,
                        onValueChange = { confirmsecurityanswer = it },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text("CONFIRM SECURITY ANSWER",fontFamily = FontFamily.SansSerif) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black // Ensure it's readable
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Set background to pure white
                            focusedIndicatorColor = Color.Transparent, // Remove underline
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("MESSAGE") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        textStyle = TextStyle(fontSize = 16.sp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Optional (Maximum Characters 250)", color = Color.Gray) }
                    )
                    Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /* Handle click */ },
                shape = RoundedCornerShape(20.dp), // Rounded corners
                border = BorderStroke(1.dp, Color.White), // White border
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent // Button background is transparent
                ),
                modifier = Modifier
                    .height(70.dp) // Button height
                    .width(170.dp) // Button width
                    .align(Alignment.CenterHorizontally) // Center horizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize() // Ensure gradient covers entire button
                        .background(Brush.verticalGradient(listOf(Color.Cyan, Color.Blue)), shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center // Center text
                ) {
                    Text("✔ Send", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp,fontFamily = FontFamily.SansSerif)
                }
            }

        }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifyRecipientDropdown() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Email & SMS") }

    val options = listOf("Email", "SMS", "Email & SMS")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Notify Recipient By",fontFamily = FontFamily.SansSerif) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .menuAnchor(),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif
            ),
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White) // White dropdown background
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (selectedOption == option) Icons.Default.Check else Icons.Default.MailOutline,
                                contentDescription = "Option Icon",
                                tint = if (selectedOption == option) Color.Blue else Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedOption == option) Color.Blue else Color.Black
                            )
                        }
                    },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (selectedOption == option) Color(0xFFE0F7FA) else Color.White)

                )
            }
        }
    }
}


@Composable
fun HistoryTabContent()
{

}
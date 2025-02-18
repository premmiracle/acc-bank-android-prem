package com.example.accbankandroid

import android.renderscript.Sampler.Value
import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Label
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun SendMoney(){

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text("Send Money", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
                    .background(color = Color.Transparent),

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
                                    Modifier.background(Color.Transparent)
                                }
                            )
                            .padding(vertical = 10.dp, horizontal = 30.dp)
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray,
                            fontWeight = FontWeight.Bold
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
        Spacer(modifier = Modifier.height(10.dp))
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
                shape = RoundedCornerShape(20.dp),  // Rounded corners
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
                            letterSpacing = 2.sp  // Adjust this value as needed for spacing
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Chequing", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(
                                "$1245.45",
                                fontSize = 16.sp,
                                color = Color.Green,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text("******1234", fontSize = 16.sp, color = Color.Gray)
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
                            letterSpacing = 2.sp  // Adjust this value as needed for spacing
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text("SAM Peter", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("sam_test@gmail.com", fontSize = 16.sp, color = Color.Gray)
                        Text("(416) 555-1234", fontSize = 16.sp, color = Color.Gray)
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
                        onValueChange = { amount = it },
                        label = { Text("HOW MUCH") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
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
                Divider(color = Color.Gray, thickness = 1.dp)

                NotifyRecipientDropdown()

                Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = securityquestion,
                        onValueChange = { securityquestion = it },
                        label = { Text("SECURITY QUESTION") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
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
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = securityanswer,
                        onValueChange = { securityanswer = it },
                        label = { Text("SECURITY ANSWER") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
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
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextField(
                        value = confirmsecurityanswer,
                        onValueChange = { confirmsecurityanswer = it },
                        label = { Text("CONFIRM SECURITY ANSWER") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle( // Apply bold styling
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
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
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("MESSAGE") },
                        placeholder = { Text("Optional (Maximum Characters 250)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp) // Adjust height to match the image
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(12.dp)
                            ), // Rounded box
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Set background to pure white
                            focusedIndicatorColor = Color.Transparent, // Remove underline
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        maxLines = 4
                    )
                    Spacer(modifier = Modifier.height(10.dp)) // Space before the line
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {/* Handle click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(48.dp), // Rounded corners
                border = BorderStroke(1.dp, color = Color.White),
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(listOf(Color.Cyan, Color.Blue)),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .height(50.dp) // Smaller height
                    .width(120.dp) // Adjust width as needed
                    .align(Alignment.CenterHorizontally) // Centers horizontally
                    .padding(vertical = 120.dp)

            ) {
                Text(" ✔ Send", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
            label = { Text("Notify Recipient By") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .menuAnchor(),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
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
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun HistoryTabContent()
{

}
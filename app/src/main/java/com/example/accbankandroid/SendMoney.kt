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
        when (selectedTabIndex) {
            0 -> NewTabContent() // Show this when "New" is selected
            1 -> HistoryTabContent() // Show this when "History" is selected
        }

    }
}


@Composable
fun HistoryTabContent()
{

}
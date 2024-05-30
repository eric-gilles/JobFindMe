package com.example.jobfindme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyOffer(
    modifier: Modifier = Modifier,
    firestore: FirebaseFirestore,
    firebaseAuth: FirebaseAuth,
    navController: NavController
) {
    var jobTitle by remember { mutableStateOf("") }
    var startingDate by remember { mutableStateOf("") }
    var endingDate by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(width = 376.dp)
            .requiredHeight(height = 815.dp)
    ) {
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .requiredWidth(width = 376.dp)
                .requiredHeight(height = 815.dp)
                .background(color = Color(0xfff6f6f6))
        ) {
            CrossedCirclesShapeBlue()
            Text(
                text = "Modify the Offer",
                color = Color.Black,
                lineHeight = 4.82.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-1).dp, y = 100.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 26.dp, y = 110.dp)
                .requiredWidth(width = 327.dp)
                .requiredHeight(height = 607.dp)
        ) {
            // Job Title Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 13.dp, y = 46.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = jobTitle,
                    onValueChange = { jobTitle = it },
                    placeholder = { Text("Job Title") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray
                    )
                )
            }

            // Starting Date Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 120.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 297.dp)
                        .requiredHeight(height = 70.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                )
                TextField(
                    value = startingDate,
                    onValueChange = { startingDate = it },
                    placeholder = { Text("Starting Date") },
                    modifier = Modifier.fillMaxSize()
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "calendar_month",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 10.dp)
                )
            }

            // Ending Date Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 195.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 297.dp)
                        .requiredHeight(height = 50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                )
                TextField(
                    value = endingDate,
                    onValueChange = { endingDate = it },
                    placeholder = { Text("Ending Date") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "calendar_month",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 10.dp)
                )
            }

            // Address Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 269.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text("Address") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 10.dp)
                )
            }

            // Salary Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 343.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = salary,
                    onValueChange = { salary = it },
                    placeholder = { Text("Salary") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
            }

            // Email Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 415.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
            }

            // Phone Number Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 485.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = { Text("Phone Number") },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
            }

            // Description Box
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 550.dp)
                    .requiredWidth(width = 295.dp)
                    .requiredHeight(height = 104.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 295.dp)
                        .requiredHeight(height = 104.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color.White)
                ) {
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text("Description") },
                        modifier = Modifier.fillMaxSize(),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Gray,
                            unfocusedLabelColor = Color.Gray
                        )
                    )
                }
            }

            // Create Offer Button
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 670.dp)
                    .requiredWidth(width = 327.dp)
                    .requiredHeight(height = 64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 327.dp)
                        .requiredHeight(height = 64.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color(0xff50c2c9))
                )
            }
            Text(
                text = "Modify Offer",
                color = Color.Black,
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp, y = 680.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp, y = 57.dp)
                .requiredWidth(width = 32.dp)
                .requiredHeight(height = 29.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = Color.White)
                .border(
                    border = BorderStroke(8.dp, Color.White),
                    shape = MaterialTheme.shapes.small
                )
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back_arrow",
                tint = Color.Black,
                modifier = Modifier.fillMaxSize()
                    .requiredWidth(width = 30.dp)
                    .requiredHeight(height = 25.dp)
            )
        }
    }
}
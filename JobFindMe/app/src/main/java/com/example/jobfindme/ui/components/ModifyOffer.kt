package com.example.jobfindme.ui.components

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.data.EmployerOutput
import com.example.jobfindme.data.Offer
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.data.toOfferOutput
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyOffer(
    modifier: Modifier = Modifier,
    firestore: FirebaseFirestore,
    firebaseAuth: FirebaseAuth,
    navController: NavController,
    sharedOfferViewModel: SharedOfferViewModel
) {
    var jobTitle by remember { mutableStateOf("") }
    var jobName by remember { mutableStateOf("") }

    val startingDate = remember { mutableStateOf(LocalDate.now()) }
    val endingDate = remember { mutableStateOf(LocalDate.now()) }
    var city by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val open = remember { mutableStateOf(false) }
    val open2 = remember { mutableStateOf(false) }
    val context: Context = LocalContext.current
    var shouldUpdateJobOffer by remember { mutableStateOf(false) }


    @RequiresApi(Build.VERSION_CODES.O)
    fun validateFields(): Boolean {
        if (startingDate.value < endingDate.value || startingDate.value <= LocalDate.now()) {
            Toast.makeText(
                context,
                "Problem found on the inserted dates",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
    @Composable
    fun updateOffer(){
        if(validateFields()){
            if(sharedOfferViewModel.offer != null){
                LaunchedEffect(sharedOfferViewModel.offer!!.id) {
                    val offerRef = firestore.collection("Offers").document(sharedOfferViewModel.offer!!.id)
                    val endingDateDate =
                        Date.from(endingDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant())
                    val startingDateDate =
                        Date.from(startingDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant())
                    val updates = mutableMapOf<String, Any>(
                        "endingDate" to endingDateDate,
                        "startingDate" to startingDateDate
                    )

                    if (city.isNotBlank()) {
                        updates["city"] = city
                    }
                    if (jobTitle.isNotBlank()) {
                        updates["title"] = jobTitle
                    }
                    if (jobName.isNotBlank()) {
                        updates["jobName"] = jobName
                    }
                    if (salary.isNotBlank()) {
                        updates["salary"] = salary
                    }
                    if (description.isNotBlank()) {
                        updates["description"] = description
                    }
                    offerRef.update(updates).addOnSuccessListener {
                        Toast.makeText(context, "Offer has been modified successfully", Toast.LENGTH_LONG).show()
                        navController.navigate("Search") {
                            popUpTo("JobForm/false") { inclusive = true }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
               }
            }
        }
    }
    if(shouldUpdateJobOffer){
        updateOffer()
        shouldUpdateJobOffer = false
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .requiredHeight(height = 1000.dp)
    ) {
        CrossedCirclesShapeBlue()

        Text(
            text = "Modify Offer",
            color = Color.Black,
            lineHeight = 4.82.em,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-1).dp, y = 100.dp)
        )

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
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = jobTitle,
                    onValueChange = { jobTitle = it },
                    placeholder = {
                        Text(
                            text = "Job Title",
                            maxLines = 1,
                            modifier = modifier
                                .horizontalScroll(rememberScrollState())
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray
                    )
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 195.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 60.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 297.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                )
                TextField(
                    modifier = Modifier.clickable {
                        open2.value = true
                    },
                    enabled = false,
                    value = endingDate.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,
                    onValueChange = {},
                    textStyle = TextStyle(
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant)
                )
                if (open2.value) {
                    CalendarDialog(
                        state = rememberUseCaseState(visible = true, true, onCloseRequest = { open2.value = false} ),
                        config = CalendarConfig(
                            yearSelection = true,
                            style = CalendarStyle.MONTH,
                        ),
                        selection = CalendarSelection.Date(
                            selectedDate = endingDate.value
                        ) { newDate ->
                            endingDate.value = newDate
                        },
                    )
                }
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "calendar_month",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 12.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 269.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 60.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 297.dp)
                        .requiredHeight(height = 60.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                )
                TextField(
                    modifier = Modifier.clickable {
                        open.value = true
                    },
                    enabled = false,
                    value = startingDate.value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,
                    onValueChange = {},
                    textStyle = TextStyle(
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant)
                )
                if (open.value) {
                    CalendarDialog(
                        state = rememberUseCaseState(visible = true, true, onCloseRequest = { open.value = false} ),
                        config = CalendarConfig(
                            yearSelection = true,
                            style = CalendarStyle.MONTH,
                        ),
                        selection = CalendarSelection.Date(
                            selectedDate = startingDate.value
                        ) { newDate ->
                            startingDate.value = newDate
                        },
                    )
                }
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "calendar_month",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 12.dp)
                )
            }

            // City Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 343.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = {
                        Text(
                            text="City",
                            maxLines = 1,
                            modifier = modifier
                                .horizontalScroll(rememberScrollState())
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                    )

                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                    tint = Color.Black,
                    modifier = Modifier.offset(x = 260.dp, y = 12.dp)
                )
            }

            // Salary Input
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 12.dp, y = 415.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = salary,
                    onValueChange = { salary = it },
                    placeholder = {
                        Text(
                            text="Salary",
                            maxLines = 1,
                            modifier = modifier
                                .horizontalScroll(rememberScrollState())
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray)
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 120.dp)
                    .requiredWidth(width = 297.dp)
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ) {
                TextField(
                    value = jobName,
                    onValueChange = { jobName = it },
                    placeholder = {
                        Text(
                            text="Job",
                            maxLines = 1,
                            modifier = modifier
                                .horizontalScroll(rememberScrollState())
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize(),
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
                    .offset(x = 15.dp, y = 490.dp)
                    .requiredWidth(width = 295.dp)
                    .requiredHeight(height = 150.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 295.dp)
                        .requiredHeight(height = 150.dp)
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
                    .offset(x = 0.dp, y = 680.dp)
                    .requiredWidth(width = 327.dp)
                    .requiredHeight(height = 64.dp)
                    .clickable {
                        shouldUpdateJobOffer = true
                    }
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 327.dp)
                        .requiredHeight(height = 64.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color(0xff50c2c9))

                )
                Text(
                    text = "Modify Offer",
                    color = Color.Black,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .align(alignment = Alignment.TopCenter)

                )
            }
        }
    }
}
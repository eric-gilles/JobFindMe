package com.example.jobfindme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var firstname by remember { mutableStateOf(TextFieldValue()) }
    var lastname by remember { mutableStateOf(TextFieldValue()) }
    var nationality by remember { mutableStateOf(TextFieldValue()) }
    val birthdate = remember { mutableStateOf(LocalDate.now())}
    val open = remember { mutableStateOf(false)}


    var city by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var confirm by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = modifier
            .verticalScroll(rememberScrollState())


    ) {

        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 1100.dp)
                .background(color = Color(0xfff6f6f6))
        ) {
            Shape(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = (-99).dp,
                        y = (-109).dp
                    ))

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 84.dp,
                        y = 850.dp
                    )
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 34.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 207.dp)
                        .requiredHeight(height = 34.dp)
                        .clip(shape = RoundedCornerShape(3.dp))
                        .background(color = Color(0xff50c2c9))) {
                    Text(
                        lineHeight = 9.sp,
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            ) { append("Upload a CV *") }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 52.dp,
                                y = 5.1817626953125.dp
                            )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 64.dp,
                    y = 83.dp
                )
                .requiredWidth(width = 246.dp)
                .requiredHeight(height = 69.dp)
        ) {

            Box (
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 30.dp) // Déplacer le Column vers le bas

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                    Text(
                        text = "We'll help you find a job.",
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Please Register",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Text(
                text = "Welcome Candidat !",
                color = Color.Black.copy(alpha = 0.75f),
                textAlign = TextAlign.Center,
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = -5.dp
                    )
                    .requiredWidth(width = 246.dp)
                    .requiredHeight(height = 28.dp))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 37.dp, y = 190.dp)
                .requiredWidth(width = 300.dp)
                .requiredHeight(height = 800.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            text = "Email *",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = firstname,
                    onValueChange = { firstname = it },
                    label = {
                        Text(
                            text = "First Name *",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },

                    label = {
                        Text(
                            text = "Last Name *",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    modifier = Modifier.clickable { //Click event
                        open.value = true
                    },
                    enabled = false,// <- Add this to make click event work
                    value = birthdate.value.format(DateTimeFormatter.ISO_DATE) ,
                    onValueChange = {},
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
                            selectedDate = birthdate.value
                        ) { newDate ->
                            birthdate.value = newDate
                        },
                    )
                }

                OutlinedTextField(
                    value = nationality,
                    onValueChange = { nationality = it },
                    label = {
                        Text(
                            text = "Nationality",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = {
                        Text(
                            text = "City",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = {
                        Text(
                            text = "Phone",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    label = {
                        Text(
                            text = "Password *",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = confirm,
                    onValueChange = { confirm = it },
                    visualTransformation = PasswordVisualTransformation(),

                    label = {
                        Text(
                            text = "Confirm Password *",
                            fontSize = 18.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 24.dp,
                    y = 910.dp
                )
                .requiredWidth(width = 326.dp)
                .requiredHeight(height = 64.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 64.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Color(0xff50c2c9))) {
                Text(
                    text = "Register",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 14.272705078125.dp
                        )
                )
            }
        }
        Text(
            lineHeight = 9.sp,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xff1e1e1e),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)) {append("Already have an account ? ")}
                withStyle(style = SpanStyle(
                    color = Color(0xff50c2c9),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)) {append("Sign In")}},
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-0.5).dp,
                    y = 990.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 84.dp,
                    y = 1030.dp
                )
                .requiredWidth(width = 207.dp)
                .requiredHeight(height = 34.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 34.dp)
                    .clip(shape = RoundedCornerShape(3.dp))
                    .background(color = Color(0xff50c2c9)))
            Text(
                text = "Anonymous ?",
                color = Color.White,
                lineHeight = 9.38.em,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 52.dp,
                        y = 3.1817626953125.dp
                    ))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 38.dp,
                    y = 57.dp
                )
                .requiredWidth(width = 32.dp)
                .requiredHeight(height = 29.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = Color.White)
                .border(
                    border = BorderStroke(8.dp, Color.White),
                    shape = MaterialTheme.shapes.small
                ))
    }
}

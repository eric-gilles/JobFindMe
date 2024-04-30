package com.example.jobfindme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.Constraints.Companion.Infinity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun Entrepriseregister(modifier: Modifier = Modifier) {
    var companyName by remember { mutableStateOf(TextFieldValue()) }
    var companyAddress by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }

    var password by remember { mutableStateOf(TextFieldValue()) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue()) }
    Box(modifier = modifier.verticalScroll(rememberScrollState())){
        Box(
            modifier = modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 1100.dp)
                .background(color = Color(0xfff6f6f6))

        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 900.dp)
            ) {
                Shape(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-99).dp,
                            y = (-109).dp
                        ))
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 62.dp,
                        y = 157.dp
                    )
                    .requiredWidth(width = 250.dp)
                    .requiredHeight(height = 84.dp)
            ) {
                Box (
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 40.dp) // Déplacer le Column vers le bas

                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                        Text(
                            text = "Discover temporary talent for your company.",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Please Register",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(
                    text = "Welcome Employer !",
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
                            y = 0.dp
                        )
                        .requiredWidth(width = 250.dp)
                        .requiredHeight(height = 28.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 38.dp,
                        y = 290.4990234375.dp
                    )
                    .requiredWidth(width = 298.dp)
                    .requiredHeight(height = 700.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
                        label = {
                            Text(
                                text = "Company Name *",
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        value = companyAddress,
                        onValueChange = { companyAddress = it },
                        label = {
                            Text(
                                text = "Company Address *",
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(
                                text = "Email *",
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = {
                            Text(
                                text = "Phone *",
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
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
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        visualTransformation = PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = "Confirm password *",
                                fontSize = 14.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 14.sp
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
                        y = 750.dp
                    )
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 326.dp)
                        .requiredHeight(height = 64.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color(0xff50c2c9)))
                Text(
                    text = "Register",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 14.272705078125.dp
                        ))
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
                        y = 850.dp
                    ))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 84.dp,
                        y = 900.dp
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
}
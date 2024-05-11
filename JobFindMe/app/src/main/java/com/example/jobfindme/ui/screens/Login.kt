package com.example.jobfindme.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jobfindme.R
import com.example.jobfindme.ui.components.Shape

@Composable
@Preview
fun Login(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }


    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 812.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 812.dp)
                    .background(color = Color(0xfff6f6f6))
            ) {
                Shape(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-99).dp,
                            y = (-109).dp
                        ))
                Image(
                    painter = painterResource(id = R.drawable.app_logo_rounded),
                    contentDescription = "Color logo with background 6",
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-0.25).dp,
                            y = 200.dp)
                        .requiredWidth(width = 203.dp)
                        .requiredHeight(height = 193.dp)
                        .clip(shape = RoundedCornerShape(65536.dp)))

            }

            Text(
                text = "Welcome Back !",
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
                        y = 149.5999755859375.dp
                    ))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 37.dp, y = 190.dp)
                    .requiredWidth(width = 300.dp)
                    .requiredHeight(height = 150.dp)
                    .offset(
                        x = 0.dp,
                        y = 200.dp
                    )
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
                        value = password,
                        onValueChange = { password = it },
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
                }
            }


            Text(
                text = "Forgot Password ?",
                color = Color(0xff00adb5),
                lineHeight = 9.38.em,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 550.dp
                    ))

            Text(
                lineHeight = 9.sp,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color(0xff1e1e1e),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium)) {append("Don’t have an account ? ")}
                    withStyle(style = SpanStyle(
                        color = Color(0xff50c2c9),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium)
                    ) {append("Register ")}},
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 680.dp
                    ))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 24.dp,
                        y = 600.dp
                    )
                    .requiredWidth(width = 326.dp)
                    .requiredHeight(height = 64.dp)
            ) {
                Button(
                    onClick = {
                        // Code à exécuter lorsque le bouton est cliqué (par exemple, interaction avec Firebase)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff50c2c9)),

                    modifier = Modifier
                        .requiredWidth(width = 326.dp)
                        .requiredHeight(height = 64.dp)

                ) {
                    Text(
                        text = "Sign In",
                        color = Color.White,
                        lineHeight = 6.25.em,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                }

            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 84.dp,
                        y = 720.dp
                    )
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 34.dp)
            ) {
                Button(
                    onClick = {
                        // Code à exécuter lorsque le bouton est cliqué (par exemple, interaction avec Firebase)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xff50c2c9)),

                    modifier = Modifier
                        .requiredWidth(width = 207.dp)
                        .requiredHeight(height = 34.dp)

                ) {
                    Text(
                        text = "Anonymous ?",
                        color = Color.White,
                        lineHeight = 9.38.em,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
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
package com.example.jobfindme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfilEmployer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) {

    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = Color(0xfff6f6f6))

    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 738.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 375.dp)
                    .requiredHeight(height = 272.dp)
                    .background(color = Color(0xff50c2c9))
            )

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 51.3157958984375.dp, y = 675.16845703125.dp)
                    .requiredWidth(width = 267.dp)
                    .requiredHeight(height = 49.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 267.dp)
                        .requiredHeight(height = 49.dp)
                        .clip(shape = RoundedCornerShape(19.68549156188965.dp))
                        .background(color = Color(0xff50c2c9))
                )
                Text(
                    text = "Change Password",
                    color = Color.White,
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 60.dp, y = 8.dp)
                        .requiredHeight(height = 28.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 287.dp)
                    .requiredWidth(width = 359.dp)
                    .requiredHeight(height = 240.dp)
            ) {
                Text(
                    text = "Name :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .requiredHeight(height = 37.dp)
                )
                Text(
                    text = "Phone Number :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 42.dp)
                        .requiredHeight(height = 37.dp)
                )
                Text(
                    text = "Email :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 82.dp)
                        .requiredHeight(height = 37.dp)
                )
                Text(
                    text = "Address :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 125.dp)
                        .requiredHeight(height = 37.dp)
                )
                Text(
                    text = "Links :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 165.dp)
                        .requiredHeight(height = 37.dp)
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 296.dp, y = 168.dp)
                        .clip(shape = MaterialTheme.shapes.small)
                        .background(color = Color.White)
                        .border(
                            border = BorderStroke(8.dp, Color.White),
                            shape = MaterialTheme.shapes.small
                        )
                ){
                    Image(
                        imageVector = Icons.Default.AddLink,
                        contentDescription = "company",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredWidth(60.dp)
                            .requiredHeight(40.dp)
                    )
                }
                Text(
                    text = "Company X",
                    color = Color.Black,
                    lineHeight = 9.38.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 97.dp, y = 5.dp)
                        .requiredHeight(height = 25.dp)
                )
                Text(
                    text = "+33 4 88 88 88 88",
                    color = Color.Black,
                    lineHeight = 9.38.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 181.dp, y = 45.dp)
                        .requiredHeight(height = 24.dp)
                )
                Text(
                    text = "company-x@mail.com",
                    color = Color.Black,
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 83.dp, y = 86.dp)
                        .requiredWidth(width = 222.dp)
                        .requiredHeight(height = 30.dp)
                )
                Text(
                    text = "25 rue du champs",
                    color = Color.Black,
                    lineHeight = 10.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 116.dp, y = 120.dp)
                )
                Text(
                    text = "34000 Montpellier",
                    color = Color.Black,
                    lineHeight = 10.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 116.dp, y = 140.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 15.dp, y = 500.dp)
                    .requiredWidth(width = 338.dp)
                    .requiredHeight(height = 164.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 0.dp)
                        .requiredWidth(width = 338.dp)
                        .requiredHeight(height = 164.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(color = Color(0xff94d6da))
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 17.dp, y = 27.dp)
                        .requiredWidth(width = 285.dp)
                        .requiredHeight(height = 49.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                ){
                    Text(
                        text = "https://www.company-x.com",
                        color = Color(0xff1e1e1e),
                        lineHeight = 8.33.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 10.dp, y = 10.dp)
                            .requiredHeight(height = 28.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 17.dp, y = 100.dp)
                        .requiredWidth(width = 285.dp)
                        .requiredHeight(height = 49.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color.White)
                ){
                    Text(
                        text = "https://www.linkedin.com/company/company-x",
                        color = Color(0xff1e1e1e),
                        lineHeight = 8.33.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 10.dp, y = 10.dp)
                            .requiredHeight(height = 28.dp)
                    )
                }
                Text(
                    text = "Website",
                    color = Color(0xff1e1e1e),
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 17.dp, y = 5.dp)
                        .requiredWidth(width = 77.dp)
                        .requiredHeight(height = 28.dp)
                )
                Text(
                    text = "Linkedin",
                    color = Color(0xff1e1e1e),
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 17.dp, y = 75.dp)
                        .requiredWidth(width = 79.dp)
                        .requiredHeight(height = 28.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 104.60525512695312.dp, y = 68.dp)
                    .requiredWidth(width = 161.dp)
                    .requiredHeight(height = 181.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 13.dp, y = 41.dp)
                        .requiredWidth(width = 140.dp)
                        .requiredHeight(height = 140.dp)
                        .clip(CircleShape)
                        .background(color = Color.White)
                        .border(border = BorderStroke(3.dp, Color.White), shape = CircleShape)
                ) {
                    Image(
                        imageVector = Icons.Default.Domain,
                        contentDescription = "company",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredWidth(110.dp)
                            .requiredHeight(110.dp)
                    )
                }
                Text(
                    text = "My Company",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 3.dp, y = 0.dp)
                        .requiredWidth(width = 155.dp)
                        .requiredHeight(height = 37.dp)
                )
            }
        }
    }
}



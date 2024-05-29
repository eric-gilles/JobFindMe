package com.example.jobfindme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfilCandidat(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    firebaseAuth: FirebaseAuth,
    firestore: FirebaseFirestore
) {
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
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 375.dp)
                        .requiredHeight(height = 272.dp)
                        .background(color = Color(0xff50c2c9))
                )
                CrossedCirclesShapeBlue()
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 94.dp, y = 68.dp)
                    .requiredWidth(width = 187.dp)
                    .requiredHeight(height = 182.dp)
            ) {
                Text(
                    text = "My Informations",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 0.dp, y = 0.dp)
                        .requiredWidth(width = 187.dp)
                        .requiredHeight(height = 35.dp)
                )
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
                        imageVector = Icons.Default.Person,
                        contentDescription = "person",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .requiredWidth(150.dp)
                            .requiredHeight(120.dp)
                    )
                }
            }
            // Button "Edit Profile"
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 52.dp, y = 695.dp)
                    .requiredWidth(width = 270.dp)
                    .requiredHeight(height = 47.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 270.dp)
                        .requiredHeight(height = 47.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff50c2c9))
                )
                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 90.dp, y = 10.dp)
                        .requiredHeight(height = 26.dp)
                )
            }
            // Button "Change Password"
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 52.dp, y = 750.dp)
                    .requiredWidth(width = 270.dp)
                    .requiredHeight(height = 47.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 270.dp)
                        .requiredHeight(height = 47.dp)
                        .clip(shape = CircleShape)
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
                        .offset(x = 70.dp, y = 10.dp)
                        .requiredHeight(height = 26.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 14.dp, y = 300.dp)
                    .requiredWidth(width = 345.dp)
                    .requiredHeight(height = 385.dp)
            ) {
                Text(
                    text = "Name :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 0.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Firstname :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 50.dp)
                        .requiredHeight(height = 35.dp)
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
                        .offset(x = 3.dp, y = 101.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Phone :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 152.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "City :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 200.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Birth date :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 250.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "Nationality :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp, y = 300.dp)
                        .requiredHeight(height = 35.dp)
                )
                Text(
                    text = "CV_JOHN_DOE.pdf",
                    color = Color(0xff1e1e1e),
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 64.dp, y = 354.dp)
                        .requiredHeight(height = 26.dp)
                )
                Text(
                    text = "CV :",
                    color = Color(0xff757575),
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 3.dp, y = 350.dp)
                        .requiredHeight(height = 35.dp)
                )
                Image(
                    imageVector = Icons.Default.FileDownload,
                    contentDescription = "file_download",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .fillMaxSize()
                        .requiredWidth(width = 30.dp)
                        .requiredHeight(height = 30.dp)
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 155.dp, y = 170.dp)
                )
                Text(
                    text = "France",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 150.dp, y = 304.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "15/05/2002",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 140.dp, y = 255.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "Montpellier",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 75.dp, y = 206.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "+33 6 60 60 66 66",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 102.dp, y = 157.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "mail@mail.com",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 95.dp, y = 105.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "John",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 140.dp, y = 56.dp)
                        .requiredHeight(height = 29.dp)
                )
                Text(
                    text = "Doe",
                    color = Color(0xff1e1e1e),
                    lineHeight = 7.5.em,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 97.dp, y = 5.dp)
                        .requiredHeight(height = 29.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun ProfilCandidatPreview() {
    //ProfilCandidat(Modifier, navController, firebaseAuth, firestore)
}
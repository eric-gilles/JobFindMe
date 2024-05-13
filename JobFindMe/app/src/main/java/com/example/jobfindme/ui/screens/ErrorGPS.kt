package com.example.jobfindme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jobfindme.R

@Composable
@Preview
fun ErrorGPS(modifier: Modifier = Modifier, navController: NavHostController) {
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
                        .offset(x = (-99).dp,
                            y = (-109).dp))
            }
            Image(
                painter = painterResource(id = R.drawable.error_gps),
                contentDescription = "No GPS Location",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 11.dp,
                        y = 175.448974609375.dp)
                    .requiredSize(size = 352.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(52.dp, Alignment.Top),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 570.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .requiredWidth(width = 327.dp)
                ) {
                    Text(
                        text = "No GPS Connection",
                        color = Color(0xff303030),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .requiredWidth(width = 230.dp))
                    Text(
                        text = "Please check for location permission",
                        color = Color(0xff4a4a4a),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .requiredWidth(width = 325.dp))
                }
            }
        }
    }
}

@Composable
fun Shape(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 290.dp)
            .requiredHeight(height = 270.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
                .background(color = Color(0xff6ae0d9).copy(alpha = 0.49f)))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
                .background(color = Color(0xff6ae0d9).copy(alpha = 0.49f)))
    }
}

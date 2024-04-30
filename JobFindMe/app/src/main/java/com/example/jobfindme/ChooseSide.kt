package com.example.jobfindme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun ChooseSide(modifier: Modifier = Modifier) {
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
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .offset(x = 0.dp,
                            y = 0.dp)
                        .requiredWidth(width = 96.dp)
                        .requiredHeight(height = 44.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .offset(x = (-16).dp,
                                y = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 20.dp)
                                .requiredHeight(height = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(x = 15.5.dp,
                                        y = 3.dp)
                                    .requiredWidth(width = 4.dp)
                                    .requiredHeight(height = 12.dp)
                                    .clip(shape = RoundedCornerShape(1.dp))
                                    .background(color = Color.Black.copy(alpha = 0.2f)))


                        }
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Color logo with background 4",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-0.12105560302734375).dp,
                        y = 206.dp)
                    .requiredWidth(width = 181.dp)
                    .requiredHeight(height = 172.dp)
                    .clip(shape = RoundedCornerShape(58499.50390625.dp)))
            Text(
                text = "Welcome !",
                color = Color.Black.copy(alpha = 0.75f),
                textAlign = TextAlign.Center,
                lineHeight = 6.25.em,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-0.5).dp,
                        y = 117.5999755859375.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 39.dp,
                        y = 459.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 51.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 0.dp,
                            y = 1.dp)
                        .requiredWidth(width = 296.dp)
                        .requiredHeight(height = 50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color(0xff50c2c9)))
                Text(
                    text = "Candidat",
                    color = Color(0xff1e1e1e),
                    textAlign = TextAlign.Center,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 94.dp,
                            y = 5.dp))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 39.dp,
                        y = 544.dp)
                    .requiredWidth(width = 296.dp)
                    .requiredHeight(height = 50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 296.dp)
                        .requiredHeight(height = 50.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(color = Color(0xff50c2c9)))
                Text(
                    text = "Employer",
                    color = Color(0xff1e1e1e),
                    textAlign = TextAlign.Center,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 94.dp,
                            y = 7.dp))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 694.dp)
                    .requiredWidth(width = 327.dp)
                    .requiredHeight(height = 42.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0xff50c2c9))
                    .border(border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(8.dp))
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = "Anonymous ?",
                    color = Color.White,
                    lineHeight = 6.25.em,
                    style = TextStyle(
                        fontSize = 16.sp))
            }
        }
    }
}

package com.example.jobfindme.ui.components

import androidx.compose.material3.Text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jobfindme.R


@Composable
fun WelcomeComponent(modifier: Modifier = Modifier) {
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
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 375.dp)
                        .requiredHeight(height = 307.dp)
                        .background(color = Color(0xff50c2c9)))
                Shape(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-99).dp,
                            y = (-109).dp
                        ))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .offset(
                            x = 0.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 96.dp)
                        .requiredHeight(height = 44.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .offset(
                                x = (-16).dp,
                                y = 16.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 20.dp)
                                .requiredHeight(height = 16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.TopStart)
                                    .offset(
                                        x = 15.5.dp,
                                        y = 3.dp
                                    )
                                    .requiredWidth(width = 4.dp)
                                    .requiredHeight(height = 12.dp)
                                    .clip(shape = RoundedCornerShape(1.dp))
                                    .background(color = Color.Black.copy(alpha = 0.2f)))



                        }
                    }
                    Text(
                        text = "9:45",
                        color = Color.Black,
                        lineHeight = 8.9.em,
                        style = TextStyle(
                            fontSize = 13.sp),
                        modifier = Modifier
                            .fillMaxSize())
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 1.dp,
                        y = 689.dp
                    )
                    .requiredWidth(width = 374.dp)
                    .requiredHeight(height = 123.dp)
                    .clip(shape = RoundedCornerShape(22.9007625579834.dp))
                    .background(color = Color(0xfff5f6f7))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .offset(
                            x = 0.dp,
                            y = (-28.534358978271484).dp
                        )
                        .requiredWidth(width = 375.dp)
                        .background(color = Color.White)
                        .padding(
                            start = 11.4503812789917.dp,
                            end = 11.4503812789917.dp,
                            top = 11.4503812789917.dp,
                            bottom = 3.816793918609619.dp
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.72519063949585.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(weight = 0.2f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(9.541984558105469.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(95.41984558105469.dp))
                                .background(color = Color(0xff539df3).copy(alpha = 0.37f))
                                .padding(all = 11.4503812789917.dp)
                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.lihome),
//                                contentDescription = "li:home",
//                                modifier = Modifier
//                                    .requiredSize(size = 23.dp))
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.72519063949585.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(weight = 0.2f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(9.541984558105469.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(95.41984558105469.dp))
                                .padding(all = 11.4503812789917.dp)
                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.lisearch),
//                                contentDescription = "li:search",
//                                modifier = Modifier
//                                    .requiredSize(size = 23.dp))
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(weight = 0.2f))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.72519063949585.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(weight = 0.2f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(9.541984558105469.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(95.41984558105469.dp))
                                .padding(all = 11.4503812789917.dp)
                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.liclock),
//                                contentDescription = "li:clock",
//                                modifier = Modifier
//                                    .requiredSize(size = 23.dp))
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.72519063949585.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(weight = 0.2f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(9.541984558105469.dp, Alignment.Start),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(95.41984558105469.dp))
                                .padding(all = 11.4503812789917.dp)
                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.liuser),
//                                contentDescription = "li:user",
//                                modifier = Modifier
//                                    .requiredSize(size = 23.dp))
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .offset(
                            x = 0.dp,
                            y = 0.0915985107421875.dp
                        )
                        .requiredWidth(width = 375.dp)
                        .requiredHeight(height = 29.dp)
                        .background(color = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .offset(
                                x = (-0.00002288818359375).dp,
                                y = (-7.633594512939453).dp
                            )
                            .requiredWidth(width = 129.dp)
                            .requiredHeight(height = 5.dp)
                            .clip(shape = RoundedCornerShape(95.41984558105469.dp))
                            .background(color = Color(0xffb9c0c9)))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 132.dp,
                        y = 119.dp
                    )
                    .requiredWidth(width = 122.dp)
                    .requiredHeight(height = 116.dp)
                    .clip(shape = RoundedCornerShape(65536.dp))
                    .background(color = Color.White)
                    .border(
                        border = BorderStroke(3.dp, Color.White),
                        shape = RoundedCornerShape(65536.dp)
                    )) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo_rounded),
                    contentDescription = "Application Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .requiredWidth(width = 122.dp)
                        .requiredHeight(height = 116.dp)
                        .clip(shape = RoundedCornerShape(65536.dp))
                )

            }
            Text(
                text = "Welcome, Oliva Grace",
                color = Color.White,
                lineHeight = 6.43.em,
                style = TextStyle(
                    fontSize = 18.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 79.dp,
                        y = 251.dp
                    ))
        }
    }
}




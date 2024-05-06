package com.example.jobfindme.ui.components

import androidx.compose.material3.Text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jobfindme.R

@Composable
@Preview
fun WelcomeComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize() // RemLplir toute la taille disponible
                    .background(color = Color(0xfff6f6f6))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Remplir toute la largeur disponible
                        .requiredHeight(height = 307.dp)
                        .background(color = Color(0xff50c2c9))
                )
                Shape(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = (-99).dp,
                            y = (-109).dp
                        )
                )
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
                    )
            ) {
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
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 79.dp,
                        y = 251.dp
                    )
            )
        }
    }
}

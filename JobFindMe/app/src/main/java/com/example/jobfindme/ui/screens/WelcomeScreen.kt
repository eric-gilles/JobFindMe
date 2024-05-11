package com.example.jobfindme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.times
import com.example.jobfindme.R

@Composable
fun Welcome(modifier: Modifier = Modifier) {
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
                        .align(alignment = Alignment.TopStart)
                        .offset(x = (-99).dp, y = (-109).dp)
                        .requiredWidth(width = 290.dp)
                        .requiredHeight(height = 270.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = CircleShape)
                            .background(color = Color(0xff6ae0d9).copy(alpha = 0.49f)))
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(y = 656.dp)
                        .requiredWidth(width = 325.dp)
                        .requiredHeight(height = 62.dp)
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
                    ){
                        Text(
                            text = "Get Started",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            lineHeight = 7.62.em,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
                Text(
                    text = "Welcome on JobFindMe !",
                    color = Color.Black.copy(alpha = 0.74f),
                    lineHeight = 6.25.em,
                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = 5.dp, y = 160.dp))

                val textLines = listOf(
                    "Discover thousands of job opportunities and talents.",
                    "Create your profile, explore opportunities",
                    "and start building your future today.",
                    "Join our dynamic community to find the perfect match",
                    "for your career or business."
                )
                textLines.forEachIndexed { index, line ->
                    Text(
                        text = line,
                        color = Color.Black.copy(alpha = 0.74f),
                        textAlign = TextAlign.Center,
                        lineHeight = 10.55.em,

                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .align(alignment = Alignment.TopCenter)
                            .offset(x = (-0.5).dp, y = 406.dp + index * 30.dp)
                            .requiredWidth(width = 364.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.app_logo_rounded),
                    contentDescription = "Application Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(x = (-0.25).dp, y = 202.dp)
                        .requiredWidth(width = 203.dp)
                        .requiredHeight(height = 193.dp)
                        .clip(shape = RoundedCornerShape(65536.dp)))
            }
        }
    }
}

@Preview
@Composable
private fun WelcomePreview() {
    Welcome(Modifier)
}
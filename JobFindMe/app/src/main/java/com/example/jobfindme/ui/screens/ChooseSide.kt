package com.example.jobfindme.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue


@Composable
fun ChooseSide(
  modifier: Modifier = Modifier,
  navController: NavController,
) {
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp

  Box(
    modifier = modifier
      .requiredHeight(height = 812.dp)
      .requiredWidth(width = screenWidth)
      .background(Color(0xfff6f6f6))
  ) {
    CrossedCirclesShapeBlue()

    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(x = (-0.12105560302734375).dp, y = 206.dp)
        .requiredWidth(width = 181.dp)
        .requiredHeight(height = 172.dp)
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
              .requiredWidth(width = 181.dp)
              .requiredHeight(height = 172.dp)
              .clip(shape = RoundedCornerShape(65536.dp))
          )
      }
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
          .offset(x = (-0.5).dp, y = 117.5999755859375.dp)
      )
    Text(
      text = "Choose your Side : Candidate or Employer",
      color = Color.Gray,
      textAlign = TextAlign.Center,
      lineHeight = 6.25.em,
      style = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic),
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(x = (-0.5).dp, y = 150.dp)
    )
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 39.dp, y = 459.dp)
          .requiredWidth(width = 296.dp)
          .requiredHeight(height = 51.dp)
      ) {
        Button(
          onClick = {
            navController.navigate("Signup/user")
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff50c2c9)),
          modifier = modifier
            .requiredWidth(width = 296.dp)
            .requiredHeight(height = 50.dp)
        ) {
          Text(
            text = "Candidat",
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 6.25.em,
            style = TextStyle(
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold
            ),
          )
        }
      }
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 39.dp, y = 544.dp)
          .requiredWidth(width = 296.dp)
          .requiredHeight(height = 50.dp)
      ) {
        Button(
          onClick = {
            navController.navigate("Signup/employer")
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
          modifier = modifier
            .requiredWidth(width = 296.dp)
            .requiredHeight(height = 50.dp)
        ) {
          Text(
            text = "Employer",
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 6.25.em,
            style = TextStyle(
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold
            ),
          )
        }
      }
      Button(
        onClick = {
          navController.navigate("Signin/anonymous")
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),

        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .offset(y = 694.dp)
          .requiredWidth(width = 327.dp)
          .requiredHeight(height = 50.dp)
      ) {
        Text(
          text = "Anonymous",
          color = Color.White,
          textAlign = TextAlign.Center,
          lineHeight = 6.25.em,
          style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
          ),
        )
      }
    }
  }


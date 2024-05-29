package com.example.jobfindme.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.jobfindme.R
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Welcome(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  firebaseAuth: FirebaseAuth,
  context: Context = LocalContext.current
) {
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
  ) { isGranted: Boolean ->
    if (isGranted) {
      if (firebaseAuth.currentUser == null) {
        navController.navigate("Choose")
      } else {
        navController.navigate("Home")
      }
    } else {
      navController.navigate("ErrorGPS")
    }
  }



  Box(
    modifier = modifier
      .fillMaxSize()
      .background(color = Color(0xfff6f6f6))
  ) {

    CrossedCirclesShapeBlue()

    Box(
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(y = 656.dp)
        .requiredWidth(325.dp)
        .requiredHeight(62.dp)
    ) {
      Button(
        onClick = {
          if (ContextCompat.checkSelfPermission(
              context,
              Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
          ) {
            if (firebaseAuth.currentUser == null) {
              navController.navigate("Choose")
            } else {
              navController.navigate("Home")
            }
          } else {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
          }
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xff50c2c9)
        ),
        modifier = Modifier
          .requiredWidth(326.dp)
          .requiredHeight(64.dp),
      ) {
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
      style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(x = 5.dp, y = 160.dp)
    )

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
        lineHeight = 12.em,
        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .padding(bottom = 16.dp)
          .fillMaxWidth()
          .offset(y =  436.dp + index * 30.dp)
      )
    }

    Image(
      painter = painterResource(id = R.drawable.app_logo_rounded),
      contentDescription = "Application Logo",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .offset(x = (-0.25).dp, y = 202.dp)
        .requiredWidth(203.dp)
        .requiredHeight(193.dp)
        .clip(shape = RoundedCornerShape(65536.dp))
    )
  }
}

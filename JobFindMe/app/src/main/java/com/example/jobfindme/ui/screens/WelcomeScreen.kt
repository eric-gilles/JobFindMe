package com.example.jobfindme.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.jobfindme.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Welcome(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  firebaseAuth: FirebaseAuth,
  context: Context
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
    MaterialTheme {
      Box(
        modifier = Modifier
          .padding(vertical = 16.dp)
          .fillMaxWidth()
          .wrapContentHeight(align = Alignment.Top)
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
            containerColor = Color(0xff3f51b5),
          ),
          modifier = Modifier
            .fillMaxWidth()
        ) {
          Text(
            text = "Get Started",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
              .padding(vertical = 16.dp)
          )
        }
      }
    }

    Text(
      text = "Welcome on JobFindMe !",
      color = Color.Black.copy(alpha = 0.74f),
      style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .padding(top = 160.dp)
    )

    val textLines = listOf(
      "Discover thousands of job opportunities and talents.",
      "Create your profile, explore opportunities",
      "and start building your future today.",
      "Join our dynamic community to find the perfect match",
      "for your career or business."
    )
    textLines.forEachIndexed { _, line ->
      Text(
        text = line,
        color = Color.Black.copy(alpha = 0.74f),
        textAlign = TextAlign.Center,
        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier
          .padding(vertical = 16.dp)
          .fillMaxWidth()
      )
    }

    Image(
      painter = painterResource(id = R.drawable.app_logo_rounded),
      contentDescription = "Application Logo",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .align(alignment = Alignment.TopCenter)
        .padding(top = 202.dp)
        .fillMaxWidth(0.5f)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(percent = 50))
    )
  }
}

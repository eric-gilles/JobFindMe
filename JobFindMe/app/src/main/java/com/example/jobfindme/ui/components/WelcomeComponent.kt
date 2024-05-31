package com.example.jobfindme.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun WelcomeComponent(
  modifier: Modifier = Modifier,
  navController: NavController,
  firebaseAuth: FirebaseAuth,
  firestore: FirebaseFirestore,
) {
  var fullName by remember { mutableStateOf("") }
  val currentUser = firebaseAuth.currentUser
  val context: Context = LocalContext.current
  var anonymousUser by remember { mutableStateOf(false) }

  if (currentUser != null) {
    val uid = currentUser.uid
    val userDocRef = firestore.collection("Users").document(uid)

    LaunchedEffect(uid) {
      userDocRef.get().addOnSuccessListener { document ->
        if (document != null) {
          val firstName = document.getString("firstname")
          val lastName = document.getString("lastname")

          if (firstName != null && lastName != null) {
            fullName = capitalizeFirstLetter(firstName) + " " + capitalizeFirstLetter(lastName)
          }
        }
      }.addOnFailureListener { exception ->
        Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
        navController.navigate("WelcomePage")
      }
    }
    val employerDocRef = firestore.collection("Employers").document(uid)
    LaunchedEffect(uid) {
      employerDocRef.get().addOnSuccessListener { document ->
        if (document != null) {
          val companyName = document.getString("name")
          if (companyName != null) {
            fullName = capitalizeFirstLetter(companyName)
          }
        }
      }.addOnFailureListener { exception ->
        Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
        navController.navigate("WelcomePage")
      }
    }
  } else {
    fullName = "Anonymous User"
    anonymousUser = true
  }
  Box(
    modifier = modifier.fillMaxSize()
  ) {
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(color = Color(0xfff6f6f6))
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(height = 307.dp)
            .background(color = Color(0xff50c2c9))
        )
        CrossedCirclesShapeWhite()
      }

      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 132.dp, y = 119.dp)
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
        text = "Welcome, $fullName",
        color = Color.White,
        lineHeight = 6.43.em,
        style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
        modifier = Modifier
          .align(alignment = Alignment.TopStart)
          .offset(x = 79.dp, y = 251.dp)
      )
    }

    if (anonymousUser) {
      Box(
        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .offset(y = 500.dp)
          .requiredWidth(325.dp)
          .requiredHeight(62.dp)
      ) {
        Button(
          onClick = {
            navController.navigate("Search")
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff50c2c9)
          ),
          modifier = Modifier
            .requiredWidth(326.dp)
            .requiredHeight(64.dp),
        ) {
          Text(
            text = "View Offers",
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
    }
  }
}



fun capitalizeFirstLetter(str: String): String {
  return str.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
  }
}
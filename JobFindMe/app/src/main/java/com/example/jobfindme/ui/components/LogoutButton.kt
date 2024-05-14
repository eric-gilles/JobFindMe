package com.example.jobfindme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LogoutButton(
  modifier: Modifier = Modifier,
  navController: NavController,
  firebaseAuth: FirebaseAuth,
  offsetX: Dp = 38.dp,
  offsetY: Dp = 57.dp
) {
  Box(
    modifier = modifier
      .offset(x = offsetX, y = offsetY)
      .requiredWidth(width = 32.dp)
      .requiredHeight(height = 29.dp)
      .clip(shape = CircleShape)
      .background(color = Color.White)
  ) {
    IconButton(
      onClick = {
        firebaseAuth.signOut()
        navController.navigate("WelcomePage")
      }
    ) {
      Image(
        painter = painterResource(id = R.drawable.logout_logo_50),
        contentDescription = "Logout Button"
      )
    }
  }
}
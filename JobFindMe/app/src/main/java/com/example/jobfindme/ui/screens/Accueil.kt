package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.LogoutButton
import com.example.jobfindme.ui.components.WelcomeComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
  navController: NavController,
  firebaseAuth: FirebaseAuth,
  firestore: FirebaseFirestore
){
  Scaffold(
    topBar = {
      WelcomeComponent(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
      LogoutButton(navController = navController)
    },
    bottomBar = {
      BottomNav(navController= navController)
    }
  ) {
  }
}
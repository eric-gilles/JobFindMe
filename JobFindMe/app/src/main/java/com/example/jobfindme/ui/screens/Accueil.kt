package com.example.jobfindme.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfindme.ui.components.LogoutButton
import com.example.jobfindme.ui.components.WelcomeComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AccueilProfile(
  navController: NavController,
  firebaseAuth: FirebaseAuth,
  firestore: FirebaseFirestore
){
  WelcomeComponent(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
  LogoutButton(navController = navController)

}
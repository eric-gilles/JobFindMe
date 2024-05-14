package com.example.jobfindme.ui.screens

import androidx.compose.runtime.Composable
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
  LogoutButton(navController = navController, firebaseAuth = firebaseAuth)
  WelcomeComponent(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
}
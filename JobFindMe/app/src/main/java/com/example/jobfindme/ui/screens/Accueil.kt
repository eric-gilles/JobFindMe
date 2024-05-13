package com.example.jobfindme.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.jobfindme.ui.components.WelcomeComponent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
@Preview
fun AccueilProfile(
  navController: NavController,
  firebaseAuth: FirebaseAuth,
  firestore: FirebaseFirestore
){
  WelcomeComponent(firebaseAuth = firebaseAuth, firestore = firestore)
}
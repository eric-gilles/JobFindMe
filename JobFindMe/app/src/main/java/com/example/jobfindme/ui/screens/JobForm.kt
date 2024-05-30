package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.ModifyOffer
import com.example.jobfindme.ui.components.NewOffer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormJob(modifier: Modifier = Modifier, navController: NavController, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth, addOffer : Boolean) {
  Scaffold(
    bottomBar = {
      BottomNav(navController= navController)
    }
  ){
    if (addOffer){
      NewOffer(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
    }else {
      ModifyOffer(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
    }
  }
}

package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.jobfindme.data.EmployerOutput
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.data.toEmployerOutput
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.ModifyOffer
import com.example.jobfindme.ui.components.NewOffer
import com.example.jobfindme.ui.components.capitalizeFirstLetter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormJob(modifier: Modifier = Modifier, navController: NavController, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth, addOffer : Boolean, sharedOfferViewModel: SharedOfferViewModel) {

  Scaffold(
    bottomBar = {
      BottomNav(navController= navController)
    }
  ){
    if (addOffer){
      NewOffer(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore)
    }else {
      ModifyOffer(navController= navController, firebaseAuth = firebaseAuth, firestore = firestore, sharedOfferViewModel = sharedOfferViewModel)
    }
  }
}

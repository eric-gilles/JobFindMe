package com.example.jobfindme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobfindme.ui.screens.AccueilProfile
import com.example.jobfindme.ui.screens.ChooseSide
import com.example.jobfindme.ui.screens.EmployerForm
import com.example.jobfindme.ui.screens.ErrorGPS
import com.example.jobfindme.ui.screens.Login
import com.example.jobfindme.ui.screens.UserForm
import com.example.jobfindme.ui.screens.Welcome
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore) {
  val navController = rememberNavController()

  NavHost(navController, startDestination = "WelcomePage") {
    composable("Signin") {
      Login(navController = navController, firebaseAuth = firebaseAuth, firestore = firestore)
    }
    composable("Signin/anonymous") {
      firebaseAuth.signInAnonymously()
      //redirige vers les offres pour les anonymes
    }
    composable("Signup/user") {
      UserForm(navController = navController, firestore = firestore, firebaseAuth = firebaseAuth)
    }
    composable("Choose") {
      ChooseSide(navController = navController)
    }
    composable("Signup/employer"){
      EmployerForm(navController = navController, firebaseAuth = firebaseAuth, firestore = firestore)
    }
    composable("WelcomePage"){
      Welcome(navController = navController, firebaseAuth = firebaseAuth)
    }
    composable("Accueil"){
      if (firebaseAuth.currentUser != null) {
        AccueilProfile(
          navController = navController,
          firebaseAuth = firebaseAuth,
          firestore = firestore
        )
      } else {
        navController.navigate("Choose")
      }
    }
    composable("ErrorGPS"){
      ErrorGPS(navController = navController)
    }
    composable("Signout") {
      firebaseAuth.signOut()
      navController.navigate("Choose")
    }
  }
}
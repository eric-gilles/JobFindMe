package com.example.jobfindme

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobfindme.ui.components.OfferScreen
import com.example.jobfindme.ui.screens.Home
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
  val context: Context = LocalContext.current

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
      //OfferScreen()
      Welcome(navController = navController, firebaseAuth = firebaseAuth)
    }
    composable("Home"){
      if (firebaseAuth.currentUser != null) {
        Home(
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
    composable("Post") {
      Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
    composable("Search") {
      Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()

    }
    composable("Account") {
      Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }

  }
}
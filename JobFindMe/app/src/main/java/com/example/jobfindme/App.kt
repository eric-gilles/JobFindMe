package com.example.jobfindme

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.ui.utils.isCandidate
import com.example.jobfindme.ui.screens.CandidaturesList
import com.example.jobfindme.ui.screens.Home
import com.example.jobfindme.ui.screens.ChooseSide
import com.example.jobfindme.ui.screens.EmployerForm
import com.example.jobfindme.ui.screens.ErrorGPS
import com.example.jobfindme.ui.screens.Login
import com.example.jobfindme.ui.screens.OfferDetails
import com.example.jobfindme.ui.screens.OffersEmployer
import com.example.jobfindme.ui.screens.SearchOffers
import com.example.jobfindme.ui.screens.UserForm
import com.example.jobfindme.ui.screens.Welcome
import com.example.jobfindme.ui.components.ProfilCandidat
import com.example.jobfindme.ui.components.ProfilEmployer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore) {
  val navController = rememberNavController()
  val context: Context = LocalContext.current
  val sharedOfferViewModel: SharedOfferViewModel = viewModel()
  val (isCandidate, setIsCandidate) = remember { mutableStateOf(false) }
  val userId = firebaseAuth.currentUser?.uid

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
      Welcome(navController = navController, firebaseAuth = firebaseAuth, context = context)
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
      if (userId!=null){
        isCandidate(setIsCandidate, userId, firestore)
        if (isCandidate) {
          SearchOffers(navController = navController, firebaseAuth = firebaseAuth, firestore = firestore, sharedOfferViewModel = sharedOfferViewModel)
        } else {
          OffersEmployer(navController = navController, firestore = firestore, firebaseAuth = firebaseAuth, sharedOfferViewModel = sharedOfferViewModel)
        }
      }
    }
    composable("Account") {
      if (userId!=null){
        isCandidate(setIsCandidate, userId, firestore)
        if (isCandidate) {
            //Toast.makeText(context, "Profil candidat", Toast.LENGTH_SHORT).show()
            ProfilCandidat(navController = navController, firebaseAuth = firebaseAuth, firestore = firestore)
        } else {
            //Toast.makeText(context, "Profil employeur", Toast.LENGTH_SHORT).show()
            ProfilEmployer(navController = navController, firebaseAuth = firebaseAuth, firestore = firestore)
        }
      }
    }
    composable("OfferDetails"){
      OfferDetails(
        navController = navController,
        firestore = firestore,
        firebaseAuth = firebaseAuth,
        sharedOfferViewModel = sharedOfferViewModel
      )
    }
    composable("CandidatureList/{isAcceptedList}", arguments = listOf(navArgument("isAcceptedList") { type = NavType.BoolType })) { backStackEntry ->

      val isAcceptedList: Boolean = backStackEntry.arguments?.getBoolean("isAcceptedList") == true
      sharedOfferViewModel.offer?.let {
        CandidaturesList(
          firestore = firestore,
          firebaseAuth = firebaseAuth,
          offerOutput = it,
          isAcceptedList = isAcceptedList,
          navController = navController
        )
      }
    }
  }
}


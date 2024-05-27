package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.data.toOfferOutput
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.example.jobfindme.ui.components.LogoutButton
import com.example.jobfindme.ui.components.OffreCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OffersEmployer(modifier:Modifier = Modifier, navController: NavController, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth,sharedOfferViewModel: SharedOfferViewModel) {

  var offersList = remember { mutableStateListOf<OfferOutput>() }
  val userId = firebaseAuth.currentUser?.uid
  val context: Context = LocalContext.current
  val heightScreen = LocalConfiguration.current.screenHeightDp.dp


  @Composable
  fun fetchOffersOnLoad() {
    if (userId != null)
      LaunchedEffect(Unit) {
        val offersCollection = firestore.collection("Offers")
        offersCollection.get().addOnSuccessListener { documents ->
          documents.forEach { document ->
            CoroutineScope(Dispatchers.IO).launch {
              val offer = document.toOfferOutput()
              withContext(Dispatchers.Main) {
                if (offer.employerDetails.id == userId)
                  offersList.add(offer)
              }
            }
          }
        }.addOnFailureListener { exception ->
          Toast.makeText(context, exception.message.toString(), Toast.LENGTH_LONG).show()
        }
      }
    }


  Scaffold(
    bottomBar = {
      BottomNav(navController= navController)
    }
  ) {
    Box {
      CrossedCirclesShapeBlue()
      Text(
        text = "My Current Offers",
        color = Color.Black,
        textAlign = TextAlign.Center,
        lineHeight = 4.29.em,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
          .align(alignment = Alignment.TopCenter)
          .offset(
            x = 0.dp,
            y = 95.dp
          )
          .wrapContentHeight(align = Alignment.CenterVertically)
      )

      fetchOffersOnLoad()
      val listHeight = if (offersList.size > 2) {
        (offersList.size * 170).dp
      } else {
        600.dp
      }
      Column(
        modifier = Modifier
          .requiredHeight(listHeight)
          .offset(y=150.dp)
          .scrollable(rememberScrollState(), Orientation.Vertical)) {
        LazyColumn(
          modifier = Modifier
            .fillMaxHeight()
            .padding(top = 70.dp)
            .scrollable(rememberScrollState(), Orientation.Vertical)

        ) {
          items(offersList) { offer ->
            OffreCard(
              offer = offer,
              firestore = firestore,
              firebaseAuth = firebaseAuth,
              navController = navController,
              sharedOfferViewModel = sharedOfferViewModel
            )
          }
        }
        Button(onClick = { /*Navigate to create job form */ }) {
          Text(
            text = "Create New Offer",
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 6.24.em,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
              .fillMaxSize()
              .wrapContentHeight(align = Alignment.CenterVertically)
          )
        }
      }
    }
  }
}



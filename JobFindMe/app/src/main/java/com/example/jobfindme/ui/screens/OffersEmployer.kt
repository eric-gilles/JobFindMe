package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.jobfindme.ui.components.OffreCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OffersEmployer(modifier:Modifier = Modifier, navController: NavController, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth,sharedOfferViewModel: SharedOfferViewModel) {
  val offersList = remember { mutableStateListOf<OfferOutput>() }
  val userId = firebaseAuth.currentUser?.uid
  val context: Context = LocalContext.current



  @Composable
  fun fetchOffersOnLoad() {
    if (userId != null) {
      LaunchedEffect(Unit) {
        val offersCollection = firestore.collection("Offers")
        try {
          val documents = offersCollection.get().await()
          offersList.clear()
          documents.forEach { document ->
            val offer = document.toOfferOutput()
            if (offer.employerDetails.id == userId) {
              offersList.add(offer)
            }
          }
        } catch (e: Exception) {
          Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG).show()
        }
      }
    }
  }
  fetchOffersOnLoad()
  Scaffold(
    bottomBar = {
      BottomNav(navController = navController)
    },
    floatingActionButton = {

      FloatingActionButton(
        onClick = {
          navController.navigate("JobForm/true")
        },
        containerColor = Color(0xff00adb5)
      ) {
        Icon(Icons.Default.Add, contentDescription = "New Offer")
      }
    }
  ) {
    if(offersList.size>=1){
      MyCurrentOffers(offers = offersList, firestore = firestore, firebaseAuth = firebaseAuth, navController = navController, sharedOfferViewModel = sharedOfferViewModel)
    } else {
      Box {
        CrossedCirclesShapeBlue()
        Text(
          text = "No Offers",
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
      }
    }
  }
}

@Composable
fun MyCurrentOffers(offers: List<OfferOutput>, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth, modifier: Modifier = Modifier, navController: NavController, sharedOfferViewModel: SharedOfferViewModel){
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
  }
  Log.d("OffersList",offers.size.toString())
  LazyColumn(
    modifier = Modifier
      .offset(x = 0.dp, y = 200.dp)
      .verticalScroll(rememberScrollState())
      .height((offers.size * 310).dp)
  ) {
    items(offers, key = { it.id }) { offer ->
      OffreCard(
        offer = offer,
        firestore = firestore,
        firebaseAuth = firebaseAuth,
        navController = navController,
        sharedOfferViewModel = sharedOfferViewModel
      )
    }
  }
}



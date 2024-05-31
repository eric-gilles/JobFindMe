package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.data.toOfferOutput
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeWhite
import com.example.jobfindme.ui.components.FilterDialog
import com.example.jobfindme.ui.components.LogoutButton
import com.example.jobfindme.ui.components.OffreCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
  firebaseAuth: FirebaseAuth,
  firestore: FirebaseFirestore,
  offersList: SnapshotStateList<OfferOutput>
) {
  var searchText by remember { mutableStateOf(TextFieldValue("")) }
  val context: Context = LocalContext.current
  val (showDialog, setShowDialog) = remember { mutableStateOf(false) }


  val job = remember { mutableStateOf("Select Job") }
  val city = remember { mutableStateOf("Select City") }
  val employer = remember { mutableStateOf("Select Employer") }
  val startingDate = remember { mutableStateOf(LocalDate.now()) }
  val endingDate = remember { mutableStateOf(LocalDate.now()) }





  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .shadow(
        elevation = 15.dp,
        shape = RoundedCornerShape(14.dp),
        clip = false
      )
      .fillMaxWidth()
      .background(Color.White, shape = RoundedCornerShape(30))
      .height(60.dp)
  ) {
    TextField(
      value = searchText,
      onValueChange = { newText -> searchText = newText },
      placeholder = { Text(
        text = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = Color.Black,
              fontSize = 16.sp
            )
          ) { append("Search an Offer") }
        },
        modifier = Modifier.fillMaxHeight() // Ensure the placeholder takes full height
      ) },
      colors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      ),
      singleLine = true
    )
    IconButton(onClick = {
      offersList.clear()
      val offersCollection = firestore.collection("Offers")
      offersCollection.get().addOnSuccessListener { documents ->
        documents.forEach { document ->
          CoroutineScope(Dispatchers.IO).launch {
            val offer = document.toOfferOutput()
            withContext(Dispatchers.Main) {
              if(searchText.text!="Search an Offer" && offer.title == searchText.text ) { offersList.add(offer) }
            }
          }
        }
      }.addOnFailureListener { exception ->
        Toast.makeText(context, exception.message.toString(), Toast.LENGTH_LONG).show()
      }

    }) {
      Image(
        painter = painterResource(id = R.drawable.loupe),
        contentDescription = "Search",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(24.dp)
      )
    }

    IconButton(onClick = {
      setShowDialog(true)
    }) {
      Image(
        painter = painterResource(id = R.drawable.filtre),
        contentDescription = "Filter",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(24.dp)
      )
    }
  }
  if (showDialog) {
    FilterDialog(onDismissRequest = {
      setShowDialog(false)
    },
      job = job,
      city = city,
      employer = employer,
      startingDate = startingDate,
      endingDate = endingDate,
      offersList = offersList,
      firestore = firestore)
  }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun SearchOffers(modifier: Modifier = Modifier, navController: NavController, firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore, sharedOfferViewModel: SharedOfferViewModel){
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp
  val context : Context = LocalContext.current
  var offersList = remember { mutableStateListOf<OfferOutput>() }




  @Composable
  fun fetchOffersOnLoad(){
    LaunchedEffect(Unit) {
      val offersCollection = firestore.collection("Offers")
      offersCollection.get().addOnSuccessListener { documents ->
        documents.forEach { document ->
          CoroutineScope(Dispatchers.IO).launch {
            val offer = document.toOfferOutput()
            withContext(Dispatchers.Main) {
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
    topBar = {
      Box(
        modifier = modifier
          .requiredWidth(width = screenWidth)
          .requiredHeight(height = 170.dp)
          .background(color = Color(0xff50c2c9)))
      CrossedCirclesShapeWhite()
      LogoutButton(navController = navController)
    },
    bottomBar = {
      BottomNav(navController= navController)
    }
  ){
    Box(
      modifier=Modifier
        .offset(y = 180.dp)

    ){
      SearchBar(firebaseAuth = firebaseAuth, firestore = firestore, offersList = offersList)
      fetchOffersOnLoad()
      val listHeight = if (offersList.size > 2) {
        (offersList.size * 160).dp
      } else {
        600.dp
      }
      LazyColumn(
        modifier = Modifier
          .requiredHeight(listHeight)
          .padding(top = 70.dp)
          .scrollable(rememberScrollState(), Orientation.Vertical)

      ) {
        items(offersList) { offer ->
          OffreCard(offer=offer, firestore = firestore, firebaseAuth = firebaseAuth, navController = navController, sharedOfferViewModel = sharedOfferViewModel)
        }
      }
    }
  }
}
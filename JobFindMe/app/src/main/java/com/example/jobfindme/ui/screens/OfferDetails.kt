package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.data.NewJobApplication
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.SharedOfferViewModel
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.example.jobfindme.ui.components.LogoutButton
import com.example.jobfindme.ui.utils.isCandidate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OfferDetails(navController: NavController, firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth, sharedOfferViewModel: SharedOfferViewModel) {
  val offer = sharedOfferViewModel.offer


  Scaffold(
    bottomBar = {
      BottomNav(navController= navController)
    }
  ){
    Box(modifier= Modifier.verticalScroll(rememberScrollState())){

      Details(navController= navController, offerOutput = offer, firestore= firestore, firebaseAuth = firebaseAuth)
      CrossedCirclesShapeBlue()
      LogoutButton(navController = navController)
    }
  }
}

@Composable
fun Details(navController: NavController, offerOutput: OfferOutput?, firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore) {
  val userId = firebaseAuth.currentUser?.uid
  val context: Context = LocalContext.current

  fun addNewApplication() {
    userId?.let { userId ->
      offerOutput?.let { offer ->
        val candidaturesRef = firestore.collection("JobApplications")
        candidaturesRef
          .whereEqualTo("userId", userId)
          .whereEqualTo("offerId", offer.id)
          .get()
          .addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty) {
              val newApplication = NewJobApplication(
                userId = userId,
                offerId = offer.id,
                status = "On Going"
              )
              candidaturesRef
                .add(newApplication)
                .addOnSuccessListener { _ ->
                  Toast.makeText(context,"Your candidature has successfully been sent", Toast.LENGTH_LONG).show()
                  navController.navigate("Search")
                }
            } else {
              Toast.makeText(context,"Already applied for this offer", Toast.LENGTH_LONG).show()
            }
          }
          .addOnFailureListener { e ->
            Toast.makeText(context,e.message.toString(), Toast.LENGTH_LONG).show()
          }
      }
    }
  }


  val (isCandidate, setIsCandidate) = remember { mutableStateOf(false) }



  if (userId != null) {
    isCandidate(setIsCandidate,userId, firestore)
  }

  val heightScreen: Dp = if (isCandidate) 1100.dp else 1200.dp
  offerOutput?.let { offer ->
    Column(
      modifier = Modifier
        .background(Color(0xfff6f6f6))
        .padding(horizontal = 16.dp)
        .requiredHeight(heightScreen)

    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 160.dp)
          .align(Alignment.CenterHorizontally)
      ) {
        Text(
          text = offer.title,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xff50c2c9),
          modifier = Modifier
            .padding(bottom = 8.dp)
            .align(Alignment.CenterHorizontally),
          textAlign = TextAlign.Center
        )

        Text(
          text = offer.employerDetails.name,
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Black,
          modifier = Modifier
            .padding(bottom = 8.dp)
            .align(Alignment.CenterHorizontally),
          textAlign = TextAlign.Center
        )

        Text(
          text = offer.city,
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Black,
          modifier = Modifier
            .padding(bottom = 16.dp)
            .align(Alignment.CenterHorizontally),
          textAlign = TextAlign.Center
        )
      }
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
          .shadow(
            elevation = 15.dp,
            shape = RoundedCornerShape(14.dp),
            clip = false
          )
          .background(color = Color.White, shape = RoundedCornerShape(14.dp))
          .padding(8.dp)
          .verticalScroll(rememberScrollState())
      ) {
        Text(
          text = offer.description,
          fontSize = 16.sp,
          color = Color.Black,
          textAlign = TextAlign.Justify
        )
      }

      Spacer(modifier = Modifier.height(16.dp))


      DetailsSection("Job", offer.jobName)
      DetailsSection("Salary", offer.salary.toString())
      DetailsSection("Start Date", offer.startingDate.toReadableString())
      DetailsSection("End Date", offer.endingDate.toReadableString())
      DetailsSection("Employer Address", offer.employerDetails.address)
      Spacer(modifier = Modifier.height(40.dp))
      if(userId!=null){


        Column(
          modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          if (isCandidate && firebaseAuth.currentUser != null) {
            Button(
              onClick = { /* Handle contact action */ },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
              shape = RoundedCornerShape(50),
              modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()


            ) {
              Text(
                text = "Contact",
                color = Color.White,
                fontSize = 18.sp
              ) // Ajustez la taille du texte ici
            }
            Button(
              onClick = {
                addNewApplication()
              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
              shape = RoundedCornerShape(50),
              modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
            ) {
              Text(
                text = "Apply Now",
                color = Color.White,
                fontSize = 18.sp
              )
            }
          } else if (firebaseAuth.currentUser != null) {

            Button(
              onClick = {

              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
              shape = RoundedCornerShape(50),
              modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
            ) {
              Text(text = "Modify", color = Color.White, fontSize = 18.sp)
            }

            Button(
              onClick = {
                navController.navigate(
                  "CandidatureList/false",
                )
              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
              shape = RoundedCornerShape(50),
              modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
            ) {
              Text(text = "All Applications", color = Color.White, fontSize = 18.sp)
            }

            Button(
              onClick = {
                navController.navigate(
                  "CandidatureList/true",
                )
              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xff50c2c9)),
              shape = RoundedCornerShape(50),
              modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
            ) {
              Text(text = "Applications Accepted", color = Color.White, fontSize = 18.sp)
            }
          }
        }
      }
    }
  }?: run {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Text("No offer details available")
    }
  }
}

@Composable
fun DetailsSection(label: String, value: String) {

  Column(modifier = Modifier.padding(vertical = 8.dp)) {
    Text(
      text = label,
      fontSize = 16.sp,
      color = Color.Gray,
      fontWeight = FontWeight.Bold
    )
    Text(
      text = if (label=="Salary") "$value €" else value,
      fontSize = 16.sp,
      color = Color.Black
    )
  }
}

fun Date.toReadableString(): String {
  val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
  return dateFormat.format(this)
}
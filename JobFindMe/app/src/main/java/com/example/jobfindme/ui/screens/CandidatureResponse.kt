package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.User
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.example.jobfindme.ui.components.capitalizeFirstLetter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CandidatureResponse(
  modifier: Modifier = Modifier, firestore: FirebaseFirestore,
  firebaseAuth: FirebaseAuth, offerOutput: OfferOutput,
  isAcceptedList: Boolean, navController: NavController, user: User
) {
  val context = LocalContext.current
  val cvScope = rememberCoroutineScope()


  fun sendEmail() {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
      data = Uri.parse("mailto:${user.email}")
      putExtra(Intent.EXTRA_SUBJECT, "Subject")
      putExtra(Intent.EXTRA_TEXT, "Email body")
    }
    startActivity(context, Intent.createChooser(emailIntent, "Send email"), null)
  }

  fun callPhone() {
    val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
      data = Uri.parse("tel:${user.phone}")
    }
    startActivity(context, phoneIntent, null)
  }

  fun sendSms() {
    val smsIntent = Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse("sms:${user.phone}")
      putExtra("sms_body", "SMS body")
    }
    startActivity(context, smsIntent, null)
  }

  suspend fun downloadCv() {
    val storage = Firebase.storage
    val fileUri = user.uriCV
    Log.d("DownloadFile", "URI du PDF $fileUri")

    val storageRef = storage.reference
    val pdfRef = storageRef.child(fileUri)
    Log.d("DownloadFile", "Downloading ...")

    val localFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "${capitalizeFirstLetter(user.firstname)}_${capitalizeFirstLetter(user.lastname)}-${user.id}.pdf")

    pdfRef.getFile(localFile).addOnSuccessListener {
      Log.d("DownloadFile", "Downloaded")

      val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", localFile)

      val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      }

      val chooser = Intent.createChooser(intent, "Open PDF")
      context.startActivity(chooser)
    }.addOnFailureListener {
      Log.d("DownloadFile", "Failed to download")

      it.printStackTrace()
    }
  }

  fun respondeToCandidature(response: String) {
    val userId = user.id
    userId.let { userId ->
      offerOutput.let { offer ->
        val candidaturesRef = firestore.collection("JobApplications")
        candidaturesRef
          .whereEqualTo("userId", userId)
          .whereEqualTo("offerId", offer.id)
          .get()
          .addOnSuccessListener {
            if (!it.isEmpty) {
              val docId = it.documents[0].id
              candidaturesRef.document(docId)
                .update("status", response)
                .addOnSuccessListener { _ ->
                  Toast.makeText(context,"This candidature has been $response successfully", Toast.LENGTH_LONG).show()
                  navController.navigate("CandidatureResponse/true") {
                    popUpTo("CandidatureResponse/false") { inclusive = true }
                  }
                }
                .addOnFailureListener{ e ->
                  Toast.makeText(context,e.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
          }
          .addOnFailureListener { e ->
            Toast.makeText(context,e.message.toString(), Toast.LENGTH_LONG).show()
          }
      }
    }
  }

  Scaffold(
    bottomBar = {
      BottomNav(navController = navController)
    }
  ) {
    CrossedCirclesShapeBlue()

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

      Box (modifier = Modifier
        .fillMaxWidth()
        .offset(y = 170.dp)
      ){
        Text(
          text = "Candidate: " + capitalizeFirstLetter(user.firstname) + " " + capitalizeFirstLetter(
            user.lastname
          ),
          style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
          modifier = Modifier
            .align(Alignment.TopCenter)
        )
        Column(modifier = Modifier
          .fillMaxWidth()
          .padding(top = 70.dp)
        ) {
          Section(value = "CV", idImg = R.drawable.file_download, onClick = {
            cvScope.launch {
              withContext(Dispatchers.IO) {
                downloadCv()
              }
            }
          })
          Spacer(modifier = Modifier.height(16.dp))
          Section(value = user.email, idImg = R.drawable.email, onClick = {
            sendEmail()
          })
          if (user.phone != null && user.phone != "") {
            Spacer(modifier = Modifier.height(16.dp))
            Section(value = user.phone!!, idImg = R.drawable.phone_call, onClick = {
              callPhone()
            })
            Spacer(modifier = Modifier.height(16.dp))
            Section(value = "SMS", idImg = R.drawable.sms, onClick = {
              sendSms()
            })
          }
          if(!isAcceptedList){
            Row(modifier = modifier
              .align(alignment = Alignment.CenterHorizontally)
              .padding(top = 60.dp)
            ) {
              Image(
                painter = painterResource(id = R.drawable.decline),
                contentDescription = "decline",
                modifier = Modifier
                  .size(90.dp)
                  .clickable {
                    respondeToCandidature("Rejected")
                  }
              )
              Spacer(modifier = modifier.width(30.dp))
              Image(
                painter = painterResource(id = R.drawable.accept),
                contentDescription = "accept",
                modifier = Modifier
                  .size(90.dp)
                  .clickable {
                    respondeToCandidature("Accepted")
                  }
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun Section(value : String, idImg: Int, onClick: () -> Unit){
  Box(
      modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(44.dp)
    ) {

    Row {
      Image(
        painter = painterResource(id = idImg),
        contentDescription = value,
        colorFilter = ColorFilter.tint(Color.Black),
        modifier = Modifier
          .size(80.dp)
          .padding(end = 16.dp)
          .clickable {
            onClick()
          }
      )
      Text(
        text = value,
        color = Color(0xff1e1e1e),
        lineHeight = 5.77.em,
        style = TextStyle(
          fontSize = 26.sp,
          fontWeight = FontWeight.Medium
        ),
        maxLines = 1,

        modifier = Modifier
          .padding(start = 16.dp)
          .horizontalScroll(rememberScrollState())
      )
    }
  }
}
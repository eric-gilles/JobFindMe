package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobfindme.data.OfferOutput
import com.example.jobfindme.data.SharedUserViewModel
import com.example.jobfindme.data.User
import com.example.jobfindme.data.toUser
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeBlue
import com.example.jobfindme.ui.components.LogoutButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun Title(
  title: String,
  maxLength: Int = 20,
  textAlign: TextAlign,
  color: Color,
  modifier: Modifier,
  style: TextStyle
) {
  Text(
    text = if (title.length> maxLength) title.take(maxLength)+"..." else title,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign,
    color = color,
    modifier = modifier,
    style = style
  )

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CandidaturesList(modifier: Modifier = Modifier, firestore: FirebaseFirestore,
                     firebaseAuth: FirebaseAuth, offerOutput: OfferOutput,
                     isAcceptedList: Boolean, navController:NavController, sharedUserViewModel: SharedUserViewModel){


  val offerId = offerOutput.id
  var userIds by remember { mutableStateOf<List<String>>(emptyList()) }
  var users by remember { mutableStateOf<List<User>>(emptyList()) }
  val context: Context = LocalContext.current
  @Composable
  fun fetchUsersFromOfferOnLoad(offerId: String, isAcceptedList: Boolean){
    LaunchedEffect(offerId, isAcceptedList) {
      if (offerId.isNotEmpty()) {
        val querySnapshot = firestore.collection("JobApplications")
          .whereEqualTo("offerId", offerId)
          .get()
          .await()

        val ids = querySnapshot.documents.mapNotNull { it.getString("userId") }

        // Filter user IDs based on acceptance status
        userIds = if (isAcceptedList) {
          ids.filter { userId ->
            val applicationData = querySnapshot.documents.find { it.getString("userId") == userId }
            val status = applicationData?.getString("status")
            status == "Accepted"
          }
        } else {
          ids.filter { userId ->
            val applicationData = querySnapshot.documents.find { it.getString("userId") == userId }
            val status = applicationData?.getString("status")
            status == "On Going"
          }
        }

        // Fetch user data based on user IDs
        val usersList = mutableListOf<User>()
        for (userId in userIds) {
          val userDoc = firestore.collection("Users").document(userId).get().await()
          val user = userDoc.toUser()
          user.let { usersList.add(it) }
        }
        users = usersList
      }
    }
  }
  fetchUsersFromOfferOnLoad(offerId=offerId,isAcceptedList=isAcceptedList)
  Scaffold(
    bottomBar = {
      BottomNav(navController= navController)
    }
  ){
    Box{
      Box(modifier= Modifier.offset(y=160.dp)){

        UserList(
          users = users,
          offerOutput = offerOutput,
          isAcceptedList= isAcceptedList,
          sharedUserViewModel = sharedUserViewModel,
          navController = navController
          )
      }
      CrossedCirclesShapeBlue()
      LogoutButton(navController = navController)
    }
  }
}


@Composable
fun UserList(users: List<User>,offerOutput: OfferOutput, isAcceptedList:Boolean, sharedUserViewModel: SharedUserViewModel, navController: NavController) {
  Column(modifier = Modifier
    .padding(horizontal = 16.dp, vertical = 25.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Title(
        title = offerOutput.title,
        style = TextStyle(fontSize = 28.sp),
        textAlign = TextAlign.Center,
        color = Color(0xff50c2c9),
        modifier = Modifier.fillMaxWidth()
      )
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),

      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = if(isAcceptedList) "Accepted candidates" else "Pending candidates",
        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
      )
    }

    Column(
      modifier = Modifier
        .fillMaxSize() // Ensure the column fills the screen
        .verticalScroll(rememberScrollState()) // Enable scrolling
        .padding(vertical = 30.dp, horizontal = 20.dp)
    ) {
      Column( // Use a nested Column with optional minimum height
        modifier = Modifier.fillMaxWidth().height(120.dp*users.size)
      ) {
        users.forEach { user ->
          UserItem(user = user, onItemClick = {
            sharedUserViewModel.addUser(user)
            navController.navigate("CandidatureResponse/$isAcceptedList")
          })
          Spacer(modifier = Modifier.height(16.dp))
        }
      }
    }
  }
}

@Composable
fun UserItem(user: User, onItemClick: (User) -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()

      .clip(RoundedCornerShape(8.dp))
      .background(Color(0xff50c2c9))
      .clickable { onItemClick(user) }
      .padding(16.dp)
      ,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(text = user.firstname+" "+user.lastname,
      textAlign = TextAlign.Center,
      style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xfff6f6f6)),
    )
    ArrowIcon(imageVector = Icons.AutoMirrored.Filled.ArrowForward)
  }
}

@Composable
fun ArrowIcon(imageVector: ImageVector) {
  Icon(
    imageVector = imageVector,
    contentDescription = null,
    tint = Color(0xfff6f6f6),
    modifier = Modifier.size(24.dp)
  )
}


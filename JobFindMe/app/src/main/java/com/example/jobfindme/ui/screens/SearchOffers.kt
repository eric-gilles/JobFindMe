package com.example.jobfindme.ui.screens

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfindme.R
import com.example.jobfindme.ui.components.BottomNav
import com.example.jobfindme.ui.components.CrossedCirclesShapeWhite
import com.example.jobfindme.ui.components.FilterDialog
import com.example.jobfindme.ui.components.LogoutButton
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
) {
  var searchText by remember { mutableStateOf(TextFieldValue("")) }
  val context: Context = LocalContext.current
  val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .width(365.dp)
      .background(Color.White, shape = CircleShape)
      .padding(8.dp)
      .height(56.dp)
  ) {
    TextField(
      value = searchText,
      onValueChange = { newText -> searchText = newText },
      modifier = Modifier
        .weight(1f)
        .padding(start = 8.dp),
      placeholder = { Text("Search an Offer") },
      colors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      ),
      singleLine = true
    )
    IconButton(onClick = { Toast.makeText(context, "Not yet implemented",Toast.LENGTH_SHORT).show() }) {
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
    })
  }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "Range")
@Composable
fun SearchOffers(modifier: Modifier = Modifier, navController: NavController, firebaseAuth: FirebaseAuth){
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp
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
  ){}
  SearchBar()

}
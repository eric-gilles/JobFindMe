package com.example.jobfindme.ui.components


import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jobfindme.R

@Composable
fun LogoutButton(
  modifier: Modifier = Modifier,
  navController: NavController,
) {
  Row(
    modifier = modifier.fillMaxWidth().padding(top = 35.dp, start = 300.dp)
  ) {
    Button(
      onClick = {
        Log.d("Singout","Je pars !")
        navController.navigate("Signout")
      },
      modifier = Modifier.requiredSize(50.dp),
      colors = ButtonDefaults.buttonColors(Color.White),
      shape = RoundedCornerShape(8.dp),
      contentPadding = PaddingValues(0.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.logout_logo_100),
        contentDescription = "Logout Button"
      )
    }
  }
}
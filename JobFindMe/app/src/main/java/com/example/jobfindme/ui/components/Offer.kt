package com.example.jobfindme.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobfindme.data.OfferOutput


@Composable
fun OffreCard(offre: OfferOutput) {
  val limit = 50
  var showFullDescription by remember { mutableStateOf(offre.description.length <= limit) }

  fun limitDescription(description: String, maxLength: Int = limit): String {
    return if (description.length > maxLength) {
      description.substring(0, maxLength).trimEnd() + "..."
    } else {
      description
    }
  }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .shadow(
        elevation = 15.dp,
        shape = RoundedCornerShape(14.dp),
        clip = false
      )
      .clip(RoundedCornerShape(12.dp))
      .background(color = Color.White)
      .padding(16.dp)


  ) {
    Column {
      Text(
        text = offre.title,
        color = Color(0xff50c2c9),
        fontSize = 20.sp,
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = "Employer: ${offre.employerDetails.name}",
        color = Color(0xff000000),
        fontSize = 16.sp,
        textAlign = TextAlign.Justify
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "City: ${offre.city}",
        color = Color(0xff000000),
        fontSize = 14.sp,
        textAlign = TextAlign.Justify
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = if (showFullDescription) offre.description else limitDescription(offre.description),
        color = Color(0xff000000),
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis ,
        textAlign = TextAlign.Justify
      )
      if (offre.description.length > limit) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          if (!showFullDescription) {
            Text(
              text = "Show more",
              color = Color(0xff50c2c9),
              fontSize = 12.sp,
              modifier = Modifier.clickable {
                showFullDescription = true
              }
            )
          } else {
            Text(
              text = "Show less",
              color = Color(0xff50c2c9),
              fontSize = 12.sp,
              modifier = Modifier.clickable {
                showFullDescription = false
              }
            )
          }
        }
      }
    }
  }
}



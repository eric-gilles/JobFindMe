package com.example.jobfindme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobfindme.R

// Composable for the bottom navigation bar
@Composable
fun BottomNav(modifier: Modifier = Modifier) {
  // Main container
  Box(
    modifier = modifier
      .requiredWidth(width = 393.dp)
      .requiredHeight(height = 129.dp)
      .clip(shape = RoundedCornerShape(24.dp))
  ) {
    // Container for the bottom navigation bar
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .align(alignment = Alignment.BottomStart)
        .offset(x = 0.dp, y = (-30).dp)
        .requiredWidth(width = 393.dp)
        .background(color = Color.White)
        .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp)
    ) {
      // Navigation items
      Spacer(modifier = Modifier.size(12.dp))
      BottomNavItem(Icons.Filled.Home, "Home")
      Spacer(modifier = Modifier.size(12.dp))
      BottomNavItem(Icons.Filled.Search, "Search")
      Spacer(modifier = Modifier.size(12.dp))
      Image(
        painter = painterResource(id = R.drawable.app_logo_rounded),
        contentDescription = "logo",
        modifier = Modifier.size(80.dp)
      )
      Spacer(modifier = Modifier.size(12.dp))
      BottomNavItem(Icons.Default.AccessTime, "Post")
      Spacer(modifier = Modifier.size(12.dp))
      BottomNavItem(Icons.Filled.AccountCircle, "Account")
      Spacer(modifier = Modifier.size(12.dp))
    }
    // Decoration for the bottom navigation bar
    Box(
      modifier = Modifier
        .align(alignment = Alignment.BottomStart)
        .offset(x = 0.dp, y = 0.dp)
        .requiredWidth(width = 393.dp)
        .requiredHeight(height = 30.dp)
        .background(color = Color.White)
    ) {
      Box(
        modifier = Modifier
          .align(alignment = Alignment.BottomCenter)
          .offset(x = 0.dp, y = (-8).dp)
          .requiredWidth(width = 135.dp)
          .requiredHeight(height = 5.dp)
          .clip(shape = RoundedCornerShape(100.dp))
          .background(color = Color(0xffb9c0c9))
      )
    }
  }
}

// Composable for the bottom navigation item
@Composable
private fun BottomNavItem(icon: ImageVector, contentDescription: String) {
  Column(
    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
      modifier = Modifier
        .clip(shape = RoundedCornerShape(100.dp))
        .padding(all = 12.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = Modifier.size(30.dp)
      )
    }
  }
}

// Preview of the bottom navigation bar
@Preview(widthDp = 393, heightDp = 129)
@Composable
private fun BottomNavPreview() {
  BottomNav(Modifier)
}

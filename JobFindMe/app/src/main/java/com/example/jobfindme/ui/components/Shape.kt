package com.example.jobfindme.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Shape(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .requiredWidth(width = 290.dp)
      .requiredHeight(height = 270.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(shape = CircleShape)
        .background(color = Color(0xff6ae0d9).copy(alpha = 0.49f)))
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(shape = CircleShape)
        .background(color = Color(0xff6ae0d9).copy(alpha = 0.49f)))
  }
}
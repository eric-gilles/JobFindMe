package com.example.jobfindme.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun CrossedCirclesShapeBlue(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      val circleRadius = 102.085.dp.toPx()
      val offset1 = Offset(265f, 0f)
      val offset2 = Offset(0f, 175f)

      drawCircle(Color(0x696ae0d9), radius = circleRadius, center = offset1)
      drawCircle(Color(0x696ae0d9), radius = circleRadius, center = offset2)
    }
  }
}

@Composable
@Preview
fun CrossedCirclesShapeWhite(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      val circleRadius = 102.085.dp.toPx()
      val offset1 = Offset(265f, 0f)
      val offset2 = Offset(0f, 175f)

      drawCircle(Color(0x49f6f6f6), radius = circleRadius, center = offset1)
      drawCircle(Color(0x49f6f6f6), radius = circleRadius, center = offset2)
    }
  }
}
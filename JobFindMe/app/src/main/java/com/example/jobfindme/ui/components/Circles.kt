package com.example.jobfindme.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
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
fun CrossedCircles(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(Color(0xff6ae0d9), radius = 100.dp.toPx(), center = Offset(100f, 180f))
            drawCircle(Color(0xff6ae0d9), radius = 100.dp.toPx(), center = Offset(180f, 100f))
        }
    }
}

@Preview
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

@Preview
@Composable
private fun ShapePreview() {
    Shape(Modifier)
}

@Preview
@Composable
private fun SplashPreview() {
    CrossedCircles(Modifier)
}
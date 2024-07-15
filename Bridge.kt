package com.example.tomandjerry

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalRectangleCanvas(
    positionY: Dp,
    height: Dp,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
        .offset((space / 2 + 8).dp, positionY)
        .height(height)
        .width((300 + 2 * space - 16).dp)
    ) {
            val bridgeWidth = size.width
            val bridgeHeight = height.toPx()

            // Draw the main bridge body with a rusty color
            drawRect(
                color = Color(0xFF8B4513), // Saddle brown color for the base
                size = Size(bridgeWidth, bridgeHeight)
            )

            // Adding rust effect by overlaying with different shades
            drawRect(
                color = Color(0xFFA0522D).copy(alpha = 0.6f), // Sienna color for rust effect
                size = Size(bridgeWidth, bridgeHeight)
            )

            drawRect(
                color = Color(0xFFCD853F).copy(alpha = 0.4f), // Peru color for rust effect
                size = Size(bridgeWidth, bridgeHeight)
            )

            // Draw the bridge borders
            drawRect(
                color = Color(0xFF8B4513), // Same saddle brown color for the borders
                size = Size(bridgeWidth, bridgeHeight),
                style = Stroke(width = 8.dp.toPx())
            )

            // Draw vertical lines to give the bridge a segmented look
            val numberOfSegments = 30
            val segmentWidth = bridgeWidth / numberOfSegments
            for (i in 1 until numberOfSegments) {
                drawLine(
                    color = Color(0xFF8B4513), // Same saddle brown color for the lines
                    start = Offset(i * segmentWidth, 0f),
                    end = Offset(i * segmentWidth, bridgeHeight),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            // Draw horizontal lines for a more detailed rusty look
            val numberOfHorizontalSegments = 5
            val segmentHeight = bridgeHeight / numberOfHorizontalSegments
            for (j in 1 until numberOfHorizontalSegments) {
                drawLine(
                    color = Color(0xFF8B4513), // Same saddle brown color for the lines
                    start = Offset(0f, j * segmentHeight),
                    end = Offset(bridgeWidth, j * segmentHeight),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            // Draw the black border on top
            drawRect(
                color = Color.Black,
                topLeft = Offset(0f,-8f) ,
                size = Size(bridgeWidth, bridgeHeight + 16),
                style = Stroke(width = 4.dp.toPx())
            )
    }
    }
}
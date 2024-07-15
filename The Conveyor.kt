package com.example.tomandjerry

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Conveyor(space: Double) {
    val gridColor = Color.Gray
    val beltColor = Color.DarkGray
    val borderColor = Color.Black

    // Infinite transition for the animated lines
    val infiniteTransition = rememberInfiniteTransition(label = "animation")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if(!gameOver) 130f else 0f, // Line spacing
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "conveyor"
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(space.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .background(color = gridColor)
                    .border(4.dp, borderColor)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val leftLineSpacing = 40.dp.toPx()
                    val leftMargin = 10.dp.toPx()
                    val rightMargin = size.width - 10.dp.toPx()


                    // Start drawing lines slightly below the top
                    val startY = +animatedOffset % leftLineSpacing

                    for (i in 0 until (size.height / leftLineSpacing).toInt() + 2) {
                        val yOffsetLeft = startY + (i * leftLineSpacing)

                        // Ensure lines are within visible bounds
                        if (yOffsetLeft > 0 && yOffsetLeft < size.height) {
                            // Left side lines
                            drawLine(
                                color = beltColor,
                                start = Offset(0f, yOffsetLeft),
                                end = Offset(leftMargin, yOffsetLeft),
                                strokeWidth = 4.dp.toPx()
                            )

                            // Right side lines
                            drawLine(
                                color = beltColor,
                                start = Offset(rightMargin, yOffsetLeft),
                                end = Offset(size.width, yOffsetLeft),
                                strokeWidth = 4.dp.toPx()
                            )
                        }
                    }

                    // Vertical lines separating the horizontal lines and blank space
                    drawLine(
                        color = borderColor,
                        start = Offset(leftMargin, 0f),
                        end = Offset(leftMargin, size.height),
                        strokeWidth = 4.dp.toPx()
                    )
                    drawLine(
                        color = borderColor,
                        start = Offset(rightMargin, 0f),
                        end = Offset(rightMargin, size.height),
                        strokeWidth = 4.dp.toPx()
                    )



                    // Central vertical line
//                    drawLine(
//                        color = Color.Red,
//                        start = Offset(centerX, 0f),
//                        end = Offset(centerX, size.height),
//                        strokeWidth = 4.dp.toPx()
//                    )
                }
            }
        }
    }
}
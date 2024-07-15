package com.example.tomandjerry

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun Obstacle(offsetX: Double,offsetY: Double, item:Int,bulletHit:Boolean) {


    val crateColor = Color(0xFFA0522D) // SaddleBrown
    val shadingColor = Color(0xFF8B4513) // Darker Brown for shading
    val boltColor = Color(0xFF555555) // Gray for bolts
    val crackColor = Color(0xFF3E2723) // Dark Brown for cracks
    val beltColor = Color(0xFFFFA500) // Orange for the belt
    val nailRadius = 4.dp
    val barrelColor = Color(0xFF8B4513) // Brown
    val ringColor = Color.Gray




    // Create an infinite transition for rotating effect
    val infiniteTransition = rememberInfiniteTransition(label = "barrel")
    val animatedFraction by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if(!gameOver)1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "barrel"
    )

    if(!bulletHit)
    {
        when (item) {
            0 -> {
                Canvas(
                    modifier = Modifier
                        .size(55.dp)  // Ensure canvas size matches the desired size
                        .offset(offsetX.dp, offsetY.dp)
                ) {
                    val squareSize = size.width  // Assuming canvas is square based on size.width

                    drawRect(
                        color = crateColor,
                        size = Size(squareSize, squareSize)
                    )

                    drawRect(
                        color = Color.Black,
                        size = Size(squareSize, squareSize),
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )

                    // Adding shading to the bottom and right sides
                    drawRect(
                        color = shadingColor.copy(alpha = 0.3f),
                        topLeft = Offset(0f, squareSize - 10f),
                        size = Size(squareSize, 10f)
                    )
                    drawRect(
                        color = shadingColor.copy(alpha = 0.3f),
                        topLeft = Offset(squareSize - 10f, 0f),
                        size = Size(10f, squareSize)
                    )

                    // Adding bolts at the corners
                    val boltSize = 8f
                    drawCircle(
                        color = boltColor,
                        radius = boltSize,
                        center = Offset(boltSize * 2, boltSize * 2)
                    )
                    drawCircle(
                        color = boltColor,
                        radius = boltSize,
                        center = Offset(squareSize - boltSize * 2, boltSize * 2)
                    )
                    drawCircle(
                        color = boltColor,
                        radius = boltSize,
                        center = Offset(boltSize * 2, squareSize - boltSize * 2)
                    )
                    drawCircle(
                        color = boltColor,
                        radius = boltSize,
                        center = Offset(squareSize - boltSize * 2, squareSize - boltSize * 2)
                    )

                    // Adding cracks and wear
                    drawLine(
                        color = crackColor,
                        start = Offset(squareSize * 0.3f, squareSize * 0.1f),
                        end = Offset(squareSize * 0.7f, squareSize * 0.3f),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = crackColor,
                        start = Offset(squareSize * 0.2f, squareSize * 0.8f),
                        end = Offset(squareSize * 0.5f, squareSize * 0.6f),
                        strokeWidth = 2f
                    )

                    // Draw belts on the right and left sides
                    val beltWidth = 50f
                    val beltHeight = squareSize * 0.5f

                    // Left belt
                    drawRect(
                        color = beltColor,
                        size = Size(beltWidth, beltHeight),
                        topLeft = Offset(-beltWidth / 2, (squareSize - beltHeight) / 2)
                    )

                    drawRect(
                        color = Color.Black,
                        size = Size(beltWidth, beltHeight),
                        topLeft = Offset(-beltWidth / 2, (squareSize - beltHeight) / 2),
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )

                    // Right belt
                    drawRect(
                        color = beltColor,
                        size = Size(beltWidth, beltHeight),
                        topLeft = Offset(squareSize - beltWidth / 2, (squareSize - beltHeight) / 2)
                    )

                    drawRect(
                        color = Color.Black,
                        size = Size(beltWidth, beltHeight),
                        topLeft = Offset(squareSize - beltWidth / 2, (squareSize - beltHeight) / 2),
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )

                    // Draw nails on the belts
                    val beltNailPositions = listOf(
                        Offset(-beltWidth / 2 + beltWidth / 2, (squareSize - beltHeight) / 2 + beltHeight / 4),
                        Offset(-beltWidth / 2 + beltWidth / 2, (squareSize + beltHeight) / 2 - beltHeight / 4),
                        Offset(squareSize - beltWidth / 2 + beltWidth / 2, (squareSize - beltHeight) / 2 + beltHeight / 4),
                        Offset(squareSize - beltWidth / 2 + beltWidth / 2, (squareSize + beltHeight) / 2 - beltHeight / 4)
                    )

                    for (nailPosition in beltNailPositions) {
                        drawCircle(
                            color = boltColor,
                            radius = nailRadius.toPx(),
                            center = nailPosition
                        )
                    }
                }



            }
            1 -> {
                Canvas(modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .offset(offsetX.dp, offsetY.dp)) {

                    val squareSize = size.width  // Assuming canvas is square based on width

                    val mainRectWidth = size.width
                    val mainRectHeight = size.height * 0.7f



                    // Draw the main rectangle
                    drawRect(
                        color = Color.White,
                        size = Size(mainRectWidth, mainRectHeight)
                    )

                    drawRect(
                        color = Color.Black,
                        size = Size(mainRectWidth, mainRectHeight),
                        style = Stroke(width = 4.dp.toPx())
                    )

                    // Define the number of horizontal lines
                    val numberOfLines = 5
                    val lineSpacing = mainRectHeight / (numberOfLines + 1)

                    // Draw the horizontal lines
                    for (i in 1..numberOfLines) {
                        val yOffset = i * lineSpacing
                        drawLine(
                            color = Color.Black,
                            start = Offset(x = 0f, y = yOffset),
                            end = Offset(x = mainRectWidth, y = yOffset),
                            strokeWidth = 10f // Adjust the stroke width as needed
                        )
                    }

                    // Draw the main barrel body horizontally
                    drawRoundRect(
                        color = barrelColor,
                        size = Size(squareSize, squareSize*0.7f),
                        cornerRadius = CornerRadius(squareSize * 0.35f, squareSize * 0.35f),
                        topLeft = Offset(0f, 0f)
                    )

                    // Adjust the ring positions so they fit within the barrel
                    val ringTopOffset = 0f
                    val ringBottomOffset =  squareSize * 0.7f
                    val ringLeftOffset = squareSize * 0.3f
                    val ringRightOffset = squareSize * 0.7f

                    val ringPositions = listOf(
                        Offset(ringLeftOffset, ringTopOffset),
                        Offset(ringLeftOffset, ringBottomOffset),
                        Offset(ringRightOffset, ringTopOffset),
                        Offset(ringRightOffset, ringBottomOffset)
                    )

                    for (i in ringPositions.indices step 2) {
                        // Draw the black border for the ring
                        drawLine(
                            color = Color.Black,
                            start = ringPositions[i],
                            end = ringPositions[i + 1],
                            strokeWidth = 14.dp.toPx()
                        )

                        // Draw the ring
                        drawLine(
                            color = ringColor,
                            start = ringPositions[i],
                            end = ringPositions[i + 1],
                            strokeWidth = 8.dp.toPx()
                        )

                        // Draw two animated nails on each vertical ring
                        val nailCount = 2
                        for (j in 1..nailCount) {
                            val fraction = (animatedFraction + (j - 1) / nailCount.toFloat()) % 1f
                            val nailPosition = Offset(
                                x = ringPositions[i].x,
                                y = ringPositions[i].y + fraction * (ringPositions[i + 1].y - ringPositions[i].y)
                            )
                            drawCircle(color = Color.Black, radius = nailRadius.toPx(), center = nailPosition)
                        }
                    }


                    // Draw the black border for the barrel body
                    drawRoundRect(
                        color = Color.Black,
                        size = Size(squareSize, squareSize*0.7f),
                        cornerRadius = CornerRadius(squareSize * 0.35f, squareSize * 0.35f),
                        topLeft = Offset(0f, 0f),
                        style = Stroke(width = 3.dp.toPx())
                    )


                }

            }
            2 -> {
                // Define the number of spikes and their dimensions
                val numberOfSpikes = 5
                val spikeWidthDp = 11.dp // This will make the total width exactly 55.dp
                val spikeHeightDp = 15.dp // Height of the canvas to accommodate the spike height

                Canvas(
                    modifier = Modifier
                        .width(spikeWidthDp * numberOfSpikes)
                        .height(3 * spikeHeightDp)
                        .offset(offsetX.dp, offsetY.dp)
                ) {

                    drawRect(
                        color = Color.Black,
                        size = Size(size.width, size.height),
                        style = Stroke(width = 20f)
                    )

                    drawRect(
                        color =  Color(0xFF7E57C2),
                        size = Size(size.width, size.height),
                    )


                    // Convert spike dimensions to pixels
                    val spikeWidth = spikeWidthDp.toPx()
                    val spikeHeight = spikeHeightDp.toPx()

                    // Function to create a shiny gradient color
                    fun createSpikeGradient(): Brush {
                        return Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFD1C4E9), // Light purple
                                Color(0xFF7E57C2), // Medium purple
                                Color(0xFF311B92)  // Dark purple
                            )
                        )
                    }

                    // Draw the first set of spikes with borders
                    for (i in 0 until numberOfSpikes) {
                        val xOffset = i * spikeWidth

                        // Draw the spike with gradient
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height / 3)
                                close()
                            },
                            brush = createSpikeGradient()
                        )

                        // Draw the highlight for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset + spikeWidth / 2, size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, size.height / 3 - spikeHeight / 2)
                                close()
                            },
                            color = Color.White.copy(alpha = 0.5f)
                        )

                        // Draw the border for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height / 3)
                                close()
                            },
                            color = Color.Black,
                            style = Stroke(width = 3.dp.toPx())
                        )
                    }

                    // Draw the second set of spikes with borders
                    for (i in 0 until numberOfSpikes) {
                        val xOffset = i * spikeWidth

                        // Draw the spike with gradient
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, 2 * size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, 2 * size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, 2 * size.height / 3)
                                close()
                            },
                            brush = createSpikeGradient()
                        )

                        // Draw the highlight for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset + spikeWidth / 2, 2 * size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, 2 * size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, 2 * size.height / 3 - spikeHeight / 2)
                                close()
                            },
                            color = Color.White.copy(alpha = 0.5f)
                        )

                        // Draw the border for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, 2 * size.height / 3)
                                lineTo(xOffset + spikeWidth / 2, 2 * size.height / 3 - spikeHeight)
                                lineTo(xOffset + spikeWidth, 2 * size.height / 3)
                                close()
                            },
                            color = Color.Black,
                            style = Stroke(width = 3.dp.toPx())
                        )
                    }

                    // Draw the third set of spikes with borders
                    for (i in 0 until numberOfSpikes) {
                        val xOffset = i * spikeWidth

                        // Draw the spike with gradient
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, size.height)
                                lineTo(xOffset + spikeWidth / 2, size.height - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height)
                                close()
                            },
                            brush = createSpikeGradient()
                        )

                        // Draw the highlight for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset + spikeWidth / 2, size.height - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height)
                                lineTo(xOffset + spikeWidth / 2, size.height - spikeHeight / 2)
                                close()
                            },
                            color = Color.White.copy(alpha = 0.5f)
                        )

                        // Draw the border for the spike
                        drawPath(
                            path = Path().apply {
                                moveTo(xOffset, size.height)
                                lineTo(xOffset + spikeWidth / 2, size.height - spikeHeight)
                                lineTo(xOffset + spikeWidth, size.height)
                                close()
                            },
                            color = Color.Black,
                            style = Stroke(width = 3.dp.toPx())
                        )
                    }
                }



            }
            else -> {
                val squareSize = 150f
                val centerX = 75f
                val centerY = 75f
                val fanRadius = 5f
                val bladeWidth = 20f
                val bladeLength = 50f

                // Animation for rotating the fan blades
                val infiniteTransition = rememberInfiniteTransition(label = "Obstacle Transition")
                val animatedRotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f ,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 200, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ), label = "Obstacle"
                )

                Canvas(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .offset(offsetX.dp, offsetY.dp)
                ) {

                    val cornerSize = 20.dp.toPx()
                    val screwRadius = 4.dp.toPx()

    //            drawRect(
    //                color = Color.Red,
    //                size = Size(size.width, size.height),
    //                style = Stroke(width = 20f)
    //            )


    // Draw the outer yellow square with gradient effect
                    drawRect(
                        color = Color(0xFFFFD600),
                        topLeft = Offset(centerX - squareSize / 2, centerY - squareSize / 2),
                        size = Size(squareSize, squareSize)
                    )

    // Draw the outer black stroke square
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(centerX - squareSize / 2, centerY - squareSize / 2),
                        size = Size(squareSize, squareSize),
                        style = Stroke(width = 4.dp.toPx())
                    )

    // Draw the fan blades
                    for (i in 0 until 4) {
                        rotate(degrees = animatedRotation + i * 90, pivot = Offset(centerX, centerY)) {
                            drawRect(
                                color = Color.Black,
                                topLeft = Offset(
                                    centerX - bladeWidth / 2,
                                    centerY - fanRadius - bladeLength
                                ),
                                size = Size(bladeWidth, bladeLength)
                            )
                        }
                    }


    //// Draw air flow lines
    //            for (j in 0 until 15) { // Adjust the number of lines as needed
    //                val angle = (360 / 15) * j.toFloat() + animatedRotation
    //                val startX = centerX + 60f * Math.cos(Math.toRadians(angle.toDouble())).toFloat()
    //                val startY = centerY + 60f * Math.sin(Math.toRadians(angle.toDouble())).toFloat()
    //                drawLine(
    //                    color = Color.DarkGray,
    //                    start = Offset(startX, startY - 50f),
    //                    end = Offset(startX, startY + animatedAirOffset),
    //                    strokeWidth = 4.dp.toPx()
    //                )
    //            }

    // Draw bolts in the corners
                    val boltPositions = listOf(
                        Offset(centerX - squareSize / 2 + cornerSize, centerY - squareSize / 2 + cornerSize), // Top-left
                        Offset(centerX + squareSize / 2 - cornerSize, centerY - squareSize / 2 + cornerSize), // Top-right
                        Offset(centerX - squareSize / 2 + cornerSize, centerY + squareSize / 2 - cornerSize), // Bottom-left
                        Offset(centerX + squareSize / 2 - cornerSize, centerY + squareSize / 2 - cornerSize)  // Bottom-right
                    )

                    for (boltPosition in boltPositions) {
                        drawCircle(
                            color = Color.Gray,
                            radius = screwRadius,
                            center = boltPosition
                        )
                        drawCircle(
                            color = Color.Black,
                            radius = screwRadius / 2,
                            center = boltPosition
                        )
                    }

    // Add some fancy details inside the square (optional)
                    drawCircle(
                        color = Color.LightGray,
                        radius = fanRadius / 3,
                        center = Offset(centerX, centerY),
                        style = Stroke(width = 2.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Gray,
                        radius = fanRadius / 6,
                        center = Offset(centerX, centerY),
                        style = Stroke(width = 2.dp.toPx())
                    )

                    drawLine(
                        color = Color.Gray,
                        start = Offset(centerX - fanRadius / 3, centerY),
                        end = Offset(centerX + fanRadius / 3, centerY),
                        strokeWidth = 2.dp.toPx()
                    )

                    drawLine(
                        color = Color.Gray,
                        start = Offset(centerX, centerY - fanRadius / 3),
                        end = Offset(centerX, centerY + fanRadius / 3),
                        strokeWidth = 2.dp.toPx()
                    )

                    // Draw bolts at the corners of the outer square
                    val outerBoltPositions = listOf(
                        Offset(centerX + 30f - squareSize / 2, centerY + 30f - squareSize / 2), // Top-left
                        Offset(centerX - 30f + squareSize / 2, centerY + 30f - squareSize / 2), // Top-right
                        Offset(centerX + 30f - squareSize / 2, centerY - 30f  + squareSize / 2), // Bottom-left
                        Offset(centerX - 30f + squareSize / 2, centerY - 30f  + squareSize / 2)  // Bottom-right
                    )

                    for (boltPosition in outerBoltPositions) {
                        drawCircle(
                            color = Color.Gray,
                            radius = screwRadius,
                            center = boltPosition
                        )
                        drawCircle(
                            color = Color.Black,
                            radius = screwRadius / 2,
                            center = boltPosition
                        )
                    }

                }
            }
        }
    }
}
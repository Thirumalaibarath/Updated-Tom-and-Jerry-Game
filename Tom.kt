package com.example.tomandjerry


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp



// Tom

@Composable
fun Tom(x: Double, y: Double, r: Int, color: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val radiusPx = r.dp.toPx()
        val centerX = x.dp.toPx()
        val centerY = y.dp.toPx()
        val eyeOffsetX = radiusPx * 0.3f
        val eyeOffsetY = radiusPx * 0.2f
        val eyeRadius = radiusPx * 0.2f
        val noseRadius = radiusPx * 0.2f

        // ears
        drawCircle(
            color = Color(0xFF474747),
            radius = radiusPx*0.5f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
        )

        drawCircle(
            color = Color.Black,
            radius = radiusPx*0.5f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
            style = Stroke(width = (r/6.6).dp.toPx(), cap = StrokeCap.Round),
        )

        drawCircle(
            color = Color(0xFF474747),
            radius = radiusPx*0.5f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
        )

        drawCircle(
            color = Color.Black,
            radius = radiusPx*0.5f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
            style = Stroke(width =(r/6.6).dp.toPx(), cap = StrokeCap.Round),
        )

        // inside Ear

        drawCircle(
            color = earColor,
            radius = radiusPx*0.3f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
        )



        drawCircle(
            color = earColor,
            radius = radiusPx*0.3f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
        )

        // tail

        drawPath(
            path = Path().apply {
                moveTo(centerX  , centerY + radiusPx-(1.5*r).toFloat())
                lineTo(centerX  , centerY + radiusPx + r.toFloat())
                lineTo(centerX + r.toFloat() , centerY + radiusPx +r.toFloat())
                lineTo(centerX  + r.toFloat(), centerY + radiusPx + (3.5*r).toFloat())
                lineTo(centerX + (r/4).toFloat(), centerY + radiusPx + (2*r).toFloat())
                lineTo(centerX-r.toFloat() , centerY + radiusPx + (2*r).toFloat())
                close()
            },
            color = color,
        )

        // tail border

        drawPath(
            path = Path().apply {
                moveTo(centerX  , centerY + radiusPx-(1.5*r).toFloat())
                lineTo(centerX  , centerY + radiusPx + r.toFloat())
                lineTo(centerX + r.toFloat() , centerY + radiusPx +r.toFloat())
                lineTo(centerX  + r.toFloat(), centerY + radiusPx + (3.5*r).toFloat())
                lineTo(centerX + (r/4).toFloat(), centerY + radiusPx + (2*r).toFloat())
                lineTo(centerX-r.toFloat() , centerY + radiusPx + (2*r).toFloat())
                close()
            },
            color = Color.Black,
            style = Stroke(width = (r/6.6).dp.toPx(), cap = StrokeCap.Round),
        )

        // Draw the main circle (body)
        drawCircle(
            color = color,
            radius = radiusPx,
            center = Offset(centerX, centerY),
        )

        // Draw the circle border
        drawCircle(
            color = Color.Black,
            radius = radiusPx,
            center = Offset(centerX, centerY),
            style = Stroke(width =(r/6.6).dp.toPx(), cap = StrokeCap.Round),
        )

        // Draw eyes
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX - eyeOffsetX, centerY - eyeOffsetY),
        )
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX + eyeOffsetX, centerY - eyeOffsetY),
        )

        // Draw pupils
        drawCircle(
            color = Color.Black,
            radius = eyeRadius / 2,
            center = Offset(centerX - eyeOffsetX, centerY - eyeOffsetY),
        )
        drawCircle(
            color = Color.Black,
            radius = eyeRadius / 2,
            center = Offset(centerX + eyeOffsetX, centerY - eyeOffsetY),
        )

        // Draw nose
        drawCircle(
            color = Color.Black,
            radius = noseRadius,
            center = Offset(centerX, centerY-radiusPx),
        )

    }
}


fun tomObstacleManager() {

    val tomRadius = 30.0
    val upperLimit = tomY - tomRadius - 100
    val lowerLimit = tomY

    val rangeObstacle = upperLimit..lowerLimit

    val obstacleGridOne = globalYOne.any { it in rangeObstacle }
    val obstacleGridTwo = globalYTwo.any { it in rangeObstacle }
    val obstacleGridThree = globalYThree.any { it in rangeObstacle }


        if (tomX == (space/2 + 50)) {
            if (obstacleGridOne) {
                tomX = (screenWidthDp.toDouble()/2)
            }
        }

        if (tomX == (screenWidthDp.toDouble() - space/2 - 50)) {
            if (obstacleGridThree) {
                tomX = (screenWidthDp.toDouble()/2)
            }
        }

        if (tomX == (screenWidthDp.toDouble()/2)) {
            if (obstacleGridTwo) {
                val indexInRangeTwo: Int = globalYTwo.withIndex()
                    .first { it.value in rangeObstacle }
                    .index

                tomX = if (indexInRangeTwo < 3) {
                    if (globalYOne[indexInRangeTwo + 1] > globalYThree[indexInRangeTwo + 1]) {
                        (screenWidthDp.toDouble() - space/2 - 50)
                    } else {
                        (space/2 + 50)
                    }
                } else {
                    if (globalYOne[indexInRangeTwo] > globalYThree[indexInRangeTwo]) {
                        (screenWidthDp.toDouble() - space/2 - 50)
                    } else {
                        (space/2 + 50)
                    }
                }

            }
        }

}
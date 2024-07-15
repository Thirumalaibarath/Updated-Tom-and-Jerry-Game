package com.example.tomandjerry

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

// Jerry

@Composable
fun Jerry(x: Double, y: Int, r: Int, color: Color) {

    val infiniteTransition = rememberInfiniteTransition(label = "jerryBlinker")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "jerryBlinker"
    )


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
            color = brownTwo,
            radius = radiusPx*0.5f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
            alpha = if(blink) alpha else 1f
        )

        drawCircle(
            color = Color.Black,
            radius = radiusPx*0.5f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
            style = Stroke(width = (r/6.6).dp.toPx(), cap = StrokeCap.Round),
            alpha = if(blink) alpha else 1f
        )

        drawCircle(
            color = brownTwo,
            radius = radiusPx*0.5f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
            alpha = if(blink) alpha else 1f
        )

        drawCircle(
            color = Color.Black,
            radius = radiusPx*0.5f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
            style = Stroke(width =(r/6.6).dp.toPx(), cap = StrokeCap.Round),
            alpha = if(blink) alpha else 1f
        )

        // inside Ear

        drawCircle(
            color = earColor,
            radius = radiusPx*0.3f,
            center = Offset(centerX-radiusPx/1.5f, centerY-radiusPx/1.5f),
            alpha = if(blink) alpha else 1f
        )

        drawCircle(
            color = earColor,
            radius = radiusPx*0.3f,
            center = Offset(centerX+radiusPx/1.5f, centerY-radiusPx/1.5f),
            alpha = if(blink) alpha else 1f
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
            color = brownTwo,
            alpha =if(blink) alpha else 1f
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
            alpha = if(blink) alpha else 1f
        )

        // Draw the main circle (body)
        drawCircle(
            color = color,
            radius = radiusPx,
            center = Offset(centerX, centerY),
            alpha = if(blink) alpha else 1f
        )

        // Draw the circle border
        drawCircle(
            color = Color.Black,
            radius = radiusPx,
            center = Offset(centerX, centerY),
            style = Stroke(width =(r/6.6).dp.toPx(), cap = StrokeCap.Round),
            alpha = if(blink) alpha else 1f
        )

        // Draw eyes
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX - eyeOffsetX, centerY - eyeOffsetY),
            alpha = if(blink) alpha else 1f
        )
        drawCircle(
            color = Color.White,
            radius = eyeRadius,
            center = Offset(centerX + eyeOffsetX, centerY - eyeOffsetY),
            alpha = if(blink) alpha else 1f
        )

        // Draw pupils
        drawCircle(
            color = Color.Black,
            radius = eyeRadius / 2,
            center = Offset(centerX - eyeOffsetX, centerY - eyeOffsetY),
            alpha = if(blink) alpha else 1f
        )
        drawCircle(
            color = Color.Black,
            radius = eyeRadius / 2,
            center = Offset(centerX + eyeOffsetX, centerY - eyeOffsetY),
            alpha =if(blink) alpha else 1f
        )

        // Draw nose
        drawCircle(
            color = Color.Black,
            radius = noseRadius,
            center = Offset(centerX, centerY-radiusPx),
            alpha = if(blink) alpha else 1f
        )

    }
}

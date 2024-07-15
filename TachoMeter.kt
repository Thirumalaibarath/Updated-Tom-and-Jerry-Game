package com.example.tomandjerry


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.abs


var lives  = mutableStateListOf(0,0,0)
var bullets  = mutableStateListOf(0,0,0,0)

@Composable
fun SpeedIncrease()
{

    if(distanceTraversed > 0 && distanceTraversed < 2000 && !blink )
    {
        speed = 4.0
    }
    else if(distanceTraversed > 2000 && distanceTraversed < 6000 && !blink )
    {
        speed = 4.5
    }
    else if(distanceTraversed > 6000 && distanceTraversed < 8000 && !blink )
    {
        speed = 5.0
    }
    else if(distanceTraversed > 8000 && distanceTraversed < 9000 && !blink )
    {
        speed = 5.5
    }
    else if(distanceTraversed > 9000  && !blink )
    {
        speed = 6.0
    }

    LaunchedEffect(gameOver) {
        while(!gameOver)
        {
            distanceTraversed += (speed * 0.1)
            delay(16L)
        }

    }


}

@Composable
fun Tachometer()
{
    // Tachometer
    val space = (screenWidthDp.toDouble() - (360)) /2
    Row(

        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(space.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Top
    )
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        )
        {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp)) // Rounded corners on the right end
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp) // Same shape as the background
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    val bulletCount = 4
                    for(i in 0..<bulletCount)
                    {
                        BulletIcon(bullets[i])
                    }

                }

            }

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp)) // Rounded corners on the right end
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp) // Same shape as the background
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    LineStar()
                    Text(
                        text = (abs(speed)-4).toString(),
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }

        }

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(80.dp)
                .background(Color.Black.copy(alpha = 0.5f))

                .border(
                    width = 4.dp,
                    color = Color.Black,
                )
            ,
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = (laneTwo.toInt()).toString(),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.End
        )
        {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(140.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp)) // Rounded corners on the left end
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp) // Same shape as the background
                    ),
                contentAlignment = Alignment.Center
            )
            {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    val heartCount = 3
                    for(i in 0..<heartCount)
                    {
                        LineHeart(lives[i])
                    }

                }
            }


            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp)) // Rounded corners on the left end
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp) // Same shape as the background
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(), // Fill the available size
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CheeseIcon()
                    Text(
                        text = cheeseCount.toString(),
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }
            }

        }
    }

}

@Composable
fun PauseButton() {
    Canvas(modifier = Modifier.size(22.dp)) {
        val width = size.width
        val height = size.height
        val strokeWidth = 4f

        // Draw left vertical line
        drawLine(
            color = Color.Black,
            start = Offset(width * 0.3f, height * 0.2f),
            end = Offset(width * 0.3f, height * 0.8f),
            strokeWidth = strokeWidth
        )

        // Draw right vertical line
        drawLine(
            color = Color.Black,
            start = Offset(width * 0.7f, height * 0.2f),
            end = Offset(width * 0.7f, height * 0.8f),
            strokeWidth = strokeWidth
        )
    }
}




//Text(text = "POINT",color = Color.White , fontSize = 25.sp, fontWeight = FontWeight.Bold  )
//Text(text = autoLaneDistance[1].toInt().toString(),color = Color.White , fontSize = 25.sp, fontWeight = FontWeight.Bold  )
//Box(modifier = Modifier
//.height(25.dp)
//.width(10.dp)
//.background(color = Color.Gray.copy(alpha = 0.5f))
//.border(width = 2.dp, color = Color.Black)
//)
//Text(text = bulletCount.toString(),color = Color.White , fontSize = 25.sp, fontWeight = FontWeight.Bold  )

@Composable
fun CheeseIcon() {
    val squareSize = 22.dp

    Box(
        modifier = Modifier
            .size(squareSize)
            .background(Color.Transparent),  // Make sure the background is transparent
        contentAlignment = Alignment.Center  // Align the canvas at the center
    ) {
        if (squareSize != 0.dp) {
            Canvas(modifier = Modifier.size(squareSize)) {
                val actualSize = size.width  // Assuming canvas is square based on size.width

                // Draw the cheese square
                drawRect(
                    color = Color(0xFFFED259), // Yellow color for cheese
                    size = Size(actualSize, actualSize)
                )
                drawRect(
                    color = Color.Black,
                    size = Size(actualSize, actualSize),
                    style = Stroke(width = 10f )
                )

                // Draw cheese holes
                val holeRadius = actualSize / 10
                val holePositions = listOf(
                    Offset(actualSize * 0.25f, actualSize * 0.25f),
                    Offset(actualSize * 0.75f, actualSize * 0.25f),
                    Offset(actualSize * 0.5f, actualSize * 0.5f),
                    Offset(actualSize * 0.25f, actualSize * 0.75f),
                    Offset(actualSize * 0.75f, actualSize * 0.75f)
                )

                holePositions.forEach { position ->
                    drawCircle(
                        color = Color(0xFF6F3327), // Light gray color for holes
                        radius = holeRadius,
                        center = position
                    )
                }
            }
        }
    }
}

@Composable
fun BulletIcon(icon:Int) {

    val radius = 22
    Canvas(modifier = Modifier.size(( radius).dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2

        if(icon == 0)
        {
            drawCircle(
                color = Color(0xFFFFA500),
                radius = radius.toFloat(),
                center = Offset(centerX, centerY)
            )
        }


        drawCircle(
            color = Color.Black,
            radius = radius.toFloat(),
            center = Offset(centerX, centerY),
            style = Stroke(width = 9f, cap = StrokeCap.Round)
        )
    }
}


@Composable
fun LineHeart(live:Int) {
    Canvas(modifier = Modifier.size(25.dp)) {
        val width = size.width
        val height = size.height

        // Define the points for the heart shape
        val points = listOf(
            Offset(width * 0.5f, height * 0.2f),
            Offset(width * 0.3f, height * 0.05f),
            Offset(width * 0.1f, height * 0.2f),
            Offset(width * 0.5f, height * 0.8f),
            Offset(width * 0.9f, height * 0.2f),
            Offset(width * 0.7f, height * 0.05f),
            Offset(width * 0.5f, height * 0.2f)
        )


        // Create a path for the heart shape
        val path = Path().apply {
            moveTo(points[0].x, points[0].y)
            for (i in 1 until points.size) {
                lineTo(points[i].x, points[i].y)
            }
            close()
        }

        // Fill the heart shape with red color
        if(live == 0)
        {
            drawPath(
                path = path,
                color = Color.Red,
                style = Fill
            )
        }

        // Draw the heart shape using lines
        for (i in points.indices) {
            if (i < points.size - 1) {
                drawLine(
                    color = Color.Black,
                    start = points[i],
                    end = points[i + 1],
                    strokeWidth = 8f
                )
            }
        }
    }
}

@Composable
fun LineBullet() {
    Box(
    modifier = Modifier
        .size(width = 40.dp, height = 80.dp)
        .background(color = Color.Gray, shape = CircleShape) // This creates the semi-circle on top
        .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) // Clip to rounded corners at the top
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Draw the rectangle part of the bullet
        drawRect(
            color = Color.Gray,
            size = Size(width = width, height = height)
        )
    }
}
}

@Composable
fun LineStar() {
    Canvas(modifier = Modifier.size(22.dp)) {
        val width = size.width
        val height = size.height

        // Define the points for the star shape
        val points = listOf(
            Offset(width * 0.5f, height * 0.0f), // Top point
            Offset(width * 0.62f, height * 0.35f), // Upper right
            Offset(width * 1.0f, height * 0.35f), // Far right
            Offset(width * 0.69f, height * 0.57f), // Lower right
            Offset(width * 0.81f, height * 1.0f), // Bottom right
            Offset(width * 0.5f, height * 0.75f), // Bottom middle
            Offset(width * 0.19f, height * 1.0f), // Bottom left
            Offset(width * 0.31f, height * 0.57f), // Lower left
            Offset(width * 0.0f, height * 0.35f), // Far left
            Offset(width * 0.38f, height * 0.35f), // Upper left
            Offset(width * 0.5f, height * 0.0f) // Back to top point
        )

        // Create a path for the star shape
        val path = Path().apply {
            moveTo(points[0].x, points[0].y)
            for (i in 1 until points.size) {
                lineTo(points[i].x, points[i].y)
            }
            close()
        }

        // Fill the star shape with yellow color
        drawPath(
            path = path,
            color = Color.Yellow,
            style = Fill
        )

        // Draw the star shape using lines
        for (i in points.indices) {
            if (i < points.size - 1) {
                drawLine(
                    color = Color.Black,
                    start = points[i],
                    end = points[i + 1],
                    strokeWidth = 8f
                )
            }
        }
    }
}
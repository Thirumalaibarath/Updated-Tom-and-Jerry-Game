package com.example.tomandjerry

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate

@Composable
fun CheeseMaker()
{
    if(!cheeseCall[0])
    {
        cheesePos[0] = rand((-100..100).toList())
        cheeseCall[0] = true
    }

    if(!cheeseCall[1])
    {
        cheesePos[1] = rand((-100..100).toList())
        cheeseCall[1] = true
    }

    if(!cheeseCall[2])
    {
        cheesePos[2]  = rand((-100..100).toList())
        cheeseCall[2] = true
    }

    if(!cheeseCall[3])
    {
        cheesePos[3]  = rand((-100..100).toList())
        cheeseCall[3] = true
    }

    if(!cheeseCall[4])
    {
        cheesePos[4]  = rand((-100..100).toList())
        cheeseCall[4] = true
    }

    if(!cheeseCall[5])
    {
        cheesePos[5]  = rand((-100..100).toList())
        cheeseCall[5] = true
    }

// first set of cheese column - 2
    if((globalYTwo[0] > cheesePos[0] && globalYTwo[0] < cheesePos[0]+10)|| cheeseState[0])
    {

        LaunchedEffect(cheese[0]) {
            while(cheese[0] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[0] += speed
                }
                delay(16L)
            }

        }

        cheeseState[0] = true

    }

    if((globalYTwo[2] > cheesePos[1] && globalYTwo[2] < cheesePos[1]+10)|| cheeseState[1])
    {

        LaunchedEffect(cheese[1]) {
            while(cheese[1] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[1] += speed
                }
                delay(16L)
            }

        }

        cheeseState[1] = true

    }

    if((globalYTwo[1] > cheesePos[2] && globalYTwo[1] < cheesePos[2]+10)|| cheeseState[2])
    {

        LaunchedEffect(cheese[2]) {
            while(cheese[2] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[2] += speed
                }
                delay(16L)
            }

        }

        cheeseState[2] = true

    }

    if((globalYTwo[3] > cheesePos[3] && globalYTwo[3] < cheesePos[3]+10)|| cheeseState[3])
    {

        LaunchedEffect(cheese[3]) {
            while(cheese[3] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[3] += speed
                }
                delay(16L)
            }

        }

        cheeseState[3] = true

    }

    if((globalYTwo[1] > cheesePos[4] && globalYTwo[0] < cheesePos[4]+10)|| cheeseState[4])
    {

        LaunchedEffect(cheese[4]) {
            while(cheese[4] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[4] += speed
                }
                delay(16L)
            }

        }

        cheeseState[4] = true

    }

    if((globalYTwo[2] > cheesePos[5] && globalYTwo[2] < cheesePos[5]+10)|| cheeseState[5])
    {

        LaunchedEffect(cheese[5]) {
            while(cheese[5] <= (screenHeightDp).toInt() + 50) {
                withFrameNanos {
                    cheese[5] += speed
                }
                delay(16L)
            }

        }

        cheeseState[5] = true

    }


    if(cheese[0] >= (screenHeightDp).toInt() + 50)
    {
        cheese[0] = -100.0
        cheeseState[0] = false
        caught[0] = false
        cheeseCall[0] = false
        overlap[0] = false
        high[0] = false
    }

    if(cheese[1] >=(screenHeightDp).toInt() + 50)
    {
        cheese[1] = -100.0
        cheeseState[1] = false
        caught[1] = false
        cheeseCall[1] = false
        overlap[1] = false
        high[1] = false

    }

    if(cheese[2] >= (screenHeightDp).toInt() + 50)
    {
        cheese[2] = -100.0
        cheeseState[2] = false
        caught[2] = false
        cheeseCall[2] = false
        overlap[2] = false
        high[2] = false

    }

    if(cheese[3] >= (screenHeightDp).toInt() + 50)
    {
        cheese[3] = -100.0
        cheeseState[3] = false
        caught[3] = false
        cheeseCall[3] = false
        overlap[3] = false
        high[3] = false

    }

    if(cheese[4] >= (screenHeightDp).toInt() + 50)
    {
        cheese[4] = -100.0
        cheeseState[4] = false
        caught[4] = false
        cheeseCall[4] = false
        overlap[4] = false
        high[4] = false

    }

    if(cheese[5] >= (screenHeightDp).toInt() + 50)
    {
        cheese[5] = -100.0
        cheeseState[5] = false
        caught[5] = false
        cheeseCall[5] = false
        overlap[5] = false
        high[5] = false

    }

}


@Composable
fun CheeseChecker() {
    val jerryHead = screenHeightDp.toDouble() - 200

    val range = 0.0..jerryHead

    if (cheese[2] in range || cheese[3] in range) {
        cheeseRange = cheese.withIndex()
            .first { it.value in range }
            .index
    }

    if (cheese[0] in range || cheese[1] in range) {
        cheeseRangeTwo = cheese.withIndex()
            .first { it.value in range }
            .index
    }

    if (cheese[4] in range || cheese[5] in range) {
        cheeseRangeThree = cheese.withIndex()
            .first { it.value in range }
            .index
    }

    // overlap - 1
    for (j in globalYOne)
    {
        // Second Cheese
        for(k in cheese[2].toInt()+30 downTo cheese[2].toInt() step 1)
        {
            if (k.toDouble() in j-10..(j + 55 +5) && itemOne[globalYOne.indexOf(j)] == 0 && (cheese[2]+30) > 0 )
            {
                high[2] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 35 +5)&& itemOne[globalYOne.indexOf(j)] == 1 && (cheese[2]+30) > 0 )
            {
                high[2] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 45 + 5) && itemOne[globalYOne.indexOf(j)] == 2 && (cheese[2]+30) > 0 )
            {
                high[2] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }
        }

        // First Cheese
        for(l in cheese[3].toInt()+30 downTo cheese[3].toInt() step 1)
        {
            if (l.toDouble() in j-10..(j + 55 +5) && itemOne[globalYOne.indexOf(j)] == 0 && (cheese[3]+30) > 0 )
            {
                high[3] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }

            if (l.toDouble() in j-10..(j + 35 +5) && itemOne[globalYOne.indexOf(j)] == 1 && (cheese[3]+30) > 0 )
            {
                high[3] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }

            if (l.toDouble() in j-10..(j + 45 +5) && itemOne[globalYOne.indexOf(j)] == 2 && (cheese[3]+30) > 0 )
            {
                high[3] = !bulletHitLaneOne[globalYOne.indexOf(j)]
            }
        }

    }


    for(j in globalYTwo)
    {
        // Second Cheese
        for(k in cheese[1].toInt()+30 downTo cheese[1].toInt() step 1)
        {
            if (k.toDouble() in j-10..(j + 55 +5) && itemTwo[globalYTwo.indexOf(j)] == 0 && (cheese[1]+30) > 0 )
            {
                high[1] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 35 + 5) && itemTwo[globalYTwo.indexOf(j)] == 1 && (cheese[1]+30) > 0 )
            {
                high[1] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 45 +5) && itemTwo[globalYTwo.indexOf(j)] == 2 && (cheese[1]+30) > 0 )
            {
                high[1] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }

        }

        // First Cheese
        for(l in cheese[0].toInt()+30 downTo cheese[0].toInt() step 1)
        {
            if (l.toDouble() in j-10..(j + 55 + 5) && itemTwo[globalYTwo.indexOf(j)] == 0 && (cheese[0]+30) > 0 )
            {
                high[0] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }

            if (l.toDouble() in j-10..(j + 35 + 5) && itemTwo[globalYTwo.indexOf(j)] == 1 && (cheese[0]+30) > 0 )
            {
                high[0] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }

            if (l.toDouble() in j-10..(j + 45 + 5) && itemTwo[globalYTwo.indexOf(j)] == 2 && (cheese[0]+30) > 0 )
            {
                high[0] = !bulletHitLaneTwo[globalYTwo.indexOf(j)]
            }
        }

    }

    // overlap - 3

    for (j in globalYThree)
    {
        for(k in cheese[4].toInt()+30 downTo cheese[4].toInt() step 1)
        {
            if (k.toDouble() in j-10..(j + 55 + 5)&& itemThree[globalYThree.indexOf(j)] == 0 && (cheese[4]+30) > 0 )
            {
                high[4] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 35 + 5) && itemThree[globalYThree.indexOf(j)] == 1 && (cheese[4]+30) > 0 )
            {
                high[4] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }

            if (k.toDouble() in j-10..(j + 45 + 5) && itemThree[globalYThree.indexOf(j)] == 2 && (cheese[4]+30) > 0 )
            {
                high[4] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }
        }

        // First Cheese
        for(l in cheese[5].toInt()+30 downTo cheese[5].toInt() step 1)
        {
            if (l.toDouble() in j..(j + 55 + 5) && itemThree[globalYThree.indexOf(j)] == 0 && (cheese[5]+30) > 0 )
            {
                high[5] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }

            if (l.toDouble() in j..(j + 35 + 5) && itemThree[globalYThree.indexOf(j)] == 1 && (cheese[5]+30) > 0 )
            {
                high[5] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }

            if (l.toDouble() in j..(j + 45 + 5) && itemThree[globalYThree.indexOf(j)] == 2 && (cheese[5]+30) > 0 )
            {
                high[5] = !bulletHitLaneThree[globalYThree.indexOf(j)]
            }
        }

    }

    if (high[2] || high[3]) {
        cheesePost[1] = space / 2 + 50 + (45 / 2) + jerrySize
    }
    else {
        cheesePost[1] = space / 2 + 50 + 15 + jerrySize
    }

    if (high[0] || high[1]) {
        cheesePost[2] = screenWidthDp.toDouble() / 2 - (45 / 2) - jerrySize
        cheesePost[3] = screenWidthDp.toDouble() / 2 + (45 / 2) + jerrySize
    }
    else {
        cheesePost[2] = screenWidthDp.toDouble() / 2 - 15 - jerrySize
        cheesePost[3] = screenWidthDp.toDouble() / 2 + 15 + jerrySize
    }

    if (high[4] || high[5]) {
        cheesePost[4] = screenWidthDp.toDouble() - space / 2 - 50 - (45 / 2) - jerrySize
        cheesePost[5] = screenWidthDp.toDouble() - space / 2 - 50
    }
    else {
        cheesePost[4] = screenWidthDp.toDouble() - space / 2 - 50 - (15) - jerrySize
        cheesePost[5] = screenWidthDp.toDouble() - space / 2 - 50
    }

    // cheese Capture

    if(!blink) {
        if (jerryX in cheesePost[0]..cheesePost[1]) {
            if ((((cheese[2] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0) || (((cheese[2]) - (screenHeightDp.toDouble() - 220)) in -5.0..0.0) ) {
                if (!high[2] && !jerryOnAir && !caught[2]) {
                    caught[2] = true
                    cheeseCount += 1
                }

            }

            if ((((cheese[2] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[2] && jerryOnAir && !caught[2] ) {
                    caught[2] = true
                    cheeseCount += 1
                }

                if(high[2] && !caught[2] && jerrySize == 25)
                {
                    caught[2] = true
                    cheeseCount += 1
                }
            }

            // Two
            if ((((cheese[3] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0) || (((cheese[3]) - (screenHeightDp.toDouble() - 220)) in -5.0..0.0)) {
                if (!high[3] && !jerryOnAir && !caught[3] ) {
                    caught[3] = true
                    cheeseCount += 1
                }
            }

            if ((((cheese[3] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[3] && jerryOnAir && !caught[3] ) {
                    caught[3] = true
                    cheeseCount += 1
                }
                if(high[3] && !caught[3] && jerrySize == 25)
                {
                    caught[3] = true
                    cheeseCount += 1
                }
            }

            // 1

            if (!high[2] )
            {
                for(k in cheese[2].toInt()..cheese[2].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!high[2] && !jerryOnAir && !caught[2] && jerrySize == 25 ) {
                            caught[2] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[2] )
            {
                for(k in cheese[2].toInt()..cheese[2].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[2]) {
                            caught[2] = true
                            cheeseCount += 1
                        }
                        if(!caught[2] && jerrySize == 25)
                        {
                            caught[2] = true
                            cheeseCount += 1
                        }
                    }
                }
            }

            // 3

            if (!high[3] )
            {
                for(k in cheese[3].toInt()..cheese[3].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!jerryOnAir && !caught[3]) {
                            caught[3] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[3])
            {
                for(k in cheese[3].toInt()..cheese[3].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[3]) {
                            caught[3] = true
                            cheeseCount += 1
                        }
                        if(jerrySize == 25 && !caught[3])
                        {
                            caught[3] = true
                            cheeseCount += 1
                        }
                    }
                }
            }

        }


        if (jerryX in cheesePost[2]..cheesePost[3]) {
            // one
            if ((((cheese[0] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0) || (((cheese[0]) - (screenHeightDp.toDouble() - 220)) in -5.0..0.0)) {
                if (!high[0] && !jerryOnAir && !caught[0] ) {
                    caught[0] = true
                    cheeseCount += 1
                }
            }
            if ((((cheese[0] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[0] && jerryOnAir && !caught[0]) {
                    caught[0] = true
                    cheeseCount += 1
                }
                if(high[0] && !caught[0] && jerrySize == 25)
                {
                    caught[0] = true
                    cheeseCount += 1
                }
            }
            // Two
            if ((((cheese[1] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0) || (((cheese[1]) - (screenHeightDp.toDouble() - 220)) in -5.0..0.0)) {
                if (!high[1] && !jerryOnAir && !caught[1]) {
                    caught[1] = true
                    cheeseCount += 1
                }
            }
            if ((((cheese[1] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[1] && jerryOnAir && !caught[1]) {
                    caught[1] = true
                    cheeseCount += 1
                }
                if(high[1] && !caught[1] && jerrySize == 25)
                {
                    caught[1] = true
                    cheeseCount += 1
                }
            }

            // 1

            if (!high[0] )
            {
                for(k in cheese[0].toInt()..cheese[0].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!jerryOnAir && !caught[0]) {
                            caught[0] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[0])
            {
                for(k in cheese[0].toInt()..cheese[0].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[0]) {
                            caught[0] = true
                            cheeseCount += 1
                        }
                        if(jerrySize == 25 && !caught[0])
                        {
                            caught[0] = true
                            cheeseCount += 1
                        }
                    }
                }
            }
            //2
            if (!high[1] )
            {
                for(k in cheese[1].toInt()..cheese[1].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!jerryOnAir && !caught[1]) {
                            caught[1] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[1])
            {
                for(k in cheese[1].toInt()..cheese[1].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[1]) {
                            caught[1] = true
                            cheeseCount += 1
                        }
                        if(jerrySize == 25 && !caught[1])
                        {
                            caught[1] = true
                            cheeseCount += 1
                        }
                    }
                }
            }



        }


        if (jerryX in cheesePost[4]..cheesePost[5]) {
            if ((((cheese[4] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0) || (((cheese[4]) - (screenHeightDp.toDouble() - 220)) in -5.0..0.0)) {
                if (!high[4] && !jerryOnAir && !caught[4] ) {
                    caught[4] = true
                    cheeseCount += 1
                }
            }

            if ((((cheese[4] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[4] && jerryOnAir && !caught[4]) {
                    caught[4] = true
                    cheeseCount += 1
                }
                if(high[4] && !caught[4] && jerrySize == 25)
                {
                    caught[4] = true
                    cheeseCount += 1
                }
            }

            // Two

            if ((((cheese[5] + 30) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (!high[5] && !jerryOnAir && !caught[5] && jerrySize == 25 ) {
                    caught[5] = true
                    cheeseCount += 1
                }
            }

            if ((((cheese[5] + 45) - (screenHeightDp.toDouble() - 220)) in -5.0..5.0)) {
                if (high[5] && jerryOnAir && !caught[5]) {
                    caught[5] = true
                    cheeseCount += 1
                }
                if(high[5] && !caught[5] && jerrySize == 25)
                {
                    caught[5] = true
                    cheeseCount += 1
                }
            }

            // 1

            if (!high[4] )
            {
                for(k in cheese[4].toInt()..cheese[4].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!jerryOnAir && !caught[4]) {
                            caught[4] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[4])
            {
                for(k in cheese[4].toInt()..cheese[4].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[4]) {
                            caught[4] = true
                            cheeseCount += 1
                        }
                        if(jerrySize == 25 && !caught[4])
                        {
                            caught[4] = true
                            cheeseCount += 1
                        }
                    }
                }
            }

            //2

            if (!high[5] )
            {
                for(k in cheese[5].toInt()..cheese[5].toInt()+30)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (!jerryOnAir && !caught[5]) {
                            caught[5] = true
                            cheeseCount += 1
                        }
                    }

                }
            }

            if (high[5])
            {
                for(k in cheese[5].toInt()..cheese[5].toInt()+45)
                {
                    if(k.toDouble() in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) )
                    {
                        if (jerryOnAir && !caught[5]) {
                            caught[5] = true
                            cheeseCount += 1
                        }
                        if(jerrySize == 25 && !caught[5])
                        {
                            caught[5] = true
                            cheeseCount += 1
                        }
                    }
                }
            }


        }
    }

}



@Composable
fun Cheese(offsetY: Double, caught: Boolean, high: Boolean) {
    val square = if (!caught) if (!high) 30f else 45f else 0f


    val infiniteTransition = rememberInfiniteTransition(label = "Cheese")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Cheese"
    )

    val offsetX = 0
    Canvas(
        modifier = Modifier
            .size(square.dp)  // Ensure canvas size matches the desired size
            .offset(offsetX.dp, offsetY.dp)
    ) {
        val squareSize = size.width  // Assuming canvas is square based on size.width
        val strokeWidth = squareSize / 10  // Proportional stroke width

        rotate(degrees = rotationAngle, pivot = Offset(squareSize / 2, squareSize / 2)) {
            drawRect(
                color = Color(0xFFFED259), // Yellow color for cheese
                size = Size(squareSize, squareSize)
            )
            drawRect(
                color = Color.Black,
                size = Size(squareSize, squareSize),
                style = Stroke(width = strokeWidth)
            )

            // Draw cheese holes
            val holeRadius = squareSize / 10
            val holePositions = listOf(
                Offset(squareSize * 0.25f, squareSize * 0.25f),
                Offset(squareSize * 0.75f, squareSize * 0.25f),
                Offset(squareSize * 0.5f, squareSize * 0.5f),
                Offset(squareSize * 0.25f, squareSize * 0.75f),
                Offset(squareSize * 0.75f, squareSize * 0.75f)
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

package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

var trapLaneOne  by mutableIntStateOf(0)
var trapLaneTwo  by mutableIntStateOf(0)
var trapLaneThree  by mutableIntStateOf(0)

var trapCollision by mutableStateOf(false)


fun trap()
{

    if((distanceTraversed.toInt() % 100) == 0 && !trap)
    {
        trap = true
    }
}

fun trapLane():Int
{
    var trapLane by mutableIntStateOf(0)

    trapLane = rand(listOf(-1,0,1))

    return trapLane
}

@Composable
fun OnTrap()
{
//    DrawVerticalLine(x = space.toInt()/2+80)
//    DrawVerticalLine(x = space.toInt()/2+90)
//
//    DrawVerticalLine(x = space.toInt()+space.toInt()/2+110)
//    DrawVerticalLine(x = space.toInt()+space.toInt()/2+120)
//
//    DrawVerticalLine(x = space.toInt()+space.toInt()/2+110+70)
//    DrawVerticalLine(x = space.toInt()+space.toInt()/2+110+80)
//
//    DrawVerticalLine(x = space.toInt()*2+space.toInt()/2+215)
//    DrawVerticalLine(x = space.toInt()*2+space.toInt()/2+225)
//
//    DrawVerticalLine(x = 2*100+space.toInt()*2)

    val upperLimit = screenHeightDp.toDouble() - 300
    val lowerLimit = screenHeightDp.toDouble() - 100
    val range = upperLimit..lowerLimit

    if(globalYOne.any { it in (upperLimit + 1)..lowerLimit }  )
    {
        trapLaneOne = globalYOne.withIndex()
            .first { it.value in range }
            .index
    }


    if(globalYTwo.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        trapLaneTwo = globalYTwo.withIndex()
            .first { it.value in range }
            .index

    }


    if(globalYThree.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        trapLaneThree = globalYThree.withIndex()
            .first { it.value in range }
            .index
    }


    for(i in globalYOne)
    {
        if( i in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+55 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+27 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180)  )
        {
            if(jerryX in  (space/2+50)..(space/2+100) && !jerryOnAir && itemOne[trapLaneOne] == 2  )
            {
                blink = true
                autoJump = true
                airControl = false
                trapCollision = true
//                gameOver = true
            }
        }

    }

    for(i in globalYTwo)
    {
        if(i in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+55 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+27 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180)  )
        {
            if(jerryX in ((space+100+space/2))..(space+200+space/2) && !jerryOnAir && itemTwo[trapLaneTwo] == 2  )
            {
                blink = true
                autoJump = true
                airControl = false
                trapCollision = true
//                gameOver = true
            }
        }

    }

    for(i in globalYThree)
    {
        if(i in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+55 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180) ||  i+27 in (screenHeightDp.toDouble() - 220)..(screenHeightDp.toDouble() - 180)   )
        {
            if(jerryX in (2*space+200+space/2)..(screenWidthDp.toDouble() - space/2 ) && !jerryOnAir && itemThree[trapLaneThree] == 2  )
            {
                blink = true
                autoJump = true
                airControl = false
                trapCollision = true
//                gameOver = true
            }
        }

    }


    if(trapCollision)
    {
        LaunchedEffect(blink) {
            delay(1500)
            blink = false
            trapCollision = false
        }
    }


}
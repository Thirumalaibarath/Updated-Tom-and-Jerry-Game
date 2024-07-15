package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay


var autoJump by mutableStateOf(false)

@Composable
fun AutoJump()
{
    val jerryRadius = 20.0
    val jerryYCoord = screenHeightDp.toDouble() - 200
    val upperLimit = jerryYCoord - jerryRadius - 100
    val lowerLimit = jerryYCoord - jerryRadius

    val rangeObstacle = upperLimit..lowerLimit

    val obstacleGridOne = globalYOne.any { it+54 in rangeObstacle }
    val obstacleGridTwo = globalYTwo.any { it+54 in rangeObstacle }
    val obstacleGridThree = globalYThree.any { it+54 in rangeObstacle }


    if(autoJump)
    {
        if(jerryX in (space/2 + 50)..(space/2 + 78) && obstacleGridOne )
        {
            jerryOnAir = true
            jumpUp = true
        }


        if( jerryX in (screenWidthDp.toDouble()/2  - 28)..screenWidthDp.toDouble()/2  + 28 &&  obstacleGridTwo )
        {
            jerryOnAir = true
            jumpUp = true
        }

        if(jerryX in (screenWidthDp.toDouble() - space/2 - 68)..(screenWidthDp.toDouble() - space/2) && obstacleGridThree )
        {
            jerryOnAir = true
            jumpUp = true
        }
    }


    if(autoJump)
    {
        LaunchedEffect(autoJump)
        {
            delay(15000)
            autoJump = false
            airControl = true
        }
    }


}

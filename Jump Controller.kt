package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

var laneOne by mutableDoubleStateOf(0.0)
var laneTwo by mutableDoubleStateOf(0.0)
var laneThree by mutableDoubleStateOf(0.0)
var airLock = mutableStateListOf(false,false,false)


var laneOneCrateNumber  by mutableIntStateOf(0)
var laneTwoCrateNumber  by mutableIntStateOf(0)
var laneThreeCrateNumber  by mutableIntStateOf(0)

var jumpLaneControl  = mutableStateListOf(false,false,false)
var fanNumber = mutableStateListOf(0,0,0)
var fanLock = mutableStateListOf(false,false,false)



@Composable
fun LaneTracker()
{
    val jerryLeft = jerryX.toInt()-20
    val jerryRight =  jerryX.toInt() + 20


    for(i in jerryLeft..jerryRight )
    {
        if(i.toDouble() in post[0]..post[2] && !fanLock[0])
        {
            laneTwo = 0.0
            laneThree = 0.0
            LaunchedEffect(gameOver) {
                while(!gameOver)
                {
                    laneOne += (speed * 0.01)
                    delay(16L)
                }

            }
        }
        else if(i.toDouble() in post[3]..post[6] && !fanLock[1])
        {
            laneOne = 0.0
            laneThree = 0.0
            LaunchedEffect(gameOver) {
                while(!gameOver)
                {
                    laneTwo += (speed * 0.01)
                    delay(16L)
                }

            }
        }

        else if(i.toDouble() in post[7]..post[9] && !fanLock[2])
        {
            laneTwo = 0.0
            laneOne = 0.0
            LaunchedEffect(gameOver) {
                while(!gameOver)
                {
                    laneThree += (speed * 0.01)
                    delay(16L)
                }

            }
        }
        else
        {
            laneTwo = 0.0
            laneOne = 0.0
            laneThree = 0.0
        }
    }







}

fun fanSetter()
{
    val upperLimit = 0.0
    val lowerLimit =  screenHeightDp.toDouble()/2
    val range = upperLimit..lowerLimit

    if(globalYOne.any { it in (upperLimit + 1)..lowerLimit }  )
    {
        laneOneCrateNumber = globalYOne.withIndex()
            .first { it.value in range }
            .index
    }

    if(globalYTwo.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        laneTwoCrateNumber = globalYTwo.withIndex()
            .first { it.value in range }
            .index

    }


    if(globalYThree.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        laneThreeCrateNumber = globalYThree.withIndex()
            .first { it.value in range }
            .index
    }

    if(laneOne>300)
    {
        fanLock[0] = true
        if(laneOneCrateNumber != 3)
        {
            itemOne[laneOneCrateNumber+1] = 3
            laneOne = 0.0
        }
        else
        {
            itemOne[3] = 0
            laneOne = 0.0
        }
    }

    if(laneTwo>300)
    {
        fanLock[1] = true
        if(laneTwoCrateNumber != 3)
        {
            itemTwo[laneTwoCrateNumber+1] = 3
            laneTwo = 0.0
        }
        else
        {
            itemTwo[3] = 0
            laneTwo = 0.0
        }
    }

    if(laneThree>300)
    {
        fanLock[2] = true
        if(laneThreeCrateNumber != 3)
        {
            itemThree[laneThreeCrateNumber+1] = 3
            laneThree = 0.0
        }
        else
        {
            itemThree[3] = 0
            laneThree = 0.0
        }
    }

    if(laneThreeCrateNumber != 3)
    {
        if(globalYThree[laneThreeCrateNumber+1] > (screenHeightDp).toInt() + 50 && !fanLock[2])
        {
            fanLock[2] = false
        }
    }
    else if(globalYThree[3] > (screenHeightDp).toInt() + 50 && !fanLock[2])
    {
        fanLock[2] = false
    }


    if(laneTwoCrateNumber != 3)
    {
        if(globalYTwo[laneTwoCrateNumber+1] > (screenHeightDp).toInt() + 50 && !fanLock[1])
        {
            fanLock[1] = false
        }

    }
    else if(globalYTwo[3] > (screenHeightDp).toInt() + 50 && !fanLock[1])
    {
        fanLock[1] = false
    }

    if(laneOneCrateNumber != 3)
    {
        if(globalYOne[laneOneCrateNumber+1] > (screenHeightDp).toInt() + 50 && !fanLock[0])
        {
            fanLock[0] = false
        }
    }
    else if(globalYOne[3] > (screenHeightDp).toInt() + 50 && !fanLock[0])
    {
        fanLock[0] = false
    }





}
fun onJump() {
    val jerryBack = screenHeightDp.toDouble() - 180

    val upperLimit = 0.0
    val range = upperLimit..jerryBack

    jumpLaneControl[0] = globalYOne.any { it in range }
    jumpLaneControl[1] = globalYTwo.any { it in range }
    jumpLaneControl[2] = globalYThree.any { it in range }


    if (globalYOne.any { it in (upperLimit)..jerryBack } && !airLock[0]) {
        fanNumber[0] = globalYOne.withIndex()
            .first { it.value in range }
            .index
    }


    if (globalYTwo.any { it in (upperLimit)..jerryBack } && !airLock[1]) {
        fanNumber[1] = globalYTwo.withIndex()
            .first { it.value in range }
            .index

    }


    if (globalYThree.any { it in (upperLimit)..jerryBack } && !airLock[2]) {
        fanNumber[2] = globalYThree.withIndex()
            .first { it.value in range }
            .index
    }

    //   Lane - 1

    if ((jerryX in (space / 2 + 50)..(space / 2 + 100))) {
        if (jumpLaneControl[0] && itemOne[fanNumber[0]] == 3) {
            airControl = false
            airLock[0] = true
        }

        if (globalYOne[fanNumber[0]] > screenHeightDp.toInt() - 150 && airLock[0]) {
            airControl = true
            airLock[0] = false
        }

    }

    //   Lane - 2
    if ((jerryX in (space + 100 + space / 2)..(space + 200 + space / 2))) {
        if (jumpLaneControl[1] && itemTwo[fanNumber[1]] == 3) {
            airControl = false
            airLock[1] = true
        }


        if (globalYTwo[fanNumber[1]] > screenHeightDp.toInt() - 150 && airLock[1]) {
            airControl = true
            airLock[1] = false
        }

    }

    //   Lane - 3
    if ((jerryX in (2 * space + 200 + space / 2)..(screenWidthDp.toDouble() - space / 2))) {
        if (jumpLaneControl[2] && itemThree[fanNumber[2]] == 3) {
            airControl = false
            airLock[2] = true
        }

        if (globalYThree[fanNumber[2]] > screenHeightDp.toInt() - 150 && airLock[2]) {
            airControl = true
            airLock[2] = false
        }

    }

}
package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import kotlinx.coroutines.delay

@Composable
fun Parallax()
{
    val limit = (-screenHeightDp.toInt()/4)
    val possibility = ( limit-30..limit+30 step 1).toMutableList()

    for(i in 0..4)
    {
        if(!called[i]) {
            activeState[i] = rand(possibility)

            if(activeState[i] in (limit-30)..(limit-20) step 1)
            {
                activeState[i+5] = rand((limit+20..limit+30).toList())
            }
            else if(activeState[i] in (limit-20)..(limit-10) step 1)
            {
                activeState[i+5] = rand((limit+10..limit+20).toList())
            }
            // middle half
            else if(activeState[i] in (limit-10)..(limit+10) step 1)
            {
                activeState[i+5] = rand((limit-10..limit+10).toList())
            }
            else if(activeState[i] in (limit+10)..(limit+20) step 1)
            {
                activeState[i+5] = rand((limit-20..limit-10).toList())
            }
            else if(activeState[i] in (limit+20)..(limit+30) step 1)
            {
                activeState[i+5] = rand((limit-30..limit-20).toList())
            }


            called[i] = true
        }

    }

    // 10..20

//    if(globalYTwo[0] in (-screenHeightDp.toInt()/4)-10..(-screenHeightDp.toInt()/4)+10 || stateOne[0])
//    {
//        LaunchedEffect(globalYOne[0]) {
//            while(globalYOne[0] <= (screenHeightDp).toInt() + 50) {
//                withFrameNanos {
//                    globalYOne[0] += speed
//                }
//                delay(16L)
//            }
//        }
//        stateOne[0] = true
//    }


    for(j in 0..3)
    {
        if(globalYTwo[j].toInt() in activeState[j].. activeState[j]+10 || stateOne[j])
        {
            LaunchedEffect(globalYOne[j]) {
                while(globalYOne[j] <= (screenHeightDp).toInt() + 50) {
                    withFrameNanos {
                        globalYOne[j] += speed
                    }
                    delay(16L)
                }
            }
            stateOne[j] = true
        }
    }


    for(k in 0..3)
    {
        if(globalYOne[k] >= (screenHeightDp).toInt() + 50 )
        {
            stateOne[k] = false
            globalYOne[k] = initialPosition
            boxHitOne[k] = false
            hitBoxLaneOne[k] = false
            bulletHitLaneOne[k] = false
            lateralCollisionLaneOne[k] = false
            if(trap && trapLane() == -1 )
            {
                itemOne[k] = 2
                trap = false
            }
            else
            {
                itemOne[k] = rand((0..1 step 1).toList())
            }
        }
    }

    // Second Column

    LaunchedEffect(globalYTwo[0]) {
        while(globalYTwo[0] < finalDistanceTwo) {
            withFrameNanos {
                globalYTwo[0] += speed
            }
            delay(16L)
        }
    }

    for(m in 0..2)
    {
        if(globalYTwo[m] > posTwo || stateTwo[m])
        {
            LaunchedEffect(globalYTwo[m+1]) {
                while(globalYTwo[m+1] < finalDistanceTwo) {
                    withFrameNanos {
                        globalYTwo[m+1] += speed
                    }
                    delay(16L)
                }

            }
            stateTwo[m] = true
        }
    }

    if(globalYTwo[0] >= finalDistanceTwo)
    {
        globalYTwo[0] = initialPosition
        called[0] = false
        boxHitTwo[0] = false
        hitBoxLaneTwo[0] = false
        bulletHitLaneTwo[0] = false
        lateralCollisionLaneTwo[0] = false

        if(trap && trapLane() == 0 )
        {
            itemTwo[0] = 2
            trap = false
        }
        else
        {
            itemTwo[0] = rand((0..1 step 1 ).toList())
        }
    }

    for(n in 1..3)
    {
        if(globalYTwo[n] >= finalDistanceTwo)
        {
            called[n] = false
            stateTwo[n-1] = false
            globalYTwo[n] =  initialPosition
            boxHitTwo[n] = false
            hitBoxLaneTwo[n] = false
            bulletHitLaneTwo[n] = false
            lateralCollisionLaneTwo[n] = false
            if(trap && trapLane() == 0 )
            {
                itemTwo[n] = 2
                trap = false
            }
            else
            {
                itemTwo[n] = rand((0..1 step 1).toList())
            }
        }
    }

    // third

    for(q in 0..3)
    {
        if(globalYTwo[q].toInt() in activeState[5+q] .. activeState[5+q] + 10 || stateThree[q])
        {
            LaunchedEffect(globalYThree[q]) {
                while(globalYThree[q] <= (screenHeightDp).toInt() + 50) {
                    withFrameNanos {
                        globalYThree[q] += speed
                    }
                    delay(16L)
                }

            }
            stateThree[q] = true
        }
    }

    for(x in 0..3)
    {
        if(globalYThree[x] >= (screenHeightDp).toInt() + 50)
        {
            stateThree[x] = false
            globalYThree[x] = initialPosition
            boxHitThree[x] = false
            hitBoxLaneThree[x] = false
            bulletHitLaneThree[x] = false
            lateralCollisionLaneThree[x] = false
            if(trap && trapLane() == 1 )
            {
                itemThree[x] = 2
                trap = false
            }
            else
            {
                itemThree[x] = rand((0..1 step 1).toList())
            }

        }
    }

}
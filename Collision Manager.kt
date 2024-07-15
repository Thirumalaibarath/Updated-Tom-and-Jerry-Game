package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

var headOnCollision by mutableStateOf(false)
var lateralCollision by mutableStateOf(false)


@Composable
fun CollisionChecker()
{
    val upperLimit = screenHeightDp.toDouble() - 300
    val lowerLimit = screenHeightDp.toDouble() - 180
    val range = upperLimit..lowerLimit

    if(globalYOne.any { it in (upperLimit + 1)..lowerLimit }  )
    {
        collisionRange = globalYOne.withIndex()
            .first { it.value in range }
            .index
    }


    if(globalYTwo.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        collisionRangeTwo = globalYTwo.withIndex()
            .first { it.value in range }
            .index

    }


    if(globalYThree.any {it in (upperLimit + 1)..lowerLimit }  )
    {
        collisionRangeThree = globalYThree.withIndex()
            .first { it.value in range }
            .index
    }


    val jerryHead = screenHeightDp.toDouble() - 220

    val jerryLeft = jerryX.toInt()-20
    val jerryMiddle =  jerryX.toInt()
    val jerryRight =  jerryX.toInt() + 20


    // first head on
    for(x in jerryLeft..jerryMiddle  )
    {
        if(x.toDouble() in  post[0]..post[1] && !bulletHitLaneOne[collisionRange]  && !jerryOnAir && !hitBoxLaneOne[collisionRange])
        {
            if(itemOne[collisionRange] == 0 || itemOne[collisionRange] == 3 )
            {
                if(((globalYOne[collisionRange]+54) - jerryHead) in collisionDistance()..0.0)
                {
                    if(itemOne[collisionRange] == 3)
                    {
                        fanLock[1] = false
                        airControl = true
                        jerryX = 100+space
                    }
                    headOnCollision = true
                    blink = true
                    hit = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneOne[collisionRange])
                    {
                        hitBoxLaneOne[collisionRange] = true
                    }
                }
            }
            else if(itemOne[collisionRange] == 1 )
            {
                if(((globalYOne[collisionRange]+35) - jerryHead) in collisionDistance()..0.0)
                {
                    hit = true
                    headOnCollision = true
                    blink = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneOne[collisionRange])
                    {
                        hitBoxLaneOne[collisionRange] = true
                    }
                }
            }

            else if(itemOne[collisionRange] == 2 )
            {
                if(((globalYOne[collisionRangeTwo]+44) - jerryHead) in collisionDistance()..0.0)
                {
                    trapCollision = true
                    blink = true
                    hit = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneOne[collisionRange])
                    {
                        hitBoxLaneOne[collisionRange] = true
                    }
                }
            }
        }
    }



    // second head on

    for(k in jerryLeft..jerryRight)
    {
        if(( k.toDouble() in post[4]..post[5] )&& !bulletHitLaneTwo[collisionRangeTwo] && !jerryOnAir && !hitBoxLaneTwo[collisionRangeTwo])
        {
            if(itemTwo[collisionRangeTwo] == 0 || itemTwo[collisionRangeTwo] == 3 )
            {
                if(((globalYTwo[collisionRangeTwo]+55) - jerryHead) in collisionDistance()..0.0)
                {
                    if(itemTwo[collisionRangeTwo] == 3)
                    {
                        fanLock[1] = false
                        airControl = true
                        jerryX = if(jerryX in ((space+100+space/2)+1)..<screenWidthDp.toDouble()/2) {
                            100+space
                        } else {
                            2*100+space*2
                        }
                    }
                    headOnCollision = true
                    hit = true
                    blink = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneTwo[collisionRangeTwo])
                    {
                        hitBoxLaneTwo[collisionRangeTwo] = true
                    }
                }
            }
            else if(itemTwo[collisionRangeTwo] == 1 )
            {
                if(((globalYTwo[collisionRangeTwo]+35) - jerryHead) in collisionDistance()..0.0)
                {
                    headOnCollision = true
                    blink = true
                    hit = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneTwo[collisionRangeTwo])
                    {
                        hitBoxLaneTwo[collisionRangeTwo] = true
                    }
                }
            }

            else if(itemTwo[collisionRangeTwo] == 2 )
            {
                if(((globalYTwo[collisionRangeTwo]+44) - jerryHead) in collisionDistance()..0.0)
                {
                    trapCollision = true
                    autoJump = true

                    if(!hitBoxLaneTwo[collisionRangeTwo])
                    {
                        hitBoxLaneTwo[collisionRangeTwo] = true
                    }
                }
            }
        }
    }

    // third head on

    for(c in jerryRight downTo  jerryMiddle)
    {
        if(c.toDouble() in post[8]..post[9] && !bulletHitLaneThree[collisionRangeThree]  && !jerryOnAir && !hitBoxLaneThree[collisionRangeThree])
        {
            if(itemThree[collisionRangeThree] == 0 || itemThree[collisionRangeThree] == 3 )
            {
                if(((globalYThree[collisionRangeThree]+54) - jerryHead) in collisionDistance()..0.0)
                {
                    if(itemThree[collisionRangeThree] == 3)
                    {
                        airControl = true
                        fanLock[2] = false
                        jerryX = 2*100+space*2
                    }

                    headOnCollision = true
                    blink = true
                    hit = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneThree[collisionRangeThree])
                    {
                        hitBoxLaneThree[collisionRangeThree] = true
                    }
                }
            }
            else if(itemThree[collisionRangeThree] == 1 )
            {
                if(((globalYThree[collisionRangeThree]+35) - jerryHead) in collisionDistance()..0.0)
                {
                    headOnCollision = true
                    blink = true
                    hit = true
                    if(hitCount!=2)
                    {
                        speed = -speed
                    }
                    if(!hitBoxLaneThree[collisionRangeThree])
                    {
                        hitBoxLaneThree[collisionRangeThree] = true
                    }
                }
            }

            else if(itemThree[collisionRangeThree] == 2 )
            {
                if(((globalYThree[collisionRangeThree]+44) - jerryHead) in collisionDistance()..0.0)
                {
                    trapCollision = true
                    if(!hitBoxLaneThree[collisionRangeThree])
                    {
                        hitBoxLaneThree[collisionRangeThree] = true
                    }
                }
            }
        }
    }



    if(headOnCollision)
    {
        LaunchedEffect(blink) {
            delay(500)
            speed = -speed
            delay(1200)
            blink = false
            headOnCollision = false
        }
    }

}

//    6-7 -> -12 , 5-6 /6 -> -10
//     8 - 9-> -12

fun collisionDistance():Double
{
    var distance = 0.0

    when (speed) {
        in 5.0 ..<6.0 -> {
            distance = -10.0
        }
        in 6.0 ..7.0 -> {
            distance = -12.0
        }
        in 8.0 ..9.0 -> {
            distance = -12.0
        }
    }

    return distance
}
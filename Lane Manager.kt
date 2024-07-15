package com.example.tomandjerry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateListOf

var crateNumber = mutableStateListOf(0,0,0)
var overLap = mutableStateListOf(false,false,false)
var lateralCollisionLaneOne = mutableStateListOf(false,false,false,false)
var lateralCollisionLaneTwo = mutableStateListOf(false,false,false,false)
var lateralCollisionLaneThree= mutableStateListOf(false,false,false,false)



fun jerryOnObstacle()
{
    val jerryLeft = jerryX.toInt()-20
    val jerryRight =  jerryX.toInt() + 20

    val jerryHead = screenHeightDp.toDouble() - 220
    val jerryBottom =  screenHeightDp.toDouble() - 180

    val rangeOne = post[0]..post[2]
    val rangeTwo =  post[3]..post[6]
    val rangeThree =  post[7]..post[9]



    //One
    for(i in globalYOne)
    {
       if(!bulletHitLaneOne[globalYOne.indexOf(i)] && !lateralCollisionLaneOne[globalYOne.indexOf(i)])
       {
           if(itemOne[globalYOne.indexOf(i)] == 0 )
           {
               for(j in i.toInt()..i.toInt()+54)
               {
                   if( j.toDouble() in jerryHead..jerryBottom )
                   {
                       for(l in jerryLeft..jerryRight )
                       {
                           if(l.toDouble() in rangeOne  && !jerryOnAir && !hitBoxLaneOne[globalYOne.indexOf(i)] )
                           {
                               crateNumber[0] = globalYOne.indexOf(i)
                               jerrySize = 25
                               overLap[0] = true
                           }
                       }
                   }
               }
           }
           else if(itemOne[globalYOne.indexOf(i)] == 1)
           {
               for(j in i.toInt()..i.toInt()+35)
               {
                   if(j.toDouble() in jerryHead..jerryBottom)
                   {
                       for(l in jerryLeft..jerryRight )
                       {
                           if(l.toDouble() in  rangeOne  && !jerryOnAir && !hitBoxLaneOne[globalYOne.indexOf(i)] )
                           {
                               crateNumber[0] = globalYOne.indexOf(i)
                               jerrySize = 25
                               overLap[0] = true
                           }
                       }

                   }
               }

           }
       }

    }

    if( (globalYOne[crateNumber[0]] > jerryBottom && overLap[0]) || (jerryX !in rangeOne && overLap[0] ) )
    {
        jerrySize = 20
        overLap[0] = false
    }

    // Two


    for(i in globalYTwo)
    {
        if(!bulletHitLaneTwo[globalYTwo.indexOf(i)] && !lateralCollisionLaneTwo[globalYTwo.indexOf(i)] && !hitBoxLaneTwo[globalYTwo.indexOf(i)])
        {
            if(itemTwo[globalYTwo.indexOf(i)] == 0 )
            {
                for(j in i.toInt()..i.toInt()+54)
                {
                    if( j.toDouble() in jerryHead..jerryBottom )
                    {
                        for(l in jerryLeft..jerryRight )
                        {
                            if(l.toDouble() in rangeTwo  && !jerryOnAir  )
                            {
                                crateNumber[1] = globalYTwo.indexOf(i)
                                jerrySize = 25
                                overLap[1] = true
                            }
                        }
                    }
                }
            }
            else if(itemTwo[globalYTwo.indexOf(i)] == 1)
            {
                for(j in i.toInt()..i.toInt()+35)
                {
                    if(j.toDouble() in jerryHead..jerryBottom)
                    {
                        for(l in jerryLeft..jerryRight )
                        {
                            if(l.toDouble() in  rangeTwo  && !jerryOnAir  )
                            {
                                crateNumber[1] = globalYTwo.indexOf(i)
                                jerrySize = 25
                                overLap[1] = true
                            }
                        }
                    }
                }
            }
        }

    }

    if((globalYTwo[crateNumber[1]] > (screenHeightDp.toInt() - 180) && overLap[1]) || (jerryX !in rangeTwo && overLap[1] ) )
    {
        jerrySize = 20
        overLap[1] = false
    }

    // Three
    for(i in globalYThree)
    {
        if(!bulletHitLaneThree[globalYThree.indexOf(i)] && !lateralCollisionLaneThree[globalYThree.indexOf(i)] && !hitBoxLaneThree[globalYThree.indexOf(i)])
        {
            if(itemThree[globalYThree.indexOf(i)] == 0 )
            {
                for(j in i.toInt()..i.toInt()+54)
                {
                    if( j.toDouble() in jerryHead..jerryBottom )
                    {
                        for(l in jerryLeft..jerryRight )
                        {
                            if(l.toDouble() in rangeThree  && !jerryOnAir  )
                            {
                                crateNumber[2] = globalYThree.indexOf(i)
                                jerrySize = 25
                                overLap[2] = true
                            }
                        }
                    }
                }
            }
            else if(itemThree[globalYThree.indexOf(i)] == 1)
            {
                for(j in i.toInt()..i.toInt()+35)
                {
                    if(j.toDouble() in jerryHead..jerryBottom)
                    {
                        for(l in jerryLeft..jerryRight )
                        {
                            if(l.toDouble() in  rangeThree  && !jerryOnAir  )
                            {
                                crateNumber[2] = globalYThree.indexOf(i)
                                jerrySize = 25
                                overLap[2] = true
                            }
                        }
                    }
                }
            }
        }

    }

    if( (globalYThree[crateNumber[2]] > (screenHeightDp.toInt() - 180) && overLap[2]) || (jerryX !in rangeThree && overLap[2] )  )
    {
        jerrySize = 20
        overLap[2] = false
    }

}


@Composable
fun ObstacleCrossOver()
{
    val jerryLeft = jerryX.toInt()-20
    val jerryMiddle =  jerryX.toInt()
    val jerryRight =  jerryX.toInt() + 20

    val jerryUp = (screenHeightDp.toInt() - 220)
    val jerryDown = (screenHeightDp.toInt() - 180)


    for(i in globalYOne)
    {
        for(j in i.toInt()+55 downTo i.toInt() step 1)
        {
            if( j in jerryUp..jerryDown )
            {
                for(k in jerryLeft .. jerryMiddle step 1)
                {
                    if( k.toDouble() in  (post[1]+1)..post[2] && !jerryOnAir && !bulletHitLaneOne[globalYOne.indexOf(i)] )
                    {
                        lateralCollisionLaneOne[globalYOne.indexOf(i)] = true
                        blink = true
                        lateralCollision = true
                        jerryX = 100+space
                    }
                }

            }
        }

    }


    for(i in globalYTwo)
    {
        for(j in i.toInt()+55 downTo i.toInt() step 1)
        {
            if ( j in jerryUp..jerryDown )
            {
                for (k in jerryRight downTo jerryMiddle step 1)
                {
                    if (k.toDouble() in post[3]..<post[4] && !jerryOnAir && !bulletHitLaneTwo[globalYTwo.indexOf(i)])
                    {
                        blink = true
                        lateralCollisionLaneTwo[globalYTwo.indexOf(i)] = true
                        lateralCollision = true
                        jerryX = 100 + space
                    }

                }
                for(l in jerryLeft.. jerryMiddle step 1 )
                {
                    if (l.toDouble() in (post[5]+1)..post[6] && !jerryOnAir && !bulletHitLaneTwo[globalYTwo.indexOf(i)])
                     {
                        blink = true
                        lateralCollisionLaneTwo[globalYTwo.indexOf(i)] = true
                        lateralCollision = true
                        jerryX = 2 * 100 + space * 2
                    }
                }
            }
        }
    }



    for(i in globalYThree)
    {
        for (j in i.toInt()+55 downTo i.toInt() step 1)
        {
            if( j in jerryUp..jerryDown  )
            {
                for (k in jerryRight downTo jerryMiddle step 1)
                {
                    if(k.toDouble() in  post[7]..<post[8]  && !jerryOnAir && !bulletHitLaneThree[globalYThree.indexOf(i)])
                    {
                        lateralCollisionLaneThree[globalYThree.indexOf(i)] = true
                        blink = true
                        lateralCollision = true
                        jerryX = 2*100+space*2
                    }
                }
            }
        }
    }


    if(lateralCollision)
    {
        LaunchedEffect(blink) {
            delay(2000)
            blink = false
            lateralCollision = false
        }
    }



}


package com.example.tomandjerry

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay

var bulletLock = mutableStateListOf(false,false,false,false)
var bulletAvailable = mutableStateListOf(true,true,true,true)
var bulletStart = mutableStateListOf(false,false,false,false)
var bulletY = mutableStateListOf(0.0,0.0,0.0,0.0)
var bulletX = mutableStateListOf(0.0,0.0,0.0,0.0)
var bulletControl by mutableStateOf(false)
var autoBulletHit by mutableStateOf(false)
var AutoBulletLane = mutableStateListOf(false,false)
var autoLaneDistance = mutableStateListOf(0.0,0.0)
var autoBulletLock = mutableStateListOf(false,false)
var bulletHitLaneOne = mutableStateListOf(false,false,false,false)
var bulletHitLaneTwo = mutableStateListOf(false,false,false,false)
var bulletHitLaneThree = mutableStateListOf(false,false,false,false)


fun bulletMaker()
{
    if(cheeseCount >= 5 && bulletCount in 0..3 )
    {

        bulletCount += cheeseCount/5
        cheeseCount %= 5
    }

    for(i in bulletCount downTo 1 step 1)
    {
        bullets[i-1] = 0
    }

}

@Composable
fun Bullet(x:Double,y:Double,launched:Boolean,r:Int,color: Color)
{
    if(launched)
    {
        Canvas(modifier = Modifier.size(2*r.dp)) {
            drawCircle(
                color = color,
                radius = r.dp.toPx(),
                center = Offset(x.dp.toPx(), y.dp.toPx())
            )

            drawCircle(
                color = Color.Black,
                radius = r.dp.toPx(),
                center = Offset(x.dp.toPx(), y.dp.toPx()),
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }

}

@Composable
fun AutoBullet(x:Double,y:Double,r:Int,color: Color)
{
    Canvas(modifier = Modifier.size(2*r.dp)) {
        drawCircle(
            color = color,
            radius = r.dp.toPx(),
            center = Offset(x.dp.toPx(), y.dp.toPx())
        )

        drawCircle(
            color = Color.Black,
            radius = r.dp.toPx(),
            center = Offset(x.dp.toPx(), y.dp.toPx()),
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
        )
    }

}


@Composable
fun BulletControl()
{
    val jerryLeft = jerryX.toInt()-20
    val jerryRight =  jerryX.toInt() + 20

    for(i in jerryLeft..jerryRight)
    {
        if( i.toDouble() in  post[2]+1..<post[3] && !AutoBulletLane[0])
        {
            airControl = false
            bulletControl = false
            autoLaneDistance[1] = 0.0
            LaunchedEffect(gameOver) {
                while(!gameOver)
                {
                    autoLaneDistance[0]  += (speed * 0.1)
                    delay(16L)
                }
            }

        }
        else if(i.toDouble() in post[6]+1 ..<post[7] && !AutoBulletLane[1])
        {
            airControl = false
            bulletControl = false
            autoLaneDistance[0] = 0.0
            LaunchedEffect(gameOver) {
                while(!gameOver)
                {
                    autoLaneDistance[1]  += (speed * 0.1)
                    delay(16L)
                }

            }
        }
        else
        {
            airControl = true
            bulletControl = true
        }
    }

}

@Composable
fun AutoBullet()
{
    for( j in 0..1)
    {
        if(!autoBulletLock[j])
        {
            autobulletX[j] = jerryX
        }
    }



    for(i in 0..1)
    {
        if(autoLaneDistance[i] > 25)
        {
            autoLaneDistance[i] = 0.0
            AutoBulletLane[i] = true
            autoBulletLock[i] = true
        }
    }


    if(AutoBulletLane[0])
    {
        val speedy = (screenHeightDp.toDouble() - 220) + 25

        LaunchedEffect(autobulletY[0]) {
            while(autobulletY[0] <= screenHeightDp+40) {
                withFrameNanos {
                    autobulletY[0] += speedy/40
                }
                delay(16L)
            }
        }
    }


    if(AutoBulletLane[1])
    {
        val speedy = (screenHeightDp.toDouble() - 220) + 25

        LaunchedEffect(autobulletY[1]) {
            while(autobulletY[1] <= screenHeightDp+40) {
                withFrameNanos {
                    autobulletY[1] += speedy/40
                }
                delay(16L)
            }
        }
    }

    if(((autobulletY[0].toInt()+5) -(screenHeightDp.toInt() - 220) == 0) && jerryX in  (space/2+101)..(space+99+space/2)  )
    {
        hit = true
        autoBulletHit = true
        autobulletY[0] = -30.0
        blink = true
        AutoBulletLane[0] = false
        autoBulletLock[0] = false
        autoLaneDistance[0] = 0.0

    }

    if(((autobulletY[1].toInt()+5) -(screenHeightDp.toInt() - 220) == 0) && jerryX in (space+201+space/2) ..<(2*space+200+space/2) )
    {
        autoBulletHit = true
        autobulletY[1] = -30.0
        blink = true
        AutoBulletLane[1] = false
        autoBulletLock[1] = false
        autoLaneDistance[1] = 0.0
        hit = true
    }

    for(k in 0..1)
    {
        if(autobulletY[k] >= screenHeightDp+40 )
        {
            AutoBulletLane[k] = false
            autoBulletLock[k] = false
            autobulletY[k] = -30.0
            autoLaneDistance[k] = 0.0
        }
    }

    if(autoBulletHit)
    {
        LaunchedEffect(blink) {
            delay(1500)
            blink = false
            autoBulletHit = false
        }
    }
}

@Composable
fun BulletLauncher()
{
    val rangeOne = post[0]..post[2]
    val rangeTwo = post[3]..post[6]
    val rangeThree = post[7]..post[9]

    for(i in 0..3)
    {
        if(!bulletLock[i])
        {
            bulletX[i] = jerryX
        }
    }


    for(i in 0..3)
    {
        if(bulletStart[i] && bulletCount>=0)
        {
            LaunchedEffect(bulletY) {
                while(bulletY[i] >= 100) {
                    withFrameNanos {
                        bulletY[i] -= 8.0
                    }
                    delay(16L)
                }
            }
        }
    }

    for(j in 0..3)
    {
        if(bulletY[j] <=100 )
        {
            bulletStart[j] = false
            bulletLock[j] = false
            bulletY[j] = screenHeightDp.toDouble() - 200
        }
    }


    for(j in 0..3)
    {
        val bulletLeft =  bulletX[j].toInt()-5
        val bulletRight =  bulletX[j].toInt()+5

        for(k in bulletLeft..bulletRight )
        {
            if(k.toDouble() in rangeOne && bulletStart[j] )
            {
                for(i in globalYOne)
                {
                    if(itemOne[globalYOne.indexOf(i)] == 0 && !bulletHitLaneOne[globalYOne.indexOf(i)]   )
                    {
                        if(bulletY[j] in i..i+55 && i+55 > 0.0 )
                        {
                            bulletHitLaneOne[globalYOne.indexOf(i)] = true
                            bulletStart[j] = false
                            bulletLock[j] = false
                            bulletY[j] = screenHeightDp.toDouble() - 200
                        }
                    }
                    if(itemOne[globalYOne.indexOf(i)] == 1 && !bulletHitLaneOne[globalYOne.indexOf(i)] )
                    {
                        if(bulletY[j] in i..i+35 && i+35 > 0.0)
                        {
                            bulletHitLaneOne[globalYOne.indexOf(i)] = true
                            bulletStart[j] = false
                            bulletLock[j] = false
                            bulletY[j] = screenHeightDp.toDouble() - 200
                        }
                    }
                }
            }
        }
    }



    for(k in 0..3)
    {
        val bulletLeft =  bulletX[k].toInt()-5
        val bulletRight =  bulletX[k].toInt()+5

        for(z in bulletLeft..bulletRight)
        {
            if(z.toDouble() in rangeTwo && bulletStart[k])
            {
                for(i in globalYTwo)
                {
                    if(itemTwo[globalYTwo.indexOf(i)] == 0 && !bulletHitLaneTwo[globalYTwo.indexOf(i)] )
                    {
                        if(bulletY[k] in i..i+55 && i+55 > 0.0)
                        {
                            bulletHitLaneTwo[globalYTwo.indexOf(i)] = true
                            bulletStart[k] = false
                            bulletLock[k] = false
                            bulletY[k] = screenHeightDp.toDouble() - 200
                        }
                    }
                    if(itemTwo[globalYTwo.indexOf(i)] == 1 && !bulletHitLaneTwo[globalYTwo.indexOf(i)] )
                    {
                        if(bulletY[k] in i..i+35 && i+35 > 0.0)
                        {
                            bulletHitLaneTwo[globalYTwo.indexOf(i)] = true
                            bulletStart[k] = false
                            bulletLock[k] = false
                            bulletY[k] = screenHeightDp.toDouble() - 200
                        }
                    }
                }
            }
        }
    }


    for(n in 0..3)
    {
        val bulletLeft =  bulletX[n].toInt()-5
        val bulletRight =  bulletX[n].toInt()+5

        for(u in bulletLeft..bulletRight)
        {
            if(u.toDouble() in rangeThree && bulletStart[n])
            {
                for(i in globalYThree)
                {
                    if(itemThree[globalYThree.indexOf(i)] == 0 && !bulletHitLaneThree[globalYThree.indexOf(i)] )
                    {
                        if(bulletY[n] in i..i+55 && i+55 > 0.0)
                        {
                            bulletHitLaneThree[globalYThree.indexOf(i)] = true
                            bulletStart[n] = false
                            bulletLock[n] = false
                            bulletY[n] = screenHeightDp.toDouble() - 200
                        }
                    }
                    if(itemThree[globalYThree.indexOf(i)] == 1 && !bulletHitLaneThree[globalYThree.indexOf(i)] )
                    {
                        if(bulletY[n] in i..i+35 && i+35 > 0.0)
                        {
                            bulletHitLaneThree[globalYThree.indexOf(i)] = true
                            bulletStart[n] = false
                            bulletLock[n] = false
                            bulletY[n] = screenHeightDp.toDouble() - 200
                        }
                    }
                }
            }
        }
    }
}
package com.example.tomandjerry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val brown = Color(0xFFD07905)
val brownTwo = Color(0xFFBC5B0A)
val backColor = Color(0xFFF8EFDE)
val tomColor = Color(0xFF515151)
val earColor = Color(0xFFDDADA1)

@Composable
fun MainFrame()
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backColor),
    )
    {
        HorizontalRectangleCanvas(
            positionY = (screenHeightDp.toInt() - 200-30).dp  ,
            height = 70.dp ,
        )


        Conveyor(space = space)


        if(!gameOver)
        {
            bulletMaker()
            Parallax()
            CheeseMaker()
//            AutoBullet()
            CollisionChecker()
            BulletControl()
            trap()
            BulletLauncher()
            LaneTracker()
            fanSetter()
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(space.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                contentAlignment = Alignment.TopCenter

            )
            {
                Obstacle(0.0,globalYOne[0],itemOne[0],bulletHitLaneOne[0])
                Obstacle(0.0,globalYOne[1],itemOne[1],bulletHitLaneOne[1])
                Obstacle(0.0,globalYOne[2],itemOne[2],bulletHitLaneOne[2])
                Obstacle(0.0,globalYOne[3],itemOne[3],bulletHitLaneOne[3])

                Cheese( cheese[2],caught[2],high[2])
                Cheese( cheese[3], caught[3],high[3])
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                contentAlignment = Alignment.TopCenter
                //itemTwo[0]

            ) {
                Obstacle(0.0,globalYTwo[0],itemTwo[0],bulletHitLaneTwo[0])
                Obstacle(0.0,globalYTwo[1],itemTwo[1],bulletHitLaneTwo[1])
                Obstacle(0.0,globalYTwo[2],itemTwo[2],bulletHitLaneTwo[2])
                Obstacle(0.0,globalYTwo[3],itemTwo[3],bulletHitLaneTwo[3])

                Cheese( cheese[0],caught[0],high[0])
                Cheese( cheese[1],caught[1],high[1])
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                contentAlignment = Alignment.TopCenter
            ){
                Obstacle(0.0,globalYThree[0],itemThree[0],bulletHitLaneThree[0])
                Obstacle(0.0,globalYThree[1],itemThree[1],bulletHitLaneThree[1])
                Obstacle(0.0,globalYThree[2],itemThree[2],bulletHitLaneThree[2])
                Obstacle(0.0,globalYThree[3],itemThree[3],bulletHitLaneThree[3])

                Cheese( cheese[4], caught[4],high[4])
                Cheese( cheese[5], caught[5],high[5])
            }

        }


        if(!gameOver)
        {
            for(i in 0..3)
            {
                Bullet(x= bulletX[i],y = bulletY[i],bulletStart[i],r = 5,color=Color.DarkGray)
            }
        }

        // AutoBulletOne

        AutoBullet(x=autobulletX[0] ,y = autobulletY[0] ,r = 5,color=Color.DarkGray)
        AutoBullet(x=autobulletX[1] ,y = autobulletY[1] ,r = 5,color=Color.DarkGray)

        Jerry(x = jerryX  , y = screenHeightDp.toInt() - 200, r = jerrySize, color = brown)
        Tom(x = tomX , y = tomY , r = 30, color = tomColor)

        Tachometer()


    }
}
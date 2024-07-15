package com.example.tomandjerry

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay

// Controls
@Composable
fun Controller() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(jerrySize,airControl,blink) {
                detectVerticalDragGestures { _, dragAmount ->
                    if (dragAmount < -50) {
                        if (!blink) {
                            if(airControl)
                            {
                                jumpUp = true
                                jerryOnAir = true
                            }
                        }

                    }
                }
            }
            .pointerInput(bulletStart) {
                detectTapGestures(
                    onDoubleTap = {
                        if (!blink && bulletControl && bulletCount>0) {
                            bulletAvailable[bulletCount-1] = false
                            bullets[bulletCount-1] = 1
                            bulletStart[bulletCount-1] = true
                            bulletLock[bulletCount-1] = true
                            bulletCount -= 1
                        }


                    }
                )
            }
    )



    if(jumpUp)
    {
        LaunchedEffect(jerrySize){
            while(jerrySize < 40){
                jerrySize += 1
                delay(1000L)
            }


        }

    }

    if (jerrySize == 39 || jerrySize == 40)
    {
        jumpUp = false
        jumpDown = true
    }


    if ( jumpDown ) {
        jumpUp = false
        LaunchedEffect(jerrySize) {
            while(jerrySize >= 20){
                jerrySize -= 1
                delay(100L)

            }

        }
    }

    if (jerrySize == 19 )
    {
        jumpDown = false
        jerrySize = 20
        jerryOnAir = false
    }

}
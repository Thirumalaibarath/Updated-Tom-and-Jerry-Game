package com.example.tomandjerry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

var hitCount by mutableIntStateOf(0)

fun livesCalculator()
{
    if(hit && hitCount in 0..2 step 1)
    {
        lives[2- hitCount] = 1
        hitCount += 1
        tomY-=50
        hit = false
    }
}


fun gameOver()
{
    if(hitCount == 3)
    {
        tomX = jerryX
        gameOver = true
//        blink = true
    }
}
package com.example.tomandjerry

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.media.MediaPlayer
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

var density by mutableFloatStateOf(0f)
var screenWidthDp by mutableFloatStateOf(0f)
var screenHeightDp by mutableFloatStateOf(0f)

var initialPosition by mutableDoubleStateOf(-screenHeightDp.toDouble()/2)

var globalYOne  =  mutableStateListOf(initialPosition,initialPosition,initialPosition,initialPosition)
var globalYTwo  =  mutableStateListOf(initialPosition,initialPosition,initialPosition,initialPosition)
var globalYThree = mutableStateListOf(initialPosition,initialPosition,initialPosition,initialPosition)

var itemOne =  mutableStateListOf(1,1,0,1,0)
var itemTwo =  mutableStateListOf(0,1,1,0,0)
var itemThree =  mutableStateListOf(0,1,0,1,0)

var activeState = mutableStateListOf(0,0,0,0,0,0,0,0,0,0)
var called = mutableStateListOf(false,false,false,false,false)

var jerryX by mutableDoubleStateOf(0.0)
var tomX by mutableDoubleStateOf(0.0)
var tomY by mutableDoubleStateOf(0.0)
var hit by mutableStateOf(false )


var cheeseCount by mutableIntStateOf(0)
var bulletCount by mutableIntStateOf(4)


var collisionRange by mutableIntStateOf(0)
var collisionRangeTwo by mutableIntStateOf(0)
var collisionRangeThree by mutableIntStateOf(0)

var airControl by mutableStateOf(true)

var cheeseRange by mutableIntStateOf(0)
var cheeseRangeTwo by mutableIntStateOf(0)
var cheeseRangeThree by mutableIntStateOf(0)

var gameOver by mutableStateOf(false)

var jerrySize by mutableIntStateOf(20)

var speed  by mutableDoubleStateOf(0.0)

var jumpUp by mutableStateOf(false)
var jumpDown by mutableStateOf(false)
var jerryOnAir by mutableStateOf(false)

var blink by mutableStateOf(false)

var coordInitializer by mutableStateOf(false)

var posTwo by   mutableIntStateOf(0)


var autobulletX = mutableStateListOf(0.0,0.0)
var autobulletY = mutableStateListOf(-30.0,-30.0)

var space by  mutableDoubleStateOf(0.0)

var finalDistanceTwo by  mutableIntStateOf(0)

var movement by  mutableStateOf(true)
var trap by  mutableStateOf(true)

var distanceTraversed by  mutableDoubleStateOf(0.0)

var stateOne  = mutableStateListOf(false,false,false,false,false)
var stateTwo  = mutableStateListOf(false,false,false,false)
var stateThree  = mutableStateListOf(false,false,false,false,false)

var post  = mutableStateListOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
var cheesePost = mutableStateListOf(0.0,0.0,0.0,0.0,0.0,0.0)

var caught  = mutableStateListOf(false,false,false,false,false,false)

var boxHitOne = mutableStateListOf(false,false,false,false,false)
var boxHitTwo = mutableStateListOf(false,false,false,false,false)
var boxHitThree = mutableStateListOf(false,false,false,false,false)


var hitBoxLaneOne  = mutableStateListOf(false,false,false,false,false)
var hitBoxLaneTwo  = mutableStateListOf(false,false,false,false,false)
var hitBoxLaneThree  = mutableStateListOf(false,false,false,false,false)

var cheese = mutableStateListOf(-100.0,-100.0,-100.0,-100.0,-100.0,-100.0)

var cheeseCall = mutableStateListOf(false,false,false,false,false,false)
var cheeseState = mutableStateListOf(false,false,false,false,false,false)
var cheesePos  = mutableStateListOf(0,0,0,0,0,0)
var high = mutableStateListOf(false,false,false,false,false,false)

var overlap = mutableStateListOf(false,false,false,false,false,false)

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var gyroscopeSensor: Sensor? = null

    // State variable to store gyroscope data
    private val gyroscopeData = mutableStateOf("Gyroscope data will appear here")

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            MusicApp()

            initialize(context = this)

            space = (screenWidthDp.toDouble() - 300) / 3

            if(!gameOver)
            {

                SpeedIncrease()

                if(!coordInitializer)
                {
                    coordInitializer = true
                    initialPosition = -screenHeightDp.toDouble()/2
                    finalDistanceTwo =  (3* screenHeightDp.toInt())/2
                    for (i in 0..3)
                    {
                        globalYOne[i] = initialPosition
                        globalYTwo[i] = initialPosition
                        globalYThree[i] = initialPosition
                    }
                    posTwo = 0


                    jerryX = (screenWidthDp.toDouble()/2)
                    tomX  = (screenWidthDp.toDouble()/2)
                    tomY = screenHeightDp.toDouble()-25

                    for(i in 0..3)
                    {
                        bulletY[i] = screenHeightDp.toDouble() - 200
                    }

                    speed = 4.0

                    post[0] = space/2 + 50
                    post[1] = space/2 + 68
                    post[2] = space/2 + 78
                    post[3] = screenWidthDp.toDouble()/2  - 28
                    post[4] = screenWidthDp.toDouble()/2 -  18
                    post[5] = screenWidthDp.toDouble()/2  + 18
                    post[6] = screenWidthDp.toDouble()/2  + 28
                    post[7] =  screenWidthDp.toDouble() - space/2 - 78
                    post[8] = screenWidthDp.toDouble() - space/2 - 68
                    post[9] = screenWidthDp.toDouble() - space/2 - 50


                    cheesePost[0] = space/2+50
                    cheesePost[1] = space/2+50+15+jerrySize
                    cheesePost[2] = screenWidthDp.toDouble()/2-15-jerrySize
                    cheesePost[3] = screenWidthDp.toDouble()/2+15+jerrySize
                    cheesePost[4] = screenWidthDp.toDouble() - space/2  - 65- jerrySize
                    cheesePost[5] = screenWidthDp.toDouble() - space/2 - 50
                }

                onJump()

                //For Tom Obstacle Manager
                tomObstacleManager()

                // Cheese
                CheeseChecker()

                // Controller
                Controller()

                ObstacleCrossOver()

                OnTrap()

                livesCalculator()

                // jerry On obstacle
                jerryOnObstacle()

                //autoJump
//                AutoJump()
//
            }

            MainFrame()

            gameOver()

            Debugger()
//
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyroscopeSensor == null) {
            gyroscopeData.value = "Gyroscope sensor not available"
        }
    }

    override fun onResume() {
        super.onResume()
        gyroscopeSensor?.also { gyro ->
            sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE && movement) {
            val rotationY = event.values[1]

            if(!blink && !gameOver) {
                jerryX += rotationY * 50
            }

            val minX = (space / 2) + 50
            val maxX = screenWidthDp.toDouble() - (space / 2) - 50

            jerryX = jerryX.coerceIn(minX, maxX)

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle sensor accuracy changes if needed
    }


}

@Composable
fun MusicApp() {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.fein) }

    LaunchedEffect(Unit) {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

}

fun initialize(context: Context) {
    val displayMetrics = context.resources.displayMetrics
    density = displayMetrics.density
    screenWidthDp = displayMetrics.widthPixels / density
    screenHeightDp = displayMetrics.heightPixels / density
}


// The Random Number generator

fun rand(list:List<Int>):Int
{
    val randomIndex = list.indices.random()
    return list[randomIndex]
}


@Composable
fun DrawHorizontalLine(y:Double) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw a horizontal line at 100.dp from the top
        val yPosition = y.dp.toPx() // Convert 100.dp to pixels
        drawLine(
            color = Color.Black,
            start = androidx.compose.ui.geometry.Offset(0f, yPosition),
            end = androidx.compose.ui.geometry.Offset(size.width, yPosition),
            strokeWidth = 10.dp.toPx() // Set the line thickness
        )
    }
}

@Composable
fun DrawVerticalLine(x: Double) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw a vertical line at the specified x position in dp
        val xPosition = x.dp.toPx() // Convert the x position from dp to pixels
        drawLine(
            color = Color.Red,
            start = androidx.compose.ui.geometry.Offset(xPosition, 0f),
            end = androidx.compose.ui.geometry.Offset(xPosition, size.height),
            strokeWidth = 4.dp.toPx() // Set the line thickness
        )
    }
}
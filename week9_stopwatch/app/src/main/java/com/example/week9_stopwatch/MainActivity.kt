package com.example.week9_stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.week9_stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var timeG=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var timerView : TextView = findViewById(R.id.timerView)
        var startBtn : Button = findViewById(R.id.startBtn)
        var stopBtn : Button = findViewById(R.id.stopBtn)
        var pauseBtn : Button = findViewById(R.id.pauseBtn)
        var resumeBtn : Button = findViewById(R.id.resumeBtn)

        val channel= Channel<Int>()

        lateinit var backGroundScope: Job

        var mainScope = GlobalScope.launch(Dispatchers.Main){
            channel.consumeEach{
                val df = DecimalFormat("00")
                var second = it/60
                val minute = it /3600
                val hour = it/216000
                binding.timerView.text="${df.format(hour)}:${df.format(minute)}:${df.format(second)}"
            }
        }
        startBtn.setOnClickListener {
            backGroundScope = CoroutineScope(Dispatchers.Default + Job()).launch {
                for (i in 1..2_000_000_000) {
                    timeG += 1
                    channel.send(timeG)
                    delay(10)
                }
            }
        }

        stopBtn.setOnClickListener {
            backGroundScope.cancel()
            timerView.text = "00:00:00"
            timeG = 0
        }

        pauseBtn.setOnClickListener {
            backGroundScope.cancel()
        }

        resumeBtn.setOnClickListener {
            val backGroundScope = CoroutineScope(Dispatchers.Default + Job())
            backGroundScope.launch {
                for (i in 1..2_000_000_000) {
                    timeG += 1
                    channel.send(timeG)
                    delay(10)
                }
            }
        }

    }
}
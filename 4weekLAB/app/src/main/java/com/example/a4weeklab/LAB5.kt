package com.example.a4weeklab

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LAB5 :   AppCompatActivity(){
    lateinit var BUTTON : Button
    lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        title = "직접해보기 1번 문제"
        val colors=intArrayOf(Color.RED, Color.GREEN, Color.BLUE)
        var colorIndex = 0
        layout = findViewById(R.id.layout)
        BUTTON = findViewById(R.id.LinLay)
        BUTTON.setOnClickListener {
            layout.setBackgroundColor(colors[colorIndex])
            colorIndex = (colorIndex + 1) % colors.size
        }
    }
}
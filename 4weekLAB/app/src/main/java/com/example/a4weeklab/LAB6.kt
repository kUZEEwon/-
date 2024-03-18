package com.example.a4weeklab

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class LAB6:   AppCompatActivity(){
    lateinit var BTN1 :Button
    lateinit var BTN2 :Button; lateinit var BTN3 :Button
    lateinit var BTN4 :Button; lateinit var BTN5 :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        title = "직접해보기 6번 문제"


        BTN1 = findViewById(R.id.btn1)
        BTN2 = findViewById(R.id.btn2)
        BTN3 = findViewById(R.id.btn3)
        BTN4 = findViewById(R.id.btn4)
        BTN5 = findViewById(R.id.btn5)

        val btnArray= arrayOf(BTN1, BTN2, BTN3,BTN4,BTN5)
        var btnIndex = 0
        var clickNum = 0

        BTN1.setOnClickListener{

            BTN1.visibility=View.INVISIBLE
            BTN2.visibility=View.VISIBLE
            BTN3.visibility=View.INVISIBLE
            BTN4.visibility=View.INVISIBLE
            BTN5.visibility=View.INVISIBLE
        }
        BTN2.setOnClickListener{

            BTN1.visibility=View.INVISIBLE
            BTN2.visibility=View.INVISIBLE
            BTN3.visibility=View.VISIBLE
            BTN4.visibility=View.INVISIBLE
            BTN5.visibility=View.INVISIBLE
        }
        BTN3.setOnClickListener{

            BTN1.visibility=View.INVISIBLE
            BTN2.visibility=View.INVISIBLE
            BTN3.visibility=View.INVISIBLE
            BTN4.visibility=View.VISIBLE
            BTN5.visibility=View.INVISIBLE
        }
        BTN4.setOnClickListener{

            BTN1.visibility=View.INVISIBLE
            BTN2.visibility=View.INVISIBLE
            BTN3.visibility=View.INVISIBLE
            BTN4.visibility=View.INVISIBLE
            BTN5.visibility=View.VISIBLE
        }
        BTN5.setOnClickListener{

            BTN1.visibility=View.VISIBLE
            BTN2.visibility=View.INVISIBLE
            BTN3.visibility=View.INVISIBLE
            BTN4.visibility=View.INVISIBLE
            BTN5.visibility=View.INVISIBLE
        }
    }
}
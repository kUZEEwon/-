package com.example.week6_lab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.provider.MediaStore
import android.widget.ImageView

class ex3_result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex3_result)
        Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
        var tvNameAndAge : TextView = findViewById(R.id.tvNameAndAge)
        var tvNumber : TextView = findViewById(R.id.tvNumber)
        var tvAddress : TextView = findViewById(R.id.tvAddress)
        var tvEtc : TextView = findViewById(R.id.tvEtc)
        var tvimage: ImageView = findViewById(R.id.image)

        var name = intent.getStringExtra("name")
        var age = intent.getStringExtra("age")
        var number = intent.getStringExtra("number")
        var address = intent.getStringExtra("address")
        var etc = intent.getStringExtra("etc")
// 여기 부터가 문제임



        tvNameAndAge.setText("이름: $name, 나이: $age 세")
        tvNumber.setText("연락처: $number")
        tvAddress.setText("주소: $address")
        tvEtc.setText("기타: $etc")



    }
}
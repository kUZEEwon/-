package com.example.week6_lab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.provider.MediaStore
import android.widget.ImageView
import java.io.ByteArrayOutputStream

class ex3_sub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex3_sub)
        val btn_finish : Button = findViewById(R.id.btn_finish)
        val btn_refactor : Button = findViewById(R.id.btn_refactor)
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
        // Intent에서 Bitmap 데이터 가져오기
        val bitmap = intent.getParcelableExtra<Bitmap>("image")

// ImageView에 Bitmap 표시
        tvimage.setImageBitmap(bitmap)

        tvNameAndAge.setText("이름: $name, 나이: $age 세")
        tvNumber.setText("연락처: $number")
        tvAddress.setText("주소: $address")
        tvEtc.setText("기타: $etc")

        btn_refactor.setOnClickListener {
            finish()
        }

        btn_finish.setOnClickListener {

            val intent = Intent(this, ex3_result::class.java)

            intent.putExtra("name", name.toString())
            intent.putExtra("age", age.toString())
            intent.putExtra("number", number.toString())
            intent.putExtra("address", address.toString())
            intent.putExtra("etc", etc.toString())

            startActivity(intent)


            finish()
        }

    }
}
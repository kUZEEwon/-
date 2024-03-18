package com.example.week6_hw

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1 : Button = findViewById(R.id.btn1)
        var etKorName : EditText = findViewById(R.id.etKorName)
        var etEngName : EditText = findViewById(R.id.etEngName)
        var etContact : EditText = findViewById(R.id.etContact)
        var etEMail : EditText = findViewById(R.id.etEMail)
        var etAddress : EditText = findViewById(R.id.etAddress)

        btn1.setOnClickListener {
            if (etKorName.text.isEmpty()) {
                Toast.makeText(this, "한글 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (etEngName.text.isEmpty()) {
                Toast.makeText(this, "영문 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (etContact.text.isEmpty()) {
                Toast.makeText(this, "연락처를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (etEMail.text.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (etAddress.text.isEmpty()) {
                Toast.makeText(this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var KorName : String = etKorName.text.toString()
            var EngName : String = etEngName.text.toString()
            var Contact : String = etContact.text.toString()
            var EMail : String = etEMail.text.toString()
            var Address : String = etAddress.text.toString()

            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("KorName", KorName)
            intent.putExtra("EngName", EngName)
            intent.putExtra("Contact", Contact)
            intent.putExtra("EMail", EMail)
            intent.putExtra("Address", Address)

            startActivity(intent)
        }
    }
}
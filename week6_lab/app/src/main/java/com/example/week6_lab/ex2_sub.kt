package com.example.week6_lab
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class ex2_sub: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex2_sub)
        val btn : Button = findViewById(R.id.btn_sub)
        val ev : EditText = findViewById(R.id.ev_sub)
        val res: TextView = findViewById(R.id.res_sub)
        val value = intent.getStringExtra("data")
        res.setText(value)

        btn.setOnClickListener{
            if(ev.text.isEmpty()){
                Toast.makeText(this,"값을 입력하시오.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data:String = ev.text.toString()
            val intent = Intent(this, ex2_sub::class.java)
            intent.putExtra("data", data)
            startActivityForResult(intent, 100)
            ev.text = null
            finish()
        }
    }

}
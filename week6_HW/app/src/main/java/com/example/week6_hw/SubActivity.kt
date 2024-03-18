package com.example.week6_hw
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var edittext1 :EditText=findViewById(R.id.grow)
        var edittext2 :EditText=findViewById(R.id.school)
        var edittext3 :EditText=findViewById(R.id.chracter)
        var edittext4 :EditText=findViewById(R.id.your)

        var btn2 : Button = findViewById(R.id.btn2)
        var tvKorName : TextView = findViewById(R.id.tvKorName)
        var tvEngName : TextView = findViewById(R.id.tvEngName)
        var tvContact : TextView = findViewById(R.id.tvContact)
        var tvEMail : TextView = findViewById(R.id.tvEMail)
        var tvAddress : TextView = findViewById(R.id.tvAddress)

        tvKorName.setText(intent.getStringExtra("KorName"))
        tvEngName.setText(intent.getStringExtra("EngName"))
        tvContact.setText(intent.getStringExtra("Contact"))
        tvEMail.setText(intent.getStringExtra("EMail"))
        tvAddress.setText(intent.getStringExtra("Address"))


        btn2.setOnClickListener {
            var etext1 : String = edittext1.text.toString()
            var etext2 : String = edittext2.text.toString()
            var etext3 : String = edittext3.text.toString()
            var etext4 : String = edittext4.text.toString()
            finish()
        }
    }
}
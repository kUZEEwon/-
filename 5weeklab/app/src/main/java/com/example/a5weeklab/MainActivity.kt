package com.example.a5weeklab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var editText : EditText
    lateinit var numberText : TextView
    lateinit var Button0 : Button
    lateinit var Button1 : Button
    lateinit var Button2 : Button
    lateinit var Button3 : Button
    lateinit var Button4 : Button
    lateinit var Button5 : Button
    lateinit var Button6 : Button
    lateinit var Button7 : Button
    lateinit var Button8 : Button
    lateinit var Button9 : Button
    lateinit var ButtonStar : Button
    lateinit var ButtonHash : Button
    lateinit var ButtonVideo : ImageButton
    lateinit var ButtonCall : ImageButton
    lateinit var ButtonBack : ImageButton
    var number : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        editText = findViewById(R.id.EditText)
        numberText = findViewById(R.id.numberText)
        Button0 = findViewById(R.id.Button0)
        Button1 = findViewById(R.id.Button1)
        Button2 = findViewById(R.id.Button2)
        Button3 = findViewById(R.id.Button3)
        Button4 = findViewById(R.id.Button4)
        Button5 = findViewById(R.id.Button5)
        Button6 = findViewById(R.id.Button6)
        Button7 = findViewById(R.id.Button7)
        Button8 = findViewById(R.id.Button8)
        Button9 = findViewById(R.id.Button9)
        ButtonStar = findViewById(R.id.ButtonStar)
        ButtonHash = findViewById(R.id.ButtonHash)
        ButtonVideo = findViewById(R.id.ButtonVideo)
        ButtonCall = findViewById(R.id.ButtonCall)
        ButtonBack = findViewById(R.id.ButtonBack)



        Button0.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-0"
                } else {
                    numberText.text = "${number}0"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button1.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-1"
                } else {
                    numberText.text = "${number}1"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button2.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-2"
                } else {
                    numberText.text = "${number}2"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button3.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-3"
                } else {
                    numberText.text = "${number}3"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button4.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-4"
                } else {
                    numberText.text = "${number}4"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button5.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-5"
                } else {
                    numberText.text = "${number}5"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button6.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-6"
                } else {
                    numberText.text = "${number}6"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button7.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-7"
                } else {
                    numberText.text = "${number}7"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button8.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-8"
                } else {
                    numberText.text = "${number}8"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Button9.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-9"
                } else {
                    numberText.text = "${number}9"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        ButtonStar.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-*"
                } else {
                    numberText.text = "${number}*"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        ButtonHash.setOnClickListener {
            try {
                number = numberText.text.toString()
                if (number.length.equals(3) || number.length.equals(8)) {
                    numberText.text = "${number}-#"
                } else {
                    numberText.text = "${number}#"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        ButtonVideo.setOnClickListener {
            try {
                Toast.makeText(this, "영상통화를 거는중..", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        ButtonCall.setOnClickListener {
            try {
                Toast.makeText(this, "전화를 거는중..", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "입력이 불가합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        ButtonBack.setOnClickListener {
            try {
                number = numberText.text.toString()
                numberText.text = number.substring(0, number.length-1)
            } catch (e: Exception) {
                Toast.makeText(this, "지울 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
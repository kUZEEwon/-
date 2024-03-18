package com.example.a4weeklab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class HW  :   AppCompatActivity() {
    lateinit var edit1: EditText;
    lateinit var edit2: EditText
    lateinit var btnAdd: Button;
    lateinit var btnSub: Button
    lateinit var btnMul: Button;
    lateinit var btnDiv: Button
    lateinit var btnRem: Button;
    lateinit var btnChg: Button
    lateinit var textResult: TextView;
    lateinit var textResult2: TextView
    lateinit var num1: String;
    lateinit var num2: String
    var result: Int? = null
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw)
        title = "${count}회 계산기"

        edit1 = findViewById(R.id.Edit1)
        edit2 = findViewById(R.id.Edit2)

        btnAdd = findViewById(R.id.BtnAdd)
        btnSub = findViewById(R.id.BtnSub)
        btnMul = findViewById(R.id.BtnMul)
        btnDiv = findViewById(R.id.BtnDiv)
        btnRem = findViewById(R.id.BtnRem)
        btnChg = findViewById(R.id.BtnChg)
        textResult = findViewById(R.id.TextResult)
        textResult2 = findViewById(R.id.result2)
        title = "$count 회 계산기"

        btnAdd.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                result = Integer.parseInt(num1) + Integer.parseInt(num2)
                edit1.setText(result.toString())
                edit2.text = null
                textResult2.visibility = View.GONE
                textResult.text = "계산 결과 : " + result.toString()
                count++
                title = "${count}회 계산기"
            }
            false
        }

        btnSub.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                result = Integer.parseInt(num1) - Integer.parseInt(num2)
                edit1.setText(result.toString())
                edit2.text = null
                textResult2.visibility = View.GONE
                textResult.text = "계산 결과 : " + result.toString()
                count++
                title = "${count}회 계산기"
            }
            false
        }

        btnMul.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                result = Integer.parseInt(num1) * Integer.parseInt(num2)
                edit1.setText(result.toString())
                edit2.text = null
                textResult2.visibility = View.GONE
                textResult.text = "계산 결과 : " + result.toString()
                count++
                title = "${count}회 계산기"
            }
            false
        }

        btnDiv.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (num2 == "0") {
                    //textResult.visibility = View.GONE
                    textResult.text = "오류입니다."
                } else {
                    result = Integer.parseInt(num1) / Integer.parseInt(num2)
                    edit1.setText(result.toString())
                    edit2.text = null
                    textResult2.visibility = View.GONE
                    textResult.text = "계산 결과 : " + result.toString()
                    count++
                    title = "${count}회 계산기"
                }

            }

            false
        }

        btnRem.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                if (num2 == "0") {
                    textResult.text = "오류입니다."
                } else {
                    result = Integer.parseInt(num1) % Integer.parseInt(num2)
                    edit1.setText(result.toString())
                    edit2.text = null
                    textResult2.visibility = View.GONE
                    textResult.text = "계산 결과 : " + result.toString()
                    count++
                    title = "${count}회 계산기"
                }

            }

            false
        }

        btnChg.setOnTouchListener { view, motionEvent ->
            if (edit1.text.isEmpty() || edit2.text.isEmpty()) {
                textResult2.visibility = View.VISIBLE
                textResult2.text = "숫자를 입력하시오."
            } else {
                num1 = edit1.text.toString()
                num2 = edit2.text.toString()
                edit1.setText(num2)
                edit2.setText(num1)
                textResult2.visibility = View.GONE
                count++
                title = "${count}회 계산기"
            }

            false
        }

    
    }
}
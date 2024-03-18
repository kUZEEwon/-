package com.example.week6_lab
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
class SubActivity : AppCompatActivity(){
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        setContentView(R.layout.ex1_sub)
    }
}
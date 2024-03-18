package com.example.week10_lab2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val doButton1 = findViewById<Button>(R.id.doButton1)
        val doButton2 = findViewById<Button>(R.id.doButton2)
        val doButton3 = findViewById<Button>(R.id.doButton3)
        val doButton4 = findViewById<Button>(R.id.doButton4)
        doButton1.setOnClickListener(){
            fileClass()
        }
        doButton2.setOnClickListener(){
            ExternalIF()
        }
        doButton3.setOnClickListener(){
            contextFunction()
        }
        doButton4.setOnClickListener(){
            sharedPreferences()
        }
    }
    fun fileClass(){
        val file = File(filesDir, "fileClass.txt")
        val str="hello world"
        val output1 = findViewById<TextView>(R.id.output1)
        val writeStream : OutputStreamWriter=file.writer()
        output1.append("<파일클래스>\n")

        writeStream.write(str)
        writeStream.flush()
        output1.append("파일쓰기 : $str\n")

        val readStream:BufferedReader = file.reader().buffered()
        output1.append("파일 읽기 : \n")
        readStream.forEachLine {
            output1.append(" $it \n")
        }
        return
    }
    fun ExternalIF()
    {
        val output1 = findViewById<TextView>(R.id.output1)
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)
        {
            output1.append("ExternalStorageState Mounted\n")
            ExternelFile()
        }
        else{
            output1.append("ExternalStorageState UnMounted\n")
        }
    }
    fun ExternelFile()
    {
        val output1 = findViewById<TextView>(R.id.output1)
        val file :File=File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "FIleExternel.txt")
        val str = "Android World"
        val writeStream: OutputStreamWriter=file.writer()
        output1.append("<파일 클래스 외부>\n")

        writeStream.write("$str\n")
        writeStream.flush()
        output1.append("파일 쓰기 : \n")

        val readStream:BufferedReader = file.reader().buffered()
        readStream.forEachLine {
            output1.append(" $it\n")
        }
    }
    fun contextFunction()
    {
        val output1 = findViewById<TextView>(R.id.output1)
        val str = "have a good time"
        output1.append("<컨택스트 함수>\n")

        openFileOutput("context.text", Context.MODE_PRIVATE)
            .use{it.write(str.toByteArray())}
        output1.append("파일 쓰기 : $str\n")

        output1.append("파일 읽기 : $str\n")
        openFileInput("context.txt")
            .bufferedReader().forEachLine {
                output1.append(" $it\n")
            }
    }
    fun sharedPreferences(){
        val output1 = findViewById<TextView>(R.id.output1)
        val name = "Jack"
        val score = 100
        output1.append("<sharedPreferences>\n")

        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        sharedPref.edit().run {
            putString("name",name)
            putInt("score",score)
            commit()
        }
        output1.append("키-값 저장 : $name, $score\n")
        val data1 = sharedPref.getString("name","none")
        val data2 = sharedPref.getInt("score",0)
        output1.append("키-값 읽기 : $data1, $data2\n")
    }
}
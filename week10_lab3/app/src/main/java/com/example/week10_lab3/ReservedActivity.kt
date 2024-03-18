package com.example.week10_lab3


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

data class ReservedMovie(
    val _id :Int?,
    val name :String?,
    val poster_image:String?,
    val director : String?,
    val synopsis : String?,
    val reserved_time : String?
):Serializable


class ReservedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserved)

        processIntent(intent)
        val btnclose= findViewById<Button>(R.id.btnclose)
        btnclose.setOnClickListener(){
            finish()
        }
    }

    fun processIntent(intent : Intent?){
        val movies = intent?.getSerializableExtra("movies") as ArrayList<ReservedMovie>?
        val movie = movies?.get(0)
        if(movie != null){
            val posterImageView=findViewById<ImageView>(R.id.posterImageView)
            val input1=findViewById<TextView>(R.id.input1)
            val input2=findViewById<TextView>(R.id.input2)
            val input3=findViewById<TextView>(R.id.input3)
            val input4=findViewById<TextView>(R.id.input4)
            posterImageView.setImageURI(Uri.parse(movie.poster_image))
            input1.setText(movie.name)
            //***** Textview 값 부여 *****//
            input2.setText(movie.reserved_time)
            input3.setText(movie.director)
            input4.setText(movie.synopsis)
        }

    }
}
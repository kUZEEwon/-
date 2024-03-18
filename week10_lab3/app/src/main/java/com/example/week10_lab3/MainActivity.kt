package com.example.week10_lab3

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

import java.io.*

//MainActivity 기본 요소 구현
//객체 요소변수, onCreate(), setOnClickListener() ...
//Activity 초기화시 테이블 삭제
class MainActivity : AppCompatActivity() {
    val databaseName = "movie"
    var database : SQLiteDatabase? = null
    val tableName = "movie_reserved"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDatabase()
        createTable()

        val reservation = findViewById<Button>(R.id.reservation)
        val information = findViewById<Button>(R.id.information)
        reservation.setOnClickListener(){ saveMovie() }
        information.setOnClickListener(){ loadMovie() }
    }

    fun createDatabase(){
        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)
    }

    fun createTable(){
        //***** SQL 문 구성 *****
        //database?.execSQL("DROP Table ${tableName}")
        val sql = "create table if not exists ${tableName}"+
                "(_id integer PRIMARY KEY autoincrement, "+
                "name text, "+
                "poster_image text, "+
                "director text, "+
                "synopsis text, "+
                "reserved_time text)"

        if(database == null) return
        //***** SQL 문 실행 *****
        database?.execSQL(sql)
    }

    fun saveMovie(){
        val posterImageUri = savePosterToFile(R.drawable.gg)

        val input1=findViewById<TextView>(R.id.input1)
        val input2=findViewById<TextView>(R.id.input2)
        val input3=findViewById<TextView>(R.id.input3)
        val input4=findViewById<TextView>(R.id.input4)

        val name = input1.text.toString()
        val reserved_time = input2.text.toString()
        val director = input3.text.toString()
        val synopsis = input4.text.toString()
        val poster_image = posterImageUri.toString()

        addData(name, poster_image, director,synopsis,reserved_time)
    }

    //이미지는 보통 파일로 저장하고 파일의 결로만 DB에 저장
    fun savePosterToFile(drawable : Int) : Uri {
        //getDrawable함수를 호출하면 파라미터로 전달한 id를 이용해 객체 생성
        val drawable = ContextCompat.getDrawable(applicationContext, drawable)

        //contextWrapper 객체 생성 후 getDir 함수 호출하면 단말 내부의 저장소에 접근 가능
        val bitmap = (drawable as BitmapDrawable).bitmap

        //images 폴더를 참조하도록 한 수, file 객체 생성
        val wrapper = ContextWrapper(applicationContext)
        val imageFolder = wrapper.getDir("images", Context.MODE_PRIVATE)
        val file = File(imageFolder, "gg.jpg")

        //bitmap 객체의 메소드 호출하면서 이미지 파일 저장 후 파일 경로 Uri 객체로 반환
        try{
            val stream : OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }
        catch (e : IOException){
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)
    }

    fun addData(name : String, poster_image : String, director : String, synopsis: String, reserved_time : String){
        //*****Insert SQL 문 구현 *****//
        val sql = "insert into ${tableName}(name, poster_image, director, synopsis, reserved_time)"+
                "values"+
                "('$name','$poster_image','$director','$synopsis','$reserved_time')"

        if(database == null){
            println("데이터베이스를 먼저 오픈하세요\n")
            return
        }
        //값들을 array 객체로 전달해서 SQL문 실행
        database?.execSQL(sql)
        println("데이터 추가함\n")
    }

    fun loadMovie(){
        val movies = querydata()
        //데이터 전달 및 화면 전환
        val intent = Intent(this, ReservedActivity::class.java)
        intent.putExtra("movies",movies)
        startActivity(intent)
    }

    fun querydata() : ArrayList<ReservedMovie>?{
        //데이터를 조회하는 SELECT SQL 문 추가
        val sql = "SELECT _id,name,poster_image,director,synopsis,reserved_time FROM ${tableName}"

        if(database == null){
            println("데이터베이스를 먼저 오픈하세요.\n")
            return null
        }

        //조회한 column을 하나의 객체로 만들어 반환하기 위해 ReservedMovie(data class)사용
        val list = arrayListOf<ReservedMovie>()
        val cursor = database?.rawQuery(sql, null)

        //mobeToNext함수를 이용해 칼럼 값 조회
        if(cursor != null){
            for(index in 0 until cursor.count){
                cursor.moveToNext()
                val _id = cursor.getInt(0)
                val name = cursor.getString(1)
                val poster_image = cursor.getString(2)
                val director = cursor.getString(3)
                val synopsis = cursor.getString(4)
                val reserved_time = cursor.getString(5)

                println("레코드# ${index} : $_id, $name, $poster_image, $director, $synopsis, $reserved_time\n")

                val movie = ReservedMovie(_id,name,poster_image,director,synopsis,reserved_time)
                list.add(movie)
            }

            //SQL Table의 데이터가 없는 경우에도 "예약정보보기" 클릭 시 앱이 비정상 종료되지 않게끔 조치
            if(cursor.count == 0){
                val _id = null
                val name = "null"
                val poster_image = "null"
                val director = "null"
                val synopsis = "null"
                val reserved_time = "null"
                val movie = ReservedMovie(_id,name,poster_image,director,synopsis,reserved_time)
                list.add(movie)
            }
            cursor.close()
        }
        println("데이터 조회함\n")
        return list
    }

}
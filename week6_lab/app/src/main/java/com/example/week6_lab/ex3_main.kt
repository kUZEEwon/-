package com.example.week6_lab

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

val GALLERY= 1

class ex3_main : AppCompatActivity() {
   // private lateinit var selectedImage: Bitmap // 선택한 이미지를 저장할 변수
    private lateinit var bitmap: Bitmap // 전역 변수로 사용할 변수

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                100->{
                    var ImageData: Uri? =data?.data
                    try {
                        //selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                        bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                        //  val resize = resizeBitmap(bitmap , 300,300)
                        val pt: ImageView = findViewById(R.id.imageView)
                        pt.setImageBitmap(bitmap)


                    }
                    catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        }
        //bitmap1 = data!!.getParcelableExtra<Bitmap>("image")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex3)
        val btn_save : Button = findViewById(R.id.btn_save)
        var etName : EditText = findViewById(R.id.etName)
        var etAge : EditText = findViewById(R.id.etAge)
        var etNumber : EditText = findViewById(R.id.etNumber)
        var etAddress : EditText = findViewById(R.id.etAddress)
        var etEtc : EditText = findViewById(R.id.etEtc)
        var photo : ImageView = findViewById(R.id.imageView)
        photo.isClickable = true
        photo.setOnClickListener{
            val intent :Intent= Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 100)
        }



        btn_save.setOnClickListener {
            if (etName.text.isEmpty() || etAge.text.isEmpty() || etNumber.text.isEmpty() || etAddress.text.isEmpty() || etEtc.text.isEmpty()) {
                Toast.makeText(this, "모두 입력하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var name : String = etName.text.toString()
            var age : String = etAge.text.toString()
            var number : String = etNumber.text.toString()
            var address : String = etAddress.text.toString()
            var etc : String = etEtc.text.toString()

            val intent = Intent(this, ex3_sub::class.java)
            //val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("number", number)
            intent.putExtra("address", address)
            intent.putExtra("etc", etc)
            startActivity(intent) //intent 에 저장되어 있는 액티비티 쪽으로 이동한다.

        }

    }
private fun openGallery(){
    val intent:Intent=Intent(Intent.ACTION_GET_CONTENT)
    intent.setType("image/*")
    startActivityForResult(intent, 100)
}
    private fun resizeBitmap(bitmap: Bitmap, width: Int, height:Int):Bitmap{
        return Bitmap.createScaledBitmap(
            bitmap,
            height,
            width,
            false
        )
    }
}
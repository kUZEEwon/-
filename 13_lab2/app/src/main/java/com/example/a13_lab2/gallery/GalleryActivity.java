package com.example.a13_lab2.gallery;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a13_lab2.R;
import com.example.a13_lab2.tflite.Classifier;

import java.io.IOException;
import java.util.Locale;

public class GalleryActivity extends AppCompatActivity {
    public static final String TAG = "[IC]GalleryActivity";
    public static final int GALLERY_IMAGE_REQUEST_CODE = 1;

    Classifier cls;
    Bitmap bitmap = null;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button selectBtn = findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(v -> getImageFromGallery());

        Button classifyBtn = findViewById(R.id.classifyBtn);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        classifyBtn.setOnClickListener(v->{
            if(bitmap != null){
                Pair<Integer, Float> res = cls.classify(bitmap);
                String outStr = String.format(
                        Locale.ENGLISH,
                        "%d, %.0f%%",
                        res.first,
                        res.second*100.0f);
                textView.setText(outStr);
            }
        });

        cls = new Classifier(this);
        try{
            cls.init();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    //Device 저장소로부터 이미지를 불러오고 위한 Intent 설정 추가
    private void getImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //파일 불러오기 결과 유효성 체크
        if(resultCode == Activity.RESULT_OK && requestCode==GALLERY_IMAGE_REQUEST_CODE){
            if(data == null) return;

            Uri selectedImage = data.getData();

            //Android 버전에 따른 이미지 처리
            try{
                if(Build.VERSION.SDK_INT >= 29){
                    ImageDecoder.Source src = ImageDecoder.createSource(getContentResolver(),selectedImage);
                    bitmap = ImageDecoder.decodeBitmap(src).copy(Bitmap.Config.ARGB_8888,true);
                }
                else{
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                }
            }
            catch (IOException ioe){
                Log.e(TAG, "Failed to read Image",ioe);
            }
            if (bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    protected void onDestroy(){
        cls.finish();
        super.onDestroy();
    }
}

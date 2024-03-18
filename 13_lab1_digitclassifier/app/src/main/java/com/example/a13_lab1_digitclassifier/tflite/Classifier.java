package com.example.a13_lab1_digitclassifier.tflite;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Pair;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.Tensor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public class Classifier {
    private static final String MODEL_NAME = "keras_model_cnn.tflite";

    Context context;

    Interpreter interpreter = null;

    int modelInputWidth, modelInputHeight, modelInputChannel;

    int modelOutputClasses;

    public Classifier(Context context) { this.context = context;}

    public void init()throws IOException{
        ByteBuffer model = loadModelFile(MODEL_NAME);
        model.order(ByteOrder.nativeOrder());
        interpreter = new Interpreter(model);

        initModelShape();
    }
    private ByteBuffer loadModelFile(String modelName) throws IOException{
        AssetManager am = context.getAssets(); //Assets 리소스 접근이 가능한 객체 생성
        AssetFileDescriptor afd = am.openFd(modelName); //Assets 리소스 경로 획득

        //MappedByteBuffer 자료형으로 읽기
        FileInputStream fis = new FileInputStream(afd.getFileDescriptor());
        FileChannel fc = fis.getChannel();
        long startOffset = afd.getStartOffset();
        long declaredLength = afd.getDeclaredLength();

        return fc.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void initModelShape(){
        //Interpreter로부터 로드한 모델의 Input shape를 변수로 읽어옴
        Tensor inputTensor = interpreter.getInputTensor(0);
        int[] inputShape = inputTensor.shape();
        modelInputChannel = inputShape[0];
        modelInputWidth = inputShape[1];
        modelInputHeight = inputShape[2];

        //Interpreter로부터 로드한 모델의 Output shape를 변수로 읽어옴(class수)
        Tensor outputTensor = interpreter.getOutputTensor(0);
        int[] outputShape = outputTensor.shape();
        modelOutputClasses = outputShape[1];
    }

    /***** Input Image 전처리 코드 *****/
    //입력 이미지 크기 변환
    private Bitmap resizeBitmap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap, modelInputWidth, modelInputHeight,false);
    }
    //입력 이미지 채널 및 포멧 변환
    private ByteBuffer convertBitmapToGrayByteBuffer(Bitmap bitmap){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(bitmap.getByteCount());
        byteBuffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels,0, bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());

        for(int pixel : pixels){
            int r = pixel >> 16 & 0xFF;
            int g = pixel >> 8 & 0xFF;
            int b = pixel & 0xFF;

            float avgPixelValue = (r + g + b) / 3.0f;
            float normalizedPixelValue = avgPixelValue / 255.0f;

            byteBuffer.putFloat(normalizedPixelValue);
        }
        return byteBuffer;
    }

    public Pair<Integer, Float> classify(Bitmap image) {
        ByteBuffer buffer = convertBitmapToGrayByteBuffer(resizeBitmap(image)); //함수를 통한 데이터 전처리

        float[][] result = new float[1][modelOutputClasses]; //결과 저장을 위한 배열 선언

        interpreter.run(buffer, result); //interpreter의 run()을 활용한 TFLite Model 분류
        //최대값 추출
        return argmax(result[0]);
    }

    /***** 전처리 *****/
    //출력 데이터는 10개의 Output class중 가장 높은 확률값을 찾는 argmax함수 구현
    private Pair<Integer,Float> argmax(float[] array){
        int argmax = 0;
        float max = array[0];

        for(int i=1; i<array.length; i++){
            float f = array[i];
            if(f > max){
                argmax = i;
                max = f;
            }
        }
        return new Pair<>(argmax, max);
    }

    public void finish(){
        if(interpreter != null)
            interpreter.close();
    }
}

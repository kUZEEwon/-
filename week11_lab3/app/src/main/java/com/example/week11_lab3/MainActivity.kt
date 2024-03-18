package com.example.week11_lab3

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() ,OnMapReadyCallback{
    private lateinit var mMap:GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION)
        requirePermissions(permissions, 999)
    }
    fun startProcess(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    fun requirePermissions(permissions: Array<String>, requestCode: Int){
        //버전 관리 코드
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            permissionGranted(requestCode)
        }
        else{
            //권한 획득 여부 확인
            val isAllPermissionsGranted = permissions.all{
                checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            }
            if(isAllPermissionsGranted){
                permissionGranted(requestCode)
            }
            else{
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.all{ it == PackageManager.PERMISSION_GRANTED} ){
            permissionGranted(requestCode)
        }
        else{
            permissionDenied(requestCode)
        }
    }

    //권한이 있는 경우 - 구글 지도를 보여주는 코드 실행
    fun permissionGranted(requestCode: Int){
        startProcess()
    }

    //권한이 없는 경우 Toast Message 출력
    fun permissionDenied(requestCode: Int){
        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
    }

    //onMapReady()로부터 현재위치 획득
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        updateLocation()
    }


    //위도, 경도 값 아용하기
    @SuppressLint("MissingPermission")
    fun updateLocation(){
        val locationRequest = LocationRequest.create()
        //10초 간격으로 사용자의 위치 갱신
        locationRequest.run{
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000
        }

        //사용자의 위도, 경도 값 받기
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult : LocationResult?){
                locationResult?.let{
                    for(location in it.locations){
                        val latitude = location.latitude
                        val longitude = location.longitude

                        Log.d("kkang", "$latitude, $longitude")
                        setLastLocation(location)
                    }
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }


    fun setLastLocation(lastLocation : Location){
        val LATLNG = LatLng(lastLocation.latitude,lastLocation.longitude)
        //사용자의 위치 표시 Marker생성
        val markerOptions = MarkerOptions().position(LATLNG).title("Here!")


        //전달받은 위도, 경도가 나타내는 곳으로 전환하여 화면에 출력
        val cameraPosition = CameraPosition.Builder()
            .target(LATLNG)
            .zoom(15.0f)
            .build()

        mMap.clear()
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}
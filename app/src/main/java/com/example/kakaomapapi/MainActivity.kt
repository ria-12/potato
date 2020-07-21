package com.example.kakaomapapi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapView
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val permissionList = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION)
    private val PERMISSION_REQUEST_CODE = 1000
    private var permissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapView = MapView(this)
        val mapViewContainer = findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
    }

    private fun init() {
        if(android.os.Build.VERSION.SDK_INT >= 23) {
            checkPermission()
        }
    }

    private fun checkPermission(){
        val permissionNeedList = arrayListOf<String>()
        permissionList.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                permissionNeedList.add(it)
        }

        if (permissionNeedList.isNotEmpty()) {
            val permissionNeedArray = arrayOfNulls<String>(permissionNeedList.size)
            ActivityCompat.requestPermissions(this, permissionNeedArray, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == PERMISSION_REQUEST_CODE){
            for(i in permissions.indices){
               when(PackageManager.PERMISSION_GRANTED) {
                   grantResults[i] -> Timber.d("${permissions[i]} permission granted")
                   else -> Timber.d("${permissions[i]} permission denied")
               }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
package com.example.kakaomapapi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapView


class MainActivity : AppCompatActivity() {

    private val permissionList = listOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapView = MapView(this)
        val mapViewContainer = findViewById<ViewGroup>(R.id.map_view)
        mapViewContainer.addView(mapView)
    }

//    private fun checkPermission() {
//        if(ContextCompat.checkSelfPermission(this, permissionList) != PackageManager.PERMISSION_DENIED) {
//            //permission is not granted
//        }
//    }
}
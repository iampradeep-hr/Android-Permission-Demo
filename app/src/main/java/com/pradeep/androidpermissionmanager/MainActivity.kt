package com.pradeep.androidpermissionmanager

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.pradeep.androidpermissionmanager.databinding.ActivityMainBinding

const val TAG="mainactivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            requestPermission()
        }


    }

    private fun hasReadPermission()=
        ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED

    private fun hasLocationPermission()=
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocationPermission()=
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION)==PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        var permissonToRequest = mutableListOf<String>()
        if (!hasReadPermission()){
            permissonToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!hasLocationPermission()){
            permissonToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasBackgroundLocationPermission()){
            permissonToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (permissonToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissonToRequest.toTypedArray(),0)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==0){
            for (i in grantResults.indices){
                if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "onRequestPermissionsResult: ${permissions[i]}")
                }
            }
        }
    }


}
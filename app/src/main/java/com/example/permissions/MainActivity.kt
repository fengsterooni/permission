package com.example.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCameraPermission.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "You already have the permission for camera and GPS", Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                        ), CAMERA_AND_FINE_LOCATION_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted for camera", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,
                        "Oops, NO PERMISSION", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSSION_CODE = 1
        private const val FINE_LOCATION_PERMISSION_CODE = 2
        private const val CAMERA_AND_FINE_LOCATION_PERMISSION_CODE = 12
    }
}
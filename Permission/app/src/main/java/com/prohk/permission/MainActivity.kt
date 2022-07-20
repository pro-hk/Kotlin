package com.prohk.permission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.prohk.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // 권한 - 위험권한 : 개인정보.. 사용자에게 다시 물어봄

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            checkPermission()
        }
    }

    fun checkPermission(){
        val cameraPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        if(cameraPermission == PackageManager.PERMISSION_GRANTED){
            openCamera()
        } else {
            requestPermission()
        }
    }

    fun openCamera(){
        Toast.makeText(this,"카메라를 실행합니다",Toast.LENGTH_SHORT).show()
        // 카메라 실행
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    fun requestPermission(){
        // 실제 권한 요청
        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),99)
    }

    // 권한 요청 결과값
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            99 -> {
                if(grantResults.get(0)== PackageManager.PERMISSION_GRANTED){
                    openCamera()
                } else {
                    Toast.makeText(this,"권한을 승인하지 않으면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
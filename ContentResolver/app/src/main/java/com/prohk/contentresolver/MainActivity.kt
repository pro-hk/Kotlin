package com.prohk.contentresolver

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    val permissions = Manifest.permission.READ_EXTERNAL_STORAGE
    val REQ_READ = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isPermitted()) {
            startProcess()
        } else {
//            ActivityCompat.requestPermissions(this,permissions, REQ_READ)
        }
    }

    fun isPermitted():Boolean {
        /*if(ContextCompat.checkSelfPermission(this,permissions[0])) {

        }*/

        return false
    }

    fun startProcess() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) { // 1개 일 경우 if가 더 좋을 수 잇음
            REQ_READ -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startProcess()
                } else {
                    Toast.makeText(this,"권한 요청을 승인해야지만 앱을 실행할 수 있습니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
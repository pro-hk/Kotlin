package com.prohk.camera

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class BaseActivity: AppCompatActivity() {

    abstract fun permissionGranted(requestCode:Int)
    abstract fun permissionDenied(requestCode: Int)

    // 권한 검사
    fun requirePermissions(permissions:Array<String>, requestCode: Int) {
        // API 버전이 마시멜로 미만이면 권한처리가 필요없다
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        } else {
            // 권한이 없으면 권한 요청 -> 팝업
            ActivityCompat.requestPermissions(this,permissions,requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults.all {it==PackageManager.PERMISSION_GRANTED}) { // 결과값 : boolean
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
        /*for(result in grantResults){
            if(result == PackageManager.PERMISSION_GRANTED){

            }
        }*/
    }
}
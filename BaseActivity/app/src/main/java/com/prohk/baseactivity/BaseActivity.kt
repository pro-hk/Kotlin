package com.prohk.baseactivity

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

// 비지니스로직과 상관없는 코드들 분리해서 처리
abstract class BaseActivity: AppCompatActivity() { // abstract : 상속만 가능(인스턴스 불가 - 생성자 불가)
    abstract fun permissionGranted(requestCode: Int)
    abstract fun permissionDenied(requestCode: Int)

    // 1. 권한 검사
    fun requirePermissions(permissions:Array<String>,requestCode:Int){
        // Api 버전이 마시멜로 이하이면 권한처리가 필요없다
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        } else {
            // 2. 권한이 없을 시 요청 -> 팝업
            ActivityCompat.requestPermissions(this,permissions,requestCode)
        }
    }
    // 3. 결과처리
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
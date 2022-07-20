package com.prohk.baseactivity

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.prohk.baseactivity.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val PERM_CAMERA = arrayOf(Manifest.permission.CAMERA)
    val PERM_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    val REQ_STORAGE = 99
    val REQ_CAMERA = 100
    val TAKE_CAMERA = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 앱이 시작되면 스토리지 권한을 처리한다.
        requirePermissions(PERM_STORAGE,REQ_STORAGE)

        // 카메라 버튼이 클릭되면 권한처리 후 카메라를 오픈한다.
        binding.btnCamera.setOnClickListener {
            requirePermissions(PERM_CAMERA,REQ_CAMERA)
        }
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this,"스토리지 권한이 승인되었습니다",Toast.LENGTH_SHORT).show()
            }
            REQ_CAMERA -> {
                openCamera()
            }
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode){
            REQ_STORAGE -> {
                Toast.makeText(this,"스토리지 권한이 없으면 앱을 실행할 수 없습니다.",Toast.LENGTH_SHORT).show()
                finish() // 앱 종료
            }
            REQ_CAMERA -> {
                Toast.makeText(this,"카메라 권한이 거절되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, TAKE_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK){
            when(requestCode){
                TAKE_CAMERA -> {
                    // 카메라 촬영 결과를 처리
                }
            }
        }
    }
}
package com.prohk.camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.prohk.camera.databinding.ActivityMainBinding
import java.lang.Exception
import java.text.SimpleDateFormat

// 카메라 원본 이미지 가져오기
class MainActivity : BaseActivity() {

    val PERM_STORAGE = 9
    val PERM_CAMERA = 10

    val REQ_CAMERA = 11
    val REQ_GALLERY = 12

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 공용 저장소 권한이 있는지 확인
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),PERM_STORAGE)
    }

    fun initViews() {
        // 2. 카메라 요청시 권한을 먼저 체크하고 승인되었으면 카메라를 오픈한다
        binding.buttonCamera.setOnClickListener { 
            requirePermissions(arrayOf(Manifest.permission.CAMERA), PERM_CAMERA)
        }
        // 5. 갤러리 버튼이 클릭되면 갤러리를 연다
        binding.buttonGallery.setOnClickListener {
            openGallery()
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_GALLERY)
    }

    // 원본 이미지의 주소를 저장할 변수
    var realUri:Uri? = null

    // 3. 카메라에 찍은 사진을 저장하기 위한 uri를 넘겨준다
    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        createImageUri(newFileName(),"image/jpg")?.let{ uri ->
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT,realUri)
        }

        startActivityForResult(intent, REQ_CAMERA)
    }

    // 원본 이미지를 저장할 Uri를 MediaStore(DB)에 생성하는 메서드
    fun createImageUri(filename:String, mimeType:String):Uri? {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

     // 파일 이름을 생성하는 메서드
    fun newFileName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmsss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    // 원본 이미지를 불러오는 메서드
    fun loadBitmap(photoUri:Uri): Bitmap? {
        // var image:Bitmap? = null
        try{
            return if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
                val source = ImageDecoder.createSource(contentResolver, photoUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver,photoUri)
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(this,"공용 저장소 권한을 승인해야 앱을 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
                finish()
            }
            PERM_CAMERA -> {
                Toast.makeText(this,"카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 4. 카메라를 찍은 후에 호출된다.
    // 6. 갤러리에서 선택 후 호출된다
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                REQ_CAMERA -> {
                    /*val bitmap = data?.extras?.get("data") as Bitmap // 미리보기 이미지
                    binding.imagePreview.setImageBitmap(bitmap)*/
                    realUri?.let{ uri ->
                        val bitmap = loadBitmap(uri)
                        binding.imagePreview.setImageBitmap(bitmap)
                        realUri = null
                    }
                }
                REQ_GALLERY -> {
                    data?.data?.let { uri ->
                        binding.imagePreview.setImageURI(uri)
                    }
                }
            }
        }
    }
}
package com.prohk.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prohk.basiclayout.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 값 받기
        val param = intent.getStringExtra("param")
        Log.d("서브","넘겨받은 값 = $param")

        binding.btnReturn.setOnClickListener {
            Log.d("서브","버튼클릭")
            val intent = Intent()
            intent.putExtra("param2","돌려드립니다")
            setResult(RESULT_OK,intent)
            finish() // intent 닫아줘야함
        }
    }
}
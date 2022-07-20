package com.prohk.sharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.prohk.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object { 
        const val KEY_FIRST_OPEN = "key_first_open"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val shared = getSharedPreferences("파일명", Context.MODE_PRIVATE)

        val firstOpen = shared.getBoolean(KEY_FIRST_OPEN,false)
        //val firstStringOpen = shared.getString("keyFirstStringOpen","처음")
        if(firstOpen) {
            binding.hello.visibility = View.GONE
        }

        val editor = shared.edit() // 수정을 위한 에디터를 꺼냄
        //editor.putString("keyFirstStringOpen","두번째")
        editor.putBoolean(KEY_FIRST_OPEN,true)
        editor.commit()
    }
}
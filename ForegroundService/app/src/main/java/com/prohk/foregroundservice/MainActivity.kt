package com.prohk.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat

// 상단슬라이드 - notification에 표시
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun serviceStart(view: View) {
        Log.d("서비스","스타트클릭")
        val intent = Intent(this, Foreground::class.java)
        ContextCompat.startForegroundService(this,intent)
    }

    fun serviceStop(view: View) {
        Log.d("서비스","스탑클릭")
        val intent = Intent(this, Foreground::class.java)
        stopService(intent)
    }
}
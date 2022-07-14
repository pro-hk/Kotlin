package com.prohk.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prohk.log.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("TAG","Hiii")

        binding.buttonLog.setOnClickListener {
            val TAG = "ActivityMain"
            binding.txt.text = "hiii"
            Log.d(TAG,"log")
        }
    }
}
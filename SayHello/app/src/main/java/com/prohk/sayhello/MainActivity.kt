package com.prohk.sayhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prohk.sayhello.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSay.setOnClickListener {
            val text = when(binding.textView.text){
                "Hello World!" -> "Hello Kotlin!"
                else -> "Hello World!"
            }
            binding.textView.text = text
        }
    }
}
package com.prohk.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.prohk.timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var total = 0
    var started = false

    val handler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d", total/60) //01,02,
            val second = String.format("%02d", total%60)

            binding.textTimer.text = "$minute:$second"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            started = true
            thread(start=true) {
                while(started){
                    Thread.sleep(1000)
                    if(started){
                        total++
                        handler?.sendEmptyMessage(0)
                    }
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            if(started) {
                started = false
                total = 0
                binding.textTimer.text = "00:00"
            }
        }
    }
}
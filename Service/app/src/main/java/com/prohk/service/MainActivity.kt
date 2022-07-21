package com.prohk.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.prohk.service.databinding.ActivityMainBinding

// 서비스 - 화면이 필요없는 activity
// background로 동작하지 않음 - activity와 동시에 띄어놓고 동작 시 한쪽이 끝난 후 다른 한쪽이 실행됨
class MainActivity : AppCompatActivity() {
    lateinit var serviceIntent:Intent
    //val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(this, MyService::class.java)
    }

    fun serviceStart(view:View) {
        //val intent = Intent(this, MyService::class.java)
        serviceIntent.action = MyService.ACTION_CREATE
        startService(serviceIntent)
    }

    fun serviceStop(view: View) {
        //val intent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    var myService:MyService? = null
    var isService = false
    val connection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            isService = true
            val binder = p1 as MyService.MyBinder
            myService = binder.getService()
        }
        // unbind 때 호출 X -> 알 수 없이...
        override fun onServiceDisconnected(p0: ComponentName?) {
            isService = false
        }
    }

    fun serviceBind(view: View) {
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun serviceCommand() {
        myService?.create()
        myService?.delete()
    }

    fun serviceUnbind(view: View) {

    }
}
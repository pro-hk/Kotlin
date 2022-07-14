package com.prohk.syntaxfunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        functionName()
        functionParam("값")
        val PI = getPi()
        Log.d("함수","PI = $PI")
    }
    // 함수 사용 용도 : 코드를 분류하기 위해
    // 기본 함수
    fun functionName(){
        Log.d("함수","기본 함수 출력")
    }

    // 입력 값이 있는 함수
    fun functionParam(param1:String){
        Log.d("함수","param1 = $param1")
    }

    // 출력 값이 있는 함수
    fun getPi():Double {
        return 3.141592
    }
}
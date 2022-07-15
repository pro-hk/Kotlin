package com.prohk.syntaxnullsafety

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myName:String = "메시"
        var number:Int? = null // nullable
        var newVariable: Activity? = null

        Log.d("Null Test","문자열의 길이는 =${myName.length}")

        // number.plus(20)  // Null Pointer Exception 방지 -> null safety
        var result = number?.plus(20) // Safe Call -> null인 경우 코드가 실행되지 않음
        Log.d("Null Test","result = $result")
        var result1 = number?.plus(20) ?:10 // null일 경우 ?: 뒤에 값이 대체값
        Log.d("Null Test","result1 = $result1")
    }
}
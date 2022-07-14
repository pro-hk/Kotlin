package com.prohk.syntax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prohk.syntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val TAG = "MainActivity"
        // var : 재정의 가능 - 변수(밑줄)
        var myName = "prohk"
        Log.d(TAG, "my name=$myName")
        myName = "hk"
        Log.d(TAG, "my name=$myName")
        // val : 재정의 불가 - 상수(주로 대문자)
        val PI = 3.141592
        // PI = 10
        Log.d(TAG, "pi=$PI")

        //IF
        var myNumbers = "1,2,3,4,5,6"
        var thisWeekNumbers = "5,6,7,8,9,10"
        if(myNumbers == thisWeekNumbers){
            Log.d(TAG,"당첨되었습니다")
            binding.textLog.text = "당첨되었습니다."
        } else {
            Log.d(TAG,"당첨되지 않았습니다")
            binding.textLog.text = "당첨되지 않았습니다."
        }

        for(i in 1..10){ //자바 : for(int i=1;i<=10;i++
            Log.d(TAG,"현재 숫자는 ${i}입니다")
            // 글 추가 : append
            // \n : 엔터효과
            binding.textLog.append("\n현재 숫자는 ${i}입니다")
        }
    }
}
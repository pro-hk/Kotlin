package com.prohk.syntaxfor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intA = IntArray(10)
        intA[0] = 0
        var intArray = intArrayOf(0,10,20,30,40,50,60)

        // for(조건){ 코드블럭 }
        for(i in 1 until 10 step 3){ // until : 마지막 제외 & step : 점프...
            Log.d("반복문","index $i")
        }

        for(i in 10 downTo 1){ // downTo : 감소
            Log.d("반복문","index $i")
        }

        for(value in intArray){ // downTo : 감소
            Log.d("반복문","value = $value")
        }

        // while(반복할 조건){ 코드블럭 }
        var out = 0
        while (out < 3){
            Log.d("반복문","현재 아웃카운트는 $out")
            out++
        }

        // do(무조건 실행) while
        var out1 = 3
        do{
            // 무조건 실행
            Log.d("반복문","현재 아웃카운트는 $out1")
            out1++
        } while(out<3)

        // control loop - continue : 지나감 / break : 함수 종료
        for(i in 0..10){
            if(i == 5) continue
            Log.d("반복문","현재 숫자는 $i")
        }
    }
}
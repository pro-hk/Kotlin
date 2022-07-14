package com.prohk.syntaxcondition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var first = 300
        var second = 500
        var third = 270

        // 비교연산자 - <, >, <=, >=, ==, !=
        var result1 = first < 500
        Log.d("compare","첫번째 결과는 $result1")
        var result2 = second < 500
        Log.d("compare","두번째 결과는 $result2")

        // 논리연산자 - &&(교집합), ||(합집합)
        var logic1 = result1 && result2
        Log.d("compare","논리연산 && 결과는 $logic1")
        var logic2 = result1 || result2
        Log.d("compare", "논리연산 || 결과는 $logic2")
        
        // 부정연산자
        var logic3 = !result1
        Log.d("compare","부정연산 !첫번째 결과는 $logic1")

        // if(조건식 : 비교연산 + 논리연산){ scope } else if{ scope} else { scope }
        var month:Int = 4
        if(month > 9)      Log.d("if","가을 옷을 입습니다.")
        else if(month > 6) Log.d("if","해수욕장을 갑니다.")
        else if(month > 3) Log.d("if","소풍을 갑니다.")
        else               Log.d("if","집에 있습니다.")

        // when - 대부분 switch
        /*switch(month){
            case 6 :
            Log.d("switch","해수욕장을 갑니다.")
            case 7 :
            Log.d("switch","asdfdasf")
        }*/
        when(month){
            1,2,3   -> Log.d("when","집에 있습니다.")
            in 4..6 -> Log.d("when","소풍을 갑니다.")
            in 7..9 -> Log.d("when","해수욕장을 갑니다.")
            else    -> Log.d("when","가을 옷을 입습니다.")
        }
    }
}
package com.prohk.syntaxarray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 배열 - 사이즈 지정 / index : 0 시작
        // Int -> IntArray
        var intArr:IntArray = IntArray(10)
        intArr[0] = 10
        intArr[1] = 20
        intArr[2] = 30
        intArr[3] = 40
        intArr[4] = 50
        intArr[5] = 60
        intArr[9] = 100
        //intArr[10] = 110
        Log.d("배열","9번 인덱스의 값은 ${intArr[9]}")

        var weekArray = CharArray(7)
        weekArray[0]='월'
        weekArray[1]='화'
        weekArray[2]='수'
        weekArray[3]='목'
        weekArray[4]='금'
        weekArray[5]='토'
        weekArray[6]='일'

        // 컬렉션 - 동적        Generic(제네릭) -- 타입 지정
        var mutableList = mutableListOf<Int>() // 초기화
        mutableList.add(5) // index : 0
        mutableList.add(10) // index : 1
        mutableList.add(75) // index : 2
        mutableList.add(36) // index : 3
        mutableList.add(66) // index : 4

        Log.d("컬렉션","3번 인덱스의 값은 ${mutableList.get(3)}")

        // 맵( key, value)                Generic
        var mutableMap = mutableMapOf<String, String>()
        mutableMap.put("변수1","값1")
        mutableMap.put("변수2","값2")

        Log.d("컬렉션","맵 변수 1의 값은 ${mutableMap.get("변수1")}")
    }
}
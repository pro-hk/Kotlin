package com.prohk.kotlinsyntaxvar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prohk.kotlinsyntaxvar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // onCreate : 시작점
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // type을 특정화하지 않으면 시스템이 알아서 적용
        var variable:String = "홍길동"

        // 코틀린 기본타입
        // 실수형
        var doubleValue:Double = 3.14
        var floatValue:Float = 3.14f
        // 정수형
        var intValue:Int = 12345678
        var longValue:Long = 2_147_483_647
        var shortValue:Short = 123
        var byteValue:Byte = 127
        // 문자형
        var charValue:Char = 'a'
        var stringValue:String = "문자형 길이에 상관없이 값을 입력할 수 있다 = 크기를 특정할 수 없다"
        // boolean형
        var booleanValue:Boolean = true

        // 상수 - 대문자
        val VALVALUE:Boolean = true
        // VALVALUE = false

        var helloWorld = "안녕 세상아!"  // 낙타
        var hello_world = "안녕 세상아!" // 뱀
    }

}
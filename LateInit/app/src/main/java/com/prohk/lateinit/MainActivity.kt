package com.prohk.lateinit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    // 지연초기화 (선행 : class, Null safety) - 메모리 낭비하지 않기 위해서
    // lateinit - 코드상에서 어딘가 넣을것이다 -- 기본형(Int, Long, Double, Float 등)에 사용 못함
    lateinit var person:Person // 객체
    
    //  val 변수명 by lazy { 변수에 들어갈 클래스생성자 또는 값 }
    val age by lazy{ Person() }

//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        person = Person() // 인스턴스
    }
}

class Person {
    var name = ""
    var age = 0
    var address = ""
    var tel = ""
}
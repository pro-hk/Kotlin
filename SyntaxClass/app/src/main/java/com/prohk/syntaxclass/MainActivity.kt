package com.prohk.syntaxclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // class 사용
        // 1. 초기화(init 실행) -> 인스턴스(메모리에 올라가 있는 클래스)
        var cls1 = className()
        var cls2 = className()
        cls1.variable = "hi"

        // 2. companion object로 만들기 - static
        Log.d("클래스","cls1의 변수 값은 ${cls1.variable}")
        Log.d("클래스","cls2의 변수 값은 ${cls2.variable}")

        val parent = Parent()
        parent.showHouse()

        val child = Child()
        child.showMoney()
        child.showHouse()

        var son = Son()
        val result1 = son.getNumber()
        val result2 = son.getNumber("hi")
        Log.d("클래스","result1 = $result1, result2 = $result2")
    }
}

// class : 변수(property) + 함수(method)
class className{
    /*init{
       클래스를 초기화하면 호출 -> 메모리에 올라간다
    }*/
    var variable:String = ""  // 프로퍼티

    fun functionName(){       // 메서드
        var variable_local = ""
    }
}
class Log {
    companion object{
        var variable = "난 누구"
        fun d(tag:String, msg:String){
            print("$tag : $msg")
        }
    }
}

// 상속하는 이유
// 1. 기존의 작성된 코드를 재활용하기 위해서 
// 2. 코드를 재활용 -> 체계적으로 계층구조로 사용하기 위해서 
open class Parent {
    var money = 500000000
    open var house = "강남 200평 아파트"

    open fun showHouse(){
        Log.d("클래스","parent house=$house")
    }
}

class Child:Parent() { // 초기화
    // 상속받으면 부모클래스의 프로퍼티와 매서드를 내 것처럼 사용할 수 있다
    override var house = "강남 10평 오피스텔"

    fun showMoney(){
        Log.d("클래스","money=$money")
    }

    override fun showHouse(){
        Log.d("클래스","child house=$house")
    }
}

// overload : 파라미터..
class Son{
    fun getNumber():Int{
        return 1
    }

    fun getNumber(param:String):Int{
        return 2
    }
}
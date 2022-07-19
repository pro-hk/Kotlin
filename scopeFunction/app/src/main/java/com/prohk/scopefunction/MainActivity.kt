package com.prohk.scopefunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.prohk.scopefunction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding){
            button.setOnClickListener{}
            imageView.setImageLevel(50)
            textView.text = "hi"
        }
        studyRun()

    }

    // 스코프함수 : 지연초기화와 함께 safe call 남용 막아줌
    // run, let, apply, also
    // with (사용법이 다름)

    // 1.run
    fun studyRun(){
        val phones = mutableListOf("010-1234-5678","010-2345-6789","010-3456-7890")
        val list = mutableListOf(1,2,3,4,5,6,7,8,9)
        val names = mutableListOf("Scott","Kelly","Michael")

        val seoulPeople = SeoulPeople()

        val resultRun = seoulPeople.persons.run {
            add(Person("Scott","010-1234-5678",20))
            add(Person("Kelly","010-2345-6789",21))
            add(Person("Michael","010-3456-7890",22))
            size
        }
        Log.d("스코프함수","resultRun = $resultRun")

        val resultLet = seoulPeople.persons.let { persons -> // 내가 지정 가능 - 지정안하면 it
            persons.add(Person("Scott","010-1234-5678",20))
            persons.add(Person("Scott","010-1234-5678",20))
            persons.add(Person("Scott","010-1234-5678",20))
            persons.size
        }
        Log.d("스코프함수","resultLet = $resultLet")

        val resultApply = seoulPeople.persons.apply {
            add(Person("Scott","010-1234-5678",20))
            add(Person("Kelly","010-2345-6789",21))
            add(Person("Michael","010-3456-7890",22))
            11
        }
        Log.d("스코프함수","resultApply = $resultApply")

        val resultAlso = seoulPeople.persons.also {
            it.add(Person("Scott","010-1234-5678",19))
            it.add(Person("Kelly","010-1234-5678",20))
            it.add(Person("Michael","010-1234-5678",21))
            12
        }
        Log.d("스코프함수","resultAlso = $resultAlso")
    }
}

class SeoulPeople {
    var persons = mutableListOf<Person>()
    init {
        /*persons.add(Person("Scott","010-1234-5678",20))
        persons.add(Person("Kelly","010-2345-6789",21))
        persons.add(Person("Michael","010-3456-7890",22))*/
    }
}

data class Person (
    var name:String = "",
    var phone:String = "",
    var age:Int = 20
)
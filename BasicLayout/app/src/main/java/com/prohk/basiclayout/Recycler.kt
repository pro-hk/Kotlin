package com.prohk.basiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Recycler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
    }

    fun loadData():MutableList<Memo> {
        val data:MutableList<Memo> = mutableListOf()

        for(no in 1..100){
            val title = "이것이 안드로이드다 ${no}"
            val date = System.currentTimeMillis() // 현재시간
            val memo = Memo(no,title,date)
            data.add(memo)
        }
        return data
    }
}
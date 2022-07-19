package com.prohk.basiclayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prohk.basiclayout.databinding.ActivityRecyclerBinding
import com.prohk.basiclayout.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class Recycler : AppCompatActivity() {
    val binding by lazy { ActivityRecyclerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 데이터를 불러옴
        val data = loadData()
        // 2. Adapter 생성
        val customAdapter = CustomAdapter(data)
        // 3. 화면의 RecyclerView와 연결
        binding.recyclerView.adapter = customAdapter
        // 4. 레이아웃 매니저 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun loadData():MutableList<Memo> {
        val memoList = mutableListOf<Memo>()
        for(no in 1..100){
            val title = "이것이 안드로이드다 $no"
            val date = System.currentTimeMillis()
            val memo = Memo(no,title,date)
            memoList.add(memo)
        }
        return memoList
    }
}


package com.prohk.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.prohk.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val DB_NAME = "sqlite.sql"
    val DB_VERSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val helper = SqliteHelper(this,DB_NAME, DB_VERSION)

        /*val memo = Memo(1,"내용",122313255)
        helper.insertMemo(memo)*/

        val adapter = RecyclerAdapter()
        val memos = helper.selectMemo()
        adapter.listData.addAll(memos)

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.buttonSave.setOnClickListener {
            val content = binding.editMemo.text.toString()
            if(content.isNotEmpty()){
                val memo = Memo(null,content,System.currentTimeMillis())
                helper.insertMemo(memo)
                // 기존 작성글 삭제
                binding.editMemo.setText("")
                // 목록 갱신
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
            }
        }
    }
}
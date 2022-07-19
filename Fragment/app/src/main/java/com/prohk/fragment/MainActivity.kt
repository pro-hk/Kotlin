package com.prohk.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prohk.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // 화면전환

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val listFragment by lazy { ListFragment() }
    val detailFragment by lazy { DetailFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()

        binding.btnSend.setOnClickListener {
            listFragment.setValue("값 전달하기")
        }
    }

    fun goDetail(){
        // val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout,detailFragment)
        transaction.addToBackStack("detail")  // 뒤로가기 - stack
        transaction.commit()
    }

    fun goBack(){
        onBackPressed()
    }

    fun setFragment(){
        val bundle = Bundle()
        bundle.putString("key1","List Fragment")
        bundle.putInt("key2",20220719)
        listFragment.arguments = bundle

        // 1. 사용할 프래그먼트 생성
//        val listFragment = ListFragment()
        // 2. 트랜잭션 생성
        val transaction = supportFragmentManager.beginTransaction()
        // 3. 트랜잭션을 통해 프래그먼트 삽입
        transaction.add(R.id.frameLayout, listFragment)
        transaction.commit()
    }

}
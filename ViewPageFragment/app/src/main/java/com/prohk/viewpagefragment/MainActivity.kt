package com.prohk.viewpagefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.prohk.viewpagefragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // viewPager : 스와이퍼.. && 탭레이아웃

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 페이지 데이터를 로드
        val list = listOf(FragmentA(),FragmentB(),FragmentC(),FragmentD())
        // 2. 아답터를 생성
        val pagerAdapter = FragmentPagerAdapter(list,this)
        with(binding){
        // 3. 아답터와 뷰페이저 연결
        viewPager.adapter = pagerAdapter

        // 4. 탭 메뉴의 갯수만큼 제목을 목록으로 생성
        val titles = listOf("A","B","C","D")
        // 5. 탭레이아웃과 뷰페이저 연결
        TabLayoutMediator(tabLayout,viewPager){ tab,position ->
            tab.text = titles.get(position)
        }.attach() // attach() 실행해야됨
        }

        }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
                                                        :FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList.get(position)
}
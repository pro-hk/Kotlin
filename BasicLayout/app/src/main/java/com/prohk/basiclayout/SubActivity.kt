package com.prohk.basiclayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.prohk.basiclayout.databinding.ActivitySubBinding
import kotlin.concurrent.thread

class SubActivity : AppCompatActivity() {

    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 값 받기
        val param = intent.getStringExtra("param")
        Log.d("서브","넘겨받은 값 = $param")
        val param1 = intent.getIntExtra("param1",0) // defaultValue 필요
        Log.d("서브","파람1 = ${param1}")

        binding.btnReturn.setOnClickListener {
            val intent = Intent()
            val message = binding.editMsg.text.toString()
            intent.putExtra("param2",message)
            setResult(Activity.RESULT_OK,intent)
            finish() // activity 닫아줘야함
        }

        // 토글, 스위치
        with(binding){
            toggleButton.setOnCheckedChangeListener { compoundButton, b ->
//                if(b) textToggle.text = "toggle on" else textToggle.text = "toggle off"
                textToggle.text = if(b) "toggle on" else "toggle off"
            }
            switchButton.setOnCheckedChangeListener { compoundButton, b ->
                textSwitch.text = if(b) "switch on" else "switch off"
            }
        }
        
        // 프로그래스바 - 로딩 돌아가는거..
        with(binding){
            // <- 메인 스레드
            showProgress(true)
            thread(start=true){ // <- 서브 스레드
                Thread.sleep(3000)  // 3초 후 실행
                // 화면에 영향을 미치는 코드는 메인스레드로 다시 보내야한다
                runOnUiThread {
                    showProgress(false)
                    //progressLayout.visibility = View.GONE // 가려짐
                }
            } // <- 서브스레드
            // <- 메인 스레드
        }

        // 시크바 - 볼륨 조절 컨트롤러, 음악플레이어 구간 찾아가기
        with(binding){
            // 최대 범위 늘리고 싶을 경우 : xml파일에서 seekBar의 max값 조절
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                // p1 : 현재 구간, p2 : 사람이 터치하는 유무
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if(p2){ // 사람이 터치로 동작시킬 때만 코드 실행
                        seekText.text = "$p1"
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })
        }

        // 레이팅바
        with(binding){
            // numStars : 별 갯수 변경(기본 5)
            // rating : 기본 체크갯수
            // stepSize : 디테일한 체크 가능(기본 0.5)
            // fl : 별 갯수, b : 사람이 터치하는 유무
            ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
                if(b){
                    ratingText.text = "$fl"
                }
            }
        }
    }
    fun showProgress(show:Boolean){
        binding.progressLayout.visibility = if(show) View.VISIBLE else View.GONE
    }
}
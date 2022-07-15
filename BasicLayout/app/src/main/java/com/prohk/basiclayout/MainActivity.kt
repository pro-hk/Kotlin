package com.prohk.basiclayout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.prohk.basiclayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*//listener
        val listener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Log.d("리스너","클릭되었습니다.")
            }
        }
        binding.button41.setOnClickListener(listener)*/
        /*binding.button41.setOnClickListener{ // 함수가 1개인 경우 가능
            Log.d("리스너","클릭되었습니다.")
        }*/
        // res\values\strings 에 저장 -- 다국어 가능

        /*val listener = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val current_text = p0.toString()
                if(current_text.length >= 8){
                    Log.d("리스너","$current_text")
                }
            }

        }
        binding.editText.addTextChangedListener(listener)*/

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId -> // checkedId : Integer
            when(checkedId){
                R.id.radioApple -> Log.d("리스너","사과가 선택되었습니다") // R에서 id 값을 가져옴
                R.id.radioBanana -> Log.d("리스너","바나나가 선택되었습니다")
                R.id.radioOrange -> Log.d("리스너","오렌지가 선택되었습니다")
            }
        }

        // Activity 이동
        binding.button41.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            // subActivity로 값 전달
            intent.putExtra("param","실제 값")
            // 전달만 할 경우 startActivity
            startActivityForResult(intent,REQ_SUB)
        }

        // spinner - 콤보박스
        val data = listOf("- 선택하세요 -","월","화","수","목","금","토","일")

        //containe에 데이터를 세팅 - 데이터 모양을 바꿀 수 있음
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedValue = data[p2]
                binding.textView.text = selectedValue
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        binding.button.setOnClickListener {
            val intent = Intent(this,RecyclerView::class.java)
            startActivity(intent)
        }

    }
    val REQ_SUB = 99
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQ_SUB -> {
                    val returnValue = data?.getStringExtra("param2") ?: "none"
                    Log.d("서브","돌려받은 값 = $returnValue")
                }
            }
        }
    }
}
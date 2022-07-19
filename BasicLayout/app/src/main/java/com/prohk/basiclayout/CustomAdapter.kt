package com.prohk.basiclayout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prohk.basiclayout.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class CustomAdapter(val listData:MutableList<Memo>): RecyclerView.Adapter<CustomAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 1. 사용할 데이터를 꺼내고
        val memo = listData.get(position)
        // 2. 홀더에 데이터를 전달
        holder.setMemo(memo)
    }

    class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        // 3. 데이터를 화면에 출력한다
        fun setMemo(memo:Memo){
            with(binding){
                textNo.text = "${memo.no}"
                textTitle.text = memo.title
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate = sdf.format(memo.timestamp)
                textDate.text = formattedDate
            }
        }
    }

    override fun getItemCount() = listData.size
}

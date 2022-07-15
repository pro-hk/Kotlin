package com.prohk.basiclayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prohk.basiclayout.databinding.ItemBinding
import java.text.SimpleDateFormat

// () : 상속, 없으면 인터페이스
class CustomAdapter : RecyclerView.Adapter<Holder>() {

    val listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return Holder(itemView)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }
    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setMemo(memo: Memo) {
        itemView.textNo.text = "${memo.no}"
        itemView.textTitle.text = memo.title

        val sdf = SimpleDateFormat("yyyy/MM/dd")
        sdf.format(memo.timestamp)
        itemView.textDate.text = sdf
    }

}
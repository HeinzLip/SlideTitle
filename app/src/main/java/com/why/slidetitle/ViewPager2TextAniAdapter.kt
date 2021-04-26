package com.why.slidetitle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @ClassName: ViewPager2TextAniAdapter
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/2/21 4:39 PM
 */
class ViewPager2TextAniAdapter constructor(private var datas: MutableList<String> = mutableListOf()): RecyclerView.Adapter<ViewPager2TextAniViewHolder>() {

    fun updateDatas(newDatas: MutableList<String>){
        this.datas = newDatas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2TextAniViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return ViewPager2TextAniViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPager2TextAniViewHolder, position: Int) {
        holder.bindView(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}
class ViewPager2TextAniViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view){

    fun bindView(text: String){
        view.findViewById<TextView>(R.id.tv_text).text = text
    }
}
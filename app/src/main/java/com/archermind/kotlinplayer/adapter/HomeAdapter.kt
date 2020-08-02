package com.archermind.kotlinplayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.archermind.kotlinplayer.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private val list = ArrayList<HomeItemBean>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {
        val data = list.get(position)
        val homeItemView = holder?.itemView as HomeItemView
        homeItemView.satData(data)
    }

    fun updataList(updatalist: List<HomeItemBean>?) {
        this.list.clear()
        this.list.addAll(updatalist!!)
        notifyDataSetChanged()

    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}
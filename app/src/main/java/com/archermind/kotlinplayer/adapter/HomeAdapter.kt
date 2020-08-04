package com.archermind.kotlinplayer.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.archermind.kotlinplayer.widget.HomeItemView
import com.archermind.kotlinplayer.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list = ArrayList<HomeItemBean>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        if (viewType == 1) {
            return HomeHolder(HomeItemView(parent?.context))
        } else {
            return HomeHolder(LoadMoreView(parent?.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            //最后一条
            return 1
        } else {
            //普通条目
            return 0
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {
        //如果是最后一条 不需要刷新view
        if (position == list.size) return
        val data = list.get(position)
        val homeItemView = holder?.itemView as HomeItemView
        homeItemView.satData(data)
    }

    fun updataList(list: List<HomeItemBean>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMore(list: List<HomeItemBean>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}
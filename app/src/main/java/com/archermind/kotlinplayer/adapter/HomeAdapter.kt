package com.archermind.kotlinplayer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.widget.HomeItemView
import com.archermind.kotlinplayer.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refeshItemView(itemview: HomeItemView, data: HomeItemBean) {
        itemview.satData(data)
    }
}

package com.archermind.kotlinplayer.adapter

import android.content.Context
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.widget.MvItemView
import com.itheima.player.model.bean.VideosBean

class MvlistAdapter : BaseListAdapter<VideosBean, MvItemView>() {
    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }

    override fun refeshItemView(itemview: MvItemView, data: VideosBean) {
        itemview.setData(data)
    }

}

package com.archermind.kotlinplayer.adapter

import android.content.Context
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean

class YueDanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

    override fun refeshItemView(itemview: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemview.setData(data)
    }
}
package com.archermind.kotlinplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.archermind.kotlinplayer.model.AudioBean
import com.archermind.kotlinplayer.widget.PopListItemView

class PopAdapter(var list: List<AudioBean>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var itemView: PopListItemView
        if (p1 == null) {
            itemView = PopListItemView(p2?.context)
        } else {
            itemView = p1 as PopListItemView
        }
        itemView.setData(list.get(p0))
        return itemView
    }

    override fun getItem(p0: Int): Any {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}

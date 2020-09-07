package com.archermind.kotlinplayer.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import com.archermind.kotlinplayer.model.AudioBean
import com.archermind.kotlinplayer.widget.VbangItemView

class VbangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    /**
     * 创建条目view
     */
    override fun newView(context: Context?, cursor: Cursor?, viewgroup: ViewGroup?): View {
        return VbangItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val vbangItemView = view as VbangItemView
        val audioBean = AudioBean.getAudioBean(cursor)
        vbangItemView.setData(audioBean)
    }
}
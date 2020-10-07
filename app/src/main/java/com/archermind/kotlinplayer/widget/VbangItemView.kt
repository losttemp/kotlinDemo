package com.archermind.kotlinplayer.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.model.AudioBean
import kotlinx.android.synthetic.main.item_vbang.view.*


class VbangItemView(context: Context?) : RelativeLayout(context) {

    init {
        View.inflate(context, R.layout.item_vbang, this)
    }

    fun setData(audioBean: AudioBean) {
        title.text = audioBean.display_name
        artist.text = audioBean.artist
        size.text = Formatter.formatFileSize(context, audioBean.size)
    }
}

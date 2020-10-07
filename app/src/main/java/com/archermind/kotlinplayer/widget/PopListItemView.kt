package com.archermind.kotlinplayer.widget

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.model.AudioBean
import kotlinx.android.synthetic.main.item_pop.view.*

class PopListItemView(context: Context?) : RelativeLayout(context) {

    init {
        View.inflate(context, R.layout.item_pop, this)
    }

    fun setData(data: AudioBean) {
        //title
        title.text = data.display_name
        //artist
        artist.text = data.artist
    }

}

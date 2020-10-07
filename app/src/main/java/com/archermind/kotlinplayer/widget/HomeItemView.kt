package com.archermind.kotlinplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.archermind.kotlinplayer.R
import com.itheima.player.model.bean.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeItemView(context: Context?) : RelativeLayout(context) {
    fun satData(data: HomeItemBean) {
        //歌曲名称
        title.setText(data.title)
        desc.setText(data.description)
        //背景图片
        Picasso.with(context).load(data.posterPic).into(bg)
    }


    init {
        View.inflate(context, R.layout.item_home, this)
    }

}
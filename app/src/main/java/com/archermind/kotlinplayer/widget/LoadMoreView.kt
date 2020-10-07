package com.archermind.kotlinplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.archermind.kotlinplayer.R


/**
 * ClassName:LoadMoreView
 * Description:
 */
class LoadMoreView(context: Context?) : RelativeLayout(context) {


    init {
        View.inflate(context, R.layout.view_loadmore, this)
    }
}
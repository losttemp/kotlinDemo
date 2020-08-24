package com.archermind.kotlinplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.archermind.kotlinplayer.base.BaseFragment

class DefaultFragment : BaseFragment() {
    override fun initview(): View? {
        val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text = javaClass.simpleName
        return tv
    }

}

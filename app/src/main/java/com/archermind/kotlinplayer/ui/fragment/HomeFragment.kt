package com.archermind.kotlinplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.archermind.kotlinplayer.base.BaseFragment


class HomeFragment : BaseFragment() {
    override fun initview(): View? {
        val textview = TextView(this.activity)
        textview.setTextColor(Color.RED)
        textview.setText("home")
        textview.gravity = Gravity.CENTER
        textview.setTextSize(50f)
        return textview
        // return activity.layoutInflater.inflate(R.layout.fragment_home, null)
    }

}
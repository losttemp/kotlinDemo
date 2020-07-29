package com.archermind.kotlinplayer.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseFragment


class VbangFragment : BaseFragment() {
    override fun initview(): View? {
        val textview = TextView(this.activity)
        textview.setTextColor(Color.RED)
        textview.setText("vbang")
        textview.gravity = Gravity.CENTER
        textview.setTextSize(50f)
        return textview
        //return activity.layoutInflater.inflate(R.layout.fragment_vbang, null)
    }

}
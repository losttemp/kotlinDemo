package com.archermind.kotlinplayer.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.ui.activity.SettingActivity


interface ToolbarManage {
    val toolbar: Toolbar
    fun initMainToolBar() {
        toolbar.setTitle("科特林影音")
        toolbar.inflateMenu(R.menu.main)

        toolbar.setOnMenuItemClickListener {
            toolbar.context.startActivity(Intent(toolbar.context, SettingActivity::class.java))
            true
        }
    }
}
package com.archermind.kotlinplayer.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import com.archermind.kotlinplayer.R


interface ToolbarManage {
    val toolbar: Toolbar
    fun initMainToolBar() {
        toolbar.setTitle("黑马影音")
        toolbar.inflateMenu(R.menu.main)

        toolbar.setOnMenuItemClickListener {
            //toolbar.context.startActivity(Intent)
            true }
    }
}
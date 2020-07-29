package com.archermind.kotlinplayer.ui.activity

import android.os.Build
import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.util.ToolbarManage
import org.jetbrains.anko.find

class SettingActivity : BaseActivity(), ToolbarManage {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getlayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initMainToolBar()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val sp = PreferenceManager.getDefaultSharedPreferences(this)
            val boolean = sp.getBoolean("push", false)
            println("push=$boolean")
        }
    }
}
package com.archermind.kotlinplayer.ui.activity

import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        super.initListener()
        var beginTransaction = supportFragmentManager.beginTransaction()

    }

}

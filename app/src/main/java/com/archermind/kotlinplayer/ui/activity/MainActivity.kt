package com.archermind.kotlinplayer.ui.activity

import android.support.v7.widget.Toolbar
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.util.ToolbarManage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolbarManage {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        super.initListener()
        //var beginTransaction = supportFragmentManager.beginTransaction()

    }


}

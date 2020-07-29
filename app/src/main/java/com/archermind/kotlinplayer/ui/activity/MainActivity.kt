package com.archermind.kotlinplayer.ui.activity

import android.support.v7.widget.Toolbar
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.util.FragmentUtil
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
        bottomBar.setOnTabSelectListener {
            //bug :这里必须是val 如果是var会空指针
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.container, FragmentUtil.fragmentutil.getFragment(it), it.toString())
            beginTransaction.commit()
        }


    }

    override fun initData() {
        initMainToolBar()
    }

}

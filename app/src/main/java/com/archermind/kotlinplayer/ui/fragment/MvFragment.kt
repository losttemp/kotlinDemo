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
import com.archermind.kotlinplayer.adapter.MvPagerAdapter
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.presenter.impl.MvPresenterImpl
import com.archermind.kotlinplayer.view.MvView
import com.itheima.player.model.bean.MvAreaBean
import kotlinx.android.synthetic.main.fragment_mv.*


class MvFragment : BaseFragment(), MvView {
    val present by lazy { MvPresenterImpl(this) }
    override fun initview(): View? {
        return activity.layoutInflater.inflate(R.layout.fragment_mv, null)
    }

    override fun initData() {
        present.loadDatas()
    }

    override fun initListener() {

    }

    /*请求返回结果 失敗
    view层具体实现
    * */
    override fun onError(message: String?) {
        showToast("加载数据失败")
    }

    /*请求返回结果 成功
    view层具体实现
    * */
    override fun loadSucess(result: List<MvAreaBean>) {
        val mvPagerAdapter = MvPagerAdapter(context, result, childFragmentManager)
        viewPager.adapter = mvPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
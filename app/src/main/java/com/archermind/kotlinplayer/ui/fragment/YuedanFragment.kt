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
import com.archermind.kotlinplayer.adapter.YueDanAdapter
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.base.BaseListFragment
import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.presenter.impl.YueDanPresenterImpl
import com.archermind.kotlinplayer.view.YueDanView
import com.archermind.kotlinplayer.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean


class YuedanFragment : BaseListFragment<YueDanBean, YueDanItemView, YueDanBean.PlayListsBean>() {


    override fun getChildPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getChildAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun getList(response: YueDanBean?): List<YueDanBean.PlayListsBean>? {
        return response?.playLists
    }

}
package com.archermind.kotlinplayer.ui.fragment

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.adapter.HomeAdapter
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.base.BaseListFragment
import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.presenter.impl.HomePresenterImpl
import com.archermind.kotlinplayer.view.HomeView
import com.archermind.kotlinplayer.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * RESPONSE:服务器返回的结果集合
 * ITEMVIEW：每个条目的自定义View
 * ITEMBEAN：每个条目的bean
 */
class HomeFragment : BaseListFragment<List<HomeItemBean>, HomeItemView, HomeItemBean>() {


    override fun getChildPresenter(): BaseListPresenter {
        //因为BaseListFragment实现了BaseView,所以直接用this
        //HomePresenterImpl构造中实现
        return HomePresenterImpl(this)
    }

    override fun getChildAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }

}
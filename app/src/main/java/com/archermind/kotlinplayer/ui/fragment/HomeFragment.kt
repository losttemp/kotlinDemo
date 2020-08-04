package com.archermind.kotlinplayer.ui.fragment

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.adapter.HomeAdapter
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.presenter.impl.HomePresenterImpl
import com.archermind.kotlinplayer.util.URLProviderUtils
import com.archermind.kotlinplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.util.ThreadUtil
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException


class HomeFragment : BaseFragment(), HomeView {
    val homeAdapter by lazy { HomeAdapter() }

    //Presenter持有view引用，所有逻辑在impl中
    val homePresenter by lazy { HomePresenterImpl(this) }
    override fun initview(): View? {

        return activity?.layoutInflater?.inflate(R.layout.fragment_home, null)
    }

    override fun initListener() {
        recycleView.adapter = homeAdapter
        recycleView.layoutManager = LinearLayoutManager(context)
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GRAY)
        //下拉刷新
        refreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                homePresenter.loadDatas()
            }

        })
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView?.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        //val manager: LinearLayoutManager = layoutManager
                        val LastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                        if (LastVisiblePosition == homeAdapter.itemCount - 1) {
                            homePresenter.loadMore(homeAdapter.itemCount - 1)
                        }
                    }
                }
            }
        })
    }


    override fun initData() {
        homePresenter.loadDatas()
    }

    override fun onError(message: String?) {
        showToast("加载失败")
    }

    override fun loadSucess(list: List<HomeItemBean>?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        homeAdapter.updataList(list)
    }

    override fun loadMore(list: List<HomeItemBean>?) {
        //加载更多
        homeAdapter.loadMore(list)
    }


}
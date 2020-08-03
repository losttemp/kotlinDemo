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
import com.archermind.kotlinplayer.util.URLProviderUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.util.ThreadUtil
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException


class HomeFragment : BaseFragment() {
    val homeAdapter = HomeAdapter()
    override fun initview(): View? {

        return activity?.layoutInflater?.inflate(R.layout.fragment_home, null)
    }

    override fun initListener() {
        recycleView.layoutManager = LinearLayoutManager(context)
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GRAY)
        //下拉刷新
        refreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                initDatas()
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
                            loadMore(homeAdapter.itemCount - 1)
                        }
                    }
                }
            }
        })
    }

    private fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset, 20)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(path)
                .get()
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast("获取数据失败")

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                Log.d("result", "result=" + result)
                val gson = Gson()
                //接口失效
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        //加载更多
                        homeAdapter.loadMore(list)
                    }

                })
            }

        })
    }


    override fun initData() {

        recycleView.adapter = homeAdapter
        initDatas()
    }

    private fun initDatas() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(path)
                .get()
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast("获取数据失败")

            }

            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                Log.d("result", "result=" + result)
                val gson = Gson()
                //接口失效
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                //刷新列表
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        homeAdapter.updataList(list)
                    }

                })
            }

        })
    }
}
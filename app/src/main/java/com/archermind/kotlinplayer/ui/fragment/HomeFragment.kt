package com.archermind.kotlinplayer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
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
    }

    override fun initData() {

        recycleView.adapter = homeAdapter
        initDatas()
    }

    private fun initDatas() {
        val path = URLProviderUtils.getHomeUrl(0, 20)
        Log.d("path===", path)
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
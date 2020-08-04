package com.archermind.kotlinplayer.view

import com.itheima.player.model.bean.HomeItemBean

interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSucess(list: List<HomeItemBean>?)

    /**
     * 加载更多成功
     */
    fun loadMore(list: List<HomeItemBean>?)
}
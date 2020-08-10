package com.archermind.kotlinplayer.view

import com.itheima.player.model.bean.MvAreaBean

interface MvView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSucess(result: List<MvAreaBean>)
}
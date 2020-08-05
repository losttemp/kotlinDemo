package com.archermind.kotlinplayer.base

interface BaseView<RESPONSE> {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSucess(response: RESPONSE)

    /**
     * 加载更多成功
     */
    fun loadMore(response: RESPONSE)
}
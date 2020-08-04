package com.archermind.kotlinplayer.presenter

interface HomePresenter {
    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }

    fun loadDatas()
    fun loadMore(offset: Int)

    //解绑presenter和view
    fun destoryView()
}
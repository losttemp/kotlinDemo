package com.archermind.kotlinplayer.presenter.impl

import android.util.Log
import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.base.BaseView
import com.archermind.kotlinplayer.net.HomeRequest
import com.archermind.kotlinplayer.net.ResponseHandler
import com.archermind.kotlinplayer.presenter.HomePresenter
import com.archermind.kotlinplayer.view.HomeView
import com.itheima.player.model.bean.HomeItemBean

class HomePresenterImpl(var homeView: BaseView<List<HomeItemBean>>?) : HomePresenter, ResponseHandler<List<HomeItemBean>> {
    override fun loadDatas() {
        Log.d("TAG", "loadDatas====")
        HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).exute()
    }

    override fun loadMore(offset: Int) {
        HomeRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).exute()
    }

    override fun destoryView() {
        if (homeView != null) {
            homeView = null
        }
    }

    override fun onError(type: Int, msg: String) {
        homeView?.onError(msg)
    }

    override fun onSucess(type: Int, response: List<HomeItemBean>) {
        when (type) {
            //初始化或者刷新数据
            BaseListPresenter.TYPE_INIT_OR_REFRESH -> homeView?.loadSucess(response)
            //加载更多
            BaseListPresenter.TYPE_LOAD_MORE -> homeView?.loadMore(response)
        }
    }
}
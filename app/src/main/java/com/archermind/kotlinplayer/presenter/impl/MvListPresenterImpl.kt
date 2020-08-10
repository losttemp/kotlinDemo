package com.archermind.kotlinplayer.presenter.impl

import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.net.MvListRequest
import com.archermind.kotlinplayer.net.ResponseHandler
import com.archermind.kotlinplayer.presenter.MvListPresenter
import com.archermind.kotlinplayer.view.MvListView
import com.itheima.player.model.bean.MvPagerBean

class MvListPresenterImpl(val aera: String, var mvListView: MvListView?) : MvListPresenter, ResponseHandler<MvPagerBean> {
    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, aera, 0, this).exute()
    }

    override fun loadMore(offset: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE, aera, offset, this).exute()
    }

    override fun destoryView() {
        if (mvListView != null) {
            mvListView = null
        }
    }

    override fun onError(type: Int, msg: String) {
        mvListView?.onError(msg)
    }

    override fun onSucess(type: Int, response: MvPagerBean) {
        when (type) {
            BaseListPresenter.TYPE_INIT_OR_REFRESH -> mvListView?.loadSucess(response)
            BaseListPresenter.TYPE_LOAD_MORE -> mvListView?.loadMore(response)
        }
    }
}
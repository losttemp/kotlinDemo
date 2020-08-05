package com.archermind.kotlinplayer.presenter.impl

import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.base.BaseView
import com.archermind.kotlinplayer.net.ResponseHandler
import com.archermind.kotlinplayer.net.YueDanRequest
import com.archermind.kotlinplayer.presenter.YueDanPresenter
import com.archermind.kotlinplayer.view.YueDanView
import com.itheima.player.model.bean.YueDanBean

class YueDanPresenterImpl(var yueDanView: BaseView<YueDanBean>?) : YueDanPresenter, ResponseHandler<YueDanBean> {
    override fun loadDatas() {
        YueDanRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH, 0, this).exute()
    }

    override fun loadMore(offset: Int) {
        YueDanRequest(BaseListPresenter.TYPE_LOAD_MORE, offset, this).exute()
    }

    override fun destoryView() {
        if (yueDanView != null) {
            yueDanView = null
        }
    }

    override fun onError(type: Int, msg: String) {
        yueDanView?.onError(msg)
    }


    override fun onSucess(type: Int, response: YueDanBean) {
        when (type) {
            BaseListPresenter.TYPE_INIT_OR_REFRESH -> yueDanView?.loadSucess(response)
            BaseListPresenter.TYPE_LOAD_MORE -> yueDanView?.loadMore(response)
        }
    }
}
package com.archermind.kotlinplayer.presenter.impl

import com.archermind.kotlinplayer.net.MvAeraRequest
import com.archermind.kotlinplayer.net.ResponseHandler
import com.archermind.kotlinplayer.presenter.MvPresenter
import com.archermind.kotlinplayer.view.MvView
import com.itheima.player.model.bean.MvAreaBean

class MvPresenterImpl(var mvView: MvView) : MvPresenter, ResponseHandler<List<MvAreaBean>> {
    /**
     * 加载区域数据
     */
    override fun loadDatas() {
        MvAeraRequest(this).exute()
    }

    //网络请求的回调 失败，Presenter持有view层引用，由view层回调回去
    override fun onError(type: Int, msg: String) {
        mvView.onError(msg)
    }

    override fun onSucess(type: Int, response: List<MvAreaBean>) {
        mvView.loadSucess(response)
    }


}
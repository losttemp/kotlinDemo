package com.archermind.kotlinplayer.ui.fragment

import com.archermind.kotlinplayer.adapter.MvlistAdapter
import com.archermind.kotlinplayer.base.BaseListAdapter
import com.archermind.kotlinplayer.base.BaseListFragment
import com.archermind.kotlinplayer.base.BaseListPresenter
import com.archermind.kotlinplayer.model.VidoPlayerBean
import com.archermind.kotlinplayer.presenter.impl.MvListPresenterImpl
import com.archermind.kotlinplayer.ui.activity.IjkVideoPlayerActivity
import com.archermind.kotlinplayer.ui.activity.JiecaoVideoPlayerActivity
import com.archermind.kotlinplayer.view.MvListView
import com.archermind.kotlinplayer.widget.MvItemView
import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import org.jetbrains.anko.support.v4.startActivity

class MvPagerFragment : BaseListFragment<MvPagerBean, MvItemView, VideosBean>(), MvListView {
    var keys: String? = null
    override fun init() {
        keys = arguments.getString("args")
    }

    override fun getList(response: MvPagerBean?): List<VideosBean>? {
        return response?.videos
    }

    override fun getChildPresenter(): BaseListPresenter {
        return MvListPresenterImpl(keys!!, this)
    }

    override fun getChildAdapter(): BaseListAdapter<VideosBean, MvItemView> {
        return MvlistAdapter()
    }

    override fun initListener() {
        adapter.setOnItemClickListener {
            val vidoPlayerBean = VidoPlayerBean(it.id, it.title, it.url)
            //JiecaoVideoPlayerActivity
            startActivity<IjkVideoPlayerActivity>("item" to vidoPlayerBean)
        }
    }
}
package com.archermind.kotlinplayer.base

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.archermind.kotlinplayer.R
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * ClassName:BaseListFragment
 * Description:所有具有下拉刷新和上拉加载更多列表界面的基类
 * 基类抽取
 * HomeView->BaseView
 * Presenter->BaseListPresenter
 * Adapter->BaseListAdapter
 */
abstract class BaseListFragment<RESPONSE, ITEMVIEW : View, ITEMBEAN> : BaseFragment(), BaseView<RESPONSE> {
    val adapter by lazy { getChildAdapter() }


    //Presenter持有view引用，所有逻辑在impl中
    val presenter by lazy { getChildPresenter() }


    override fun initListener() {
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GRAY)
        //下拉刷新
        refreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                presenter.loadDatas()
            }

        })
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView?.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        //val manager: LinearLayoutManager = layoutManager
                        val LastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                        if (LastVisiblePosition == adapter.itemCount - 1) {
                            presenter.loadMore(adapter.itemCount - 1)
                        }
                    }
                }
            }
        })
    }


    override fun initData() {
        presenter.loadDatas()
    }

    override fun onError(message: String?) {
        //隐藏刷新控件
        try {
            refreshLayout.isRefreshing = false
            showToast("加载失败")
        } catch (e: Exception) {
        }
    }


    override fun initview(): View? {
        return activity?.layoutInflater?.inflate(R.layout.fragment_list, null)
    }


    override fun loadSucess(response: RESPONSE) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updataList(getList(response))
    }

    override fun loadMore(response: RESPONSE) {
        //加载更多
        adapter.loadMore(getList(response))
    }

    /**
     * 从返回结果中获取列表数据集合
     */
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?

    /**
     * 获取presenter
     */
    abstract fun getChildPresenter(): BaseListPresenter

    /**
     * 获取适配器adapter
     */
    abstract fun getChildAdapter(): BaseListAdapter<ITEMBEAN, ITEMVIEW>
}
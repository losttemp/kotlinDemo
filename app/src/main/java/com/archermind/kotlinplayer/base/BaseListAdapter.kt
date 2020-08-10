package com.archermind.kotlinplayer.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.archermind.kotlinplayer.widget.LoadMoreView

abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {
    private var list = ArrayList<ITEMBEAN>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseListHolder {
        if (viewType == 1) {
            return BaseListHolder(getItemView(parent?.context))
        } else {
            return BaseListHolder(LoadMoreView(parent?.context))
        }

    }


    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            //最后一条
            return 1
        } else {
            //普通条目
            return 0
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseListHolder?, position: Int) {
        //如果是最后一条 不需要刷新view
        if (position == list.size) return
        val data = list.get(position)
        val itemview = holder?.itemView as ITEMVIEW
        refeshItemView(itemview, data)
        itemview.setOnClickListener {
            itemlistener?.let {
                it(data)
            }
        }
    }

    //定义函数类型变量
    var itemlistener: ((itemBean: ITEMBEAN) -> Unit)? = null
    abstract fun getItemView(context: Context?): ITEMVIEW
    abstract fun refeshItemView(itemview: ITEMVIEW, data: ITEMBEAN)

    fun updataList(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMore(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickListener(itemlistener: (itembean: ITEMBEAN) -> Unit) {
        this.itemlistener = itemlistener
    }

    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}
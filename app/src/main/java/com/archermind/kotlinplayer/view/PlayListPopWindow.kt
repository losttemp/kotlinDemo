package com.archermind.kotlinplayer.view

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.archermind.kotlinplayer.R

class PlayListPopWindow(context: Context, adapter: BaseAdapter, listener: AdapterView.OnItemClickListener, val window: Window) : PopupWindow() {
    //记录当前应用程序窗体透明度
    var windowAlpha: Float = 0f

    init {
        windowAlpha = window.attributes.alpha
        //设置布局
        val view = LayoutInflater.from(context).inflate(R.layout.pop_playlist, null, false)
        //获取Listview
        val listView = view.findViewById<ListView>(R.id.listView)
        //适配器
        listView.adapter = adapter
        //设置条目点击事件
        listView.setOnItemClickListener(listener)
        contentView = view
        //设置宽度和高度
        width = ViewGroup.LayoutParams.MATCH_PARENT
        //设置高度为屏幕高度3/5
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        val windowH = point.y
        height = (windowH * 3) / 5
        //设置获取焦点
        isFocusable = true
        //外部点击
        isOutsideTouchable = true
        setBackgroundDrawable(ColorDrawable())
        animationStyle = R.style.pop
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        //popwindow已经显示
        val attributes = window.attributes
        attributes.alpha = 0.3f
        window.attributes = attributes
    }

    override fun dismiss() {
        super.dismiss()
        val attributes = window.attributes
        attributes.alpha = windowAlpha
        window.attributes = attributes
    }
}
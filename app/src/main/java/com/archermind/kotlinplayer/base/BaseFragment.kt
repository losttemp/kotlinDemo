package com.archermind.kotlinplayer.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

abstract class BaseFragment : Fragment(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        debug { "test" }
    }

    open protected fun init() {

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initview()
    }

    abstract fun initview(): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
    }

    open protected fun initListener() {

    }

    open protected fun initData() {

    }

    open fun showToast(msg: String) {
        context?.runOnUiThread { toast(msg) }
    }


}
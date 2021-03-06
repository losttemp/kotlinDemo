package com.archermind.kotlinplayer.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.archermind.kotlinplayer.ui.activity.MainActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutId())
        initListener()
        initData()
    }

    open protected fun initData() {

    }

    open protected fun initListener() {

    }

    abstract fun getlayoutId(): Int

    protected fun myToast(msg: String) {
        runOnUiThread {
            toast(msg)
        }

    }

    inline fun <reified T : BaseActivity> startActivityAndFinsh() {
        startActivity<T>()
        finish()
    }
}
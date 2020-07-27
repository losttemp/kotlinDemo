package com.archermind.kotlinplayer.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    override fun getlayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(image_splash).scaleX(1.0f)
                .scaleY(1.0f).setDuration(2000).setListener(this)
    }

    override fun onAnimationEnd(view: View?) {
        startActivity<MainActivity>()
        finish()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }
}
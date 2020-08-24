package com.archermind.kotlinplayer.ui.activity

import android.support.v4.view.ViewPager
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.adapter.ViewPagerAdapter
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.model.VidoPlayerBean
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_jiechao.*

class JiecaoVideoPlayerActivity : BaseActivity() {

    override fun getlayoutId(): Int {
        return R.layout.activity_video_jiechao
    }

    override fun initData() {
        val data = intent.data
        if (data == null) {
            //获取传递的数据
            val VidoPlayerBean = intent.getParcelableExtra<VidoPlayerBean>("item")
            //应用内响应视频播放
            videoView.setUp(VidoPlayerBean.url,
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, VidoPlayerBean.title)
        } else {
            if (data.toString().startsWith("http")) {
                //应用外网络视频请求
                videoView.setUp(data.toString(),
                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            } else {
                //应用外本地视频请求
                videoView.setUp(data.path,
                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
            }
        }
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

    override fun initListener() {
        //适配vierpager
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        rg.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb1 -> viewPager.setCurrentItem(0)
                R.id.rb2 -> viewPager.setCurrentItem(1)
                R.id.rb3 -> viewPager.setCurrentItem(2)
            }
        }
        viewPager.addOnPageChangeListener(listener)
    }

    /**
     *  类似于InterfaceA interface=new InterfaceA(){
    复写的方法
    }
     */

    val listener = object : ViewPager.OnPageChangeListener {
        /**
         * 滑动状态改变的回调
         */
        override fun onPageScrollStateChanged(state: Int) {

        }

        /**
         * 滑动回调
         */
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        /**
         * 选中状态改变回调
         */
        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> rg.check(R.id.rb1)
                1 -> rg.check(R.id.rb2)
                2 -> rg.check(R.id.rb3)
            }
        }

    }
}

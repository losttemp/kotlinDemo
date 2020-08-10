package com.archermind.kotlinplayer.ui.activity

import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.model.VidoPlayerBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*

class IjkVideoPlayerActivity : BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_ijk
    }

    override fun initData() {
//获取传递的数据
        val vidoPlayerBean = intent.getParcelableExtra<VidoPlayerBean>("item")
        videoView.setVideoPath(vidoPlayerBean.url)//异步准备
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
        videoView.release(true)
    }
}
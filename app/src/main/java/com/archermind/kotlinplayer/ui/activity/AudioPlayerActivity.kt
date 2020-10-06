package com.archermind.kotlinplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.annotation.MainThread
import android.view.View
import android.widget.SeekBar
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.model.AudioBean
import com.archermind.kotlinplayer.services.AudioService
import com.archermind.kotlinplayer.services.Iservice
import com.archermind.kotlinplayer.util.StringsUtil
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*

class AudioPlayerActivity : BaseActivity(), View.OnClickListener {
    val MSG_PROGRESS: Int = 11
    var drawable: AnimationDrawable? = null
    var duration: Int = 0
    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_PROGRESS -> startupdataProgress()
            }
        }
    }

    override fun getlayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnection() }
    override fun initData() {
        //注册EventBus
        EventBus.getDefault().register(this)
        val intent = intent
        intent.setClass(this, AudioService::class.java)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        startService(intent)
    }

    var iservice: Iservice? = null
    override fun initListener() {
        //播放状态切换
        state.setOnClickListener(this)
        //返回键
        back.setOnClickListener { finish() }
        //播放模式
        mode.setOnClickListener(this)
        //上一曲
        pre.setOnClickListener(this)
        //下一曲
        next.setOnClickListener(this)
        //播放列表
        playlist.setOnClickListener(this)
        //进度条变化监听
        progress_sk.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            /**
             * 进度变化回调
             * p1:改变之后的进度
             * p2:true 通过用户手指拖动改变进度  false代表通过代码方式改变进度
             */
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                //判断是否用户操作
                if (!p2) return
                //更新播放进度
                iservice?.seeKTo(p1)
                //更新界面进度
                updateProgress(p1)
            }

            //手指触摸回调
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            //手指离开回调
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    @MainThread
    fun onEventMainThread(itembean: AudioBean) {
        audio_title.text = itembean.display_name
        artist.text = itembean.artist
        //更新播放状态按钮
        updatePlayStateBtn()
        //动画播放
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //获取总进度
        duration = iservice?.getDuration() ?: 0
        //进度条设置最大值
        progress_sk.max = duration
        //更新播放进度
        startupdataProgress()
        //更新播放模式图标
        updateplayModeBtn()
    }


    //开始更新进度
    private fun startupdataProgress() {
        //获取当前进度
        val process: Int = iservice?.getProgress() ?: 0
        //更新进度数据
        updateProgress(process)
        //定时获取进度
        handler.sendEmptyMessage(MSG_PROGRESS)
    }

    /**
     * 根据播放模式修改播放模式图标
     */
    private fun updateplayModeBtn() {
        iservice?.let {
            val modeI: Int = it.getplayMode()
            when (modeI) {
                AudioService.MODE_ALL -> mode.setImageResource(R.drawable.selector_btn_playmode_order)
                AudioService.MODE_SINGLE -> mode.setImageResource(R.drawable.selector_btn_playmode_single)
                AudioService.MODE_RANDOM -> mode.setImageResource(R.drawable.selector_btn_playmode_random)
            }
        }
    }

    private fun updatePlayStateBtn() {
        val isplaying = iservice?.isPlaying()
        isplaying?.let {
            if (it) {
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
                //开始播放动画
                drawable?.start()
                //开始更新进度
                handler.sendEmptyMessage(MSG_PROGRESS)
            } else {
                //暂停
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                handler.removeMessages(MSG_PROGRESS)
            }
        }

    }

    /**
     *根据当前进度数据更新界面
     */
    private fun updateProgress(p1: Int) {
        //更新进度数值
        progress.text = StringsUtil.parseDuration(p1)
        //更新进度条
        progress_sk.setProgress(p1)
    }

    inner class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        /**
         * service连接时
         */
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            iservice = p1 as Iservice
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
        //反注册EventBus
        EventBus.getDefault().unregister(this)
        //清空handler发送的所有消息
        handler.removeCallbacksAndMessages(null)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            //更新播放状态
            R.id.state -> updatePlayState()
            //更新播放模式
            R.id.mode -> updateplayMode()
            //上一曲
            R.id.pre -> iservice?.playPre()
            //下一曲
            R.id.next -> iservice?.playNext()
            //显示播放列表
            R.id.playlist -> showPlayList()
        }
    }

    /**
     * 显示播放列表
     */
    private fun showPlayList() {
        //TODO:播放列表
        val list = iservice?.getPlayList()

    }

    /**
     * 更新播放模式
     */
    private fun updateplayMode() {
        //修改service中的Mode
        iservice?.updatePlayMode()
        //修改界面模式图标
        updateplayModeBtn()
    }

    /**
     * 更新播放状态
     */
    private fun updatePlayState() {
        //更新播放状态
        iservice?.updatePlayState()
        updatePlayStateBtn()
    }
}



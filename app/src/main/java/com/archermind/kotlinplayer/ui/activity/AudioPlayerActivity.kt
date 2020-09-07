package com.archermind.kotlinplayer.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseActivity
import com.archermind.kotlinplayer.services.AudioService

class AudioPlayerActivity : BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnection() }
    override fun initData() {
        val intent = intent
        intent.setClass(this, AudioService::class.java)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        startService(intent)
    }

    inner class AudioConnection : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }
}



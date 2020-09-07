package com.archermind.kotlinplayer.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.archermind.kotlinplayer.model.AudioBean
import java.util.*

class AudioService : Service() {
    private val TAG: String = "AudioService"
    private var list: ArrayList<AudioBean>? = null
    private val binder by lazy { AudioBinder() }
    private var mediaPlayer: MediaPlayer? = null
    private var pos: Int = -1
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            pos = it.getIntExtra("position", -1)
            //获取集合以及position
            list = it.getParcelableArrayListExtra<AudioBean>("list")
        }
        Log.d(TAG, "intent=$intent-----" + "pos=" + pos + "list=" + list)
        binder.playItem()
        return START_NOT_STICKY
    }


    inner class AudioBinder : Binder(), MediaPlayer.OnPreparedListener {
        fun playItem() {
            //如果mediaplayer已经存在就先释放掉
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setDataSource(list?.get(pos)?.data)
                it.prepareAsync()

            }
        }

        override fun onPrepared(p0: MediaPlayer?) {
            mediaPlayer?.start()
        }
    }
}



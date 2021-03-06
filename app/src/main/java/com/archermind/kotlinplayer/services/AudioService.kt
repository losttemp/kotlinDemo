package com.archermind.kotlinplayer.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.model.AudioBean
import com.archermind.kotlinplayer.ui.activity.AudioPlayerActivity
import com.archermind.kotlinplayer.ui.activity.MainActivity
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_setting.view.*
import java.util.*


class AudioService : Service() {
    private val TAG: String = "AudioService"
    private var list: ArrayList<AudioBean>? = null
    private var manager: NotificationManager? = null
    private var mediaPlayer: MediaPlayer? = null
    private var notification: Notification? = null
    private var position: Int = -2// 正在播放的position
    val FROM_PRE = 1
    val FROM_NEXT = 2
    val FROM_STATSE = 3
    val FROM_CONTENT = 4

    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    var mode = MODE_ALL  //默认播放模式
    private val binder by lazy { AudioBinder() }
    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        mode = sp.getInt("mode", 1)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val from = intent?.getIntExtra("from", -1)
        when (from) {
            FROM_PRE -> {
                binder.playPre()
            }
            FROM_NEXT -> {
                binder.playNext()
            }
            FROM_CONTENT -> {
                binder.notifiUpdateUi()
            }
            FROM_STATSE -> {
                binder.updatePlayState()
            }
            else -> {
                val pos = intent?.getIntExtra("position", -1) ?: -1
                if (pos != position) { //想要播放的条目和正在播放的条目不是同一首
                    position = pos
                    //获取集合以及position
                    list = intent?.getParcelableArrayListExtra<AudioBean>("list")

                    Log.d(TAG, "intent=$intent-----" + "pos=" + position + "list=" + list)
                    binder.playItem()
                } else {
                    //主動通知界面更新
                    binder.notifiUpdateUi()
                }

            }
        }

        return START_NOT_STICKY
    }


    inner class AudioBinder : Iservice, Binder(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

        fun playItem() {
            //如果mediaplayer已经存在就先释放掉
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                //开始播放的回调
                it.setOnPreparedListener(this)
                //播放结束的回调
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }

        /**
         * 播放开始
         */
        override fun onPrepared(p0: MediaPlayer?) {
            //播放音乐
            start()
            //通知界面更新
            notifiUpdateUi()
            //显示通知
            showNotification()
        }

        /**
         * 显示通知
         */
        private fun showNotification() {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification = getNotification()
            manager?.notify(1, notification)
        }

        /**
         * 创建notification
         */
        private fun getNotification(): Notification? {
            //创建NotificationChannel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mnotification = NotificationCompat.Builder(this@AudioService, "channel_name")
                        .setTicker("正在播放歌曲${list?.get(position)?.display_name}")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //自定义通知view
                        .setCustomContentView(getRemoteViews())
                        .setWhen(System.currentTimeMillis())
                        .setOngoing(true)//设置不能滑动删除通知
                        .setContentIntent(getPendingIntent())//通知栏主体点击事件
                        .build()
                var channel = NotificationChannel("channel_name", packageName, NotificationManager.IMPORTANCE_HIGH)
                manager?.createNotificationChannel(channel)
                return mnotification
            }
            return null

        }

        /**
         * 通知栏主体点击事件
         */
        private fun getPendingIntent(): PendingIntent? {
            val intentM = Intent(this@AudioService, MainActivity::class.java)
            val intentA = Intent(this@AudioService, AudioPlayerActivity::class.java)
            intentA.putExtra("from", FROM_CONTENT)
            val intents = arrayOf(intentM, intentA)
            val pendingIntent = PendingIntent.getActivities(this@AudioService, 1, intents, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        private fun getRemoteViews(): RemoteViews? {
            val remoteViews = RemoteViews(packageName, R.layout.notification)
            remoteViews.setTextViewText(R.id.title, list?.get(position)?.display_name)
            remoteViews.setTextViewText(R.id.artist, list?.get(position)?.artist)
            return remoteViews
        }

        /**
         * 播放上一曲
         */
        override fun playPre() {
            list?.let {
                //获取要播放的歌曲position
                when (mode) {
                    MODE_RANDOM -> list.let { position = Random().nextInt() - 1 }
                    else -> {
                        if (position == 0) {
                            position = it.size - 1
                        } else {
                            position--
                        }
                    }
                }
                playItem()
            }
        }

        //播放下一曲
        override fun playNext() {
            list?.let {
                when (mode) {
                    MODE_RANDOM -> position = Random().nextInt() - 1
                    else -> position = (position + 1) % it.size
                }
            }
            playItem()
        }

        //更新播放状态
        override fun updatePlayState() {
            //获取当前播放状态
            val isplaying = isPlaying()
            //切换播放状态
            isplaying?.let {
                if (it) {
                    //当前是播放的状态就暂停
                    pause()
                } else {
                    //反之
                    start()
                }
            }
        }


        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        //跳转到当前进度播放
        override fun seeKTo(p1: Int) {
            mediaPlayer?.seekTo(p1)
        }

        //获取总进度
        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0
        }

        //获取当前播放进度
        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }

        /**
         * 获取播放模式
         */
        override fun getplayMode(): Int {
            return mode
        }

        /**
         * 修改播放模式
         */
        override fun updatePlayMode() {
            when (mode) {
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL
            }
            //保存播放模式
            sp.edit().putInt("mode", mode).commit()
        }

        /**
         * 获取播放集合
         */
        override fun getPlayList(): List<AudioBean>? {
            return list
        }

        override fun playPosition(p2: Int) {
            this@AudioService.position = p2
            playItem()
        }


        fun notifiUpdateUi() {
            //发送端
            EventBus.getDefault().post(list?.get(position))
        }

        //开始
        private fun start() {
            mediaPlayer?.start()
            EventBus.getDefault().post(list?.get(position))
        }

        //暂停
        private fun pause() {
            mediaPlayer?.pause()
            EventBus.getDefault().post(list?.get(position))
        }

        //歌曲播放完成之後回調
        override fun onCompletion(mediaplayer: MediaPlayer?) {
            //自动播放下一曲
            autoplayNext()
        }

        //根据播放模式自动播放下一期
        private fun autoplayNext() {
            when (mode) {
                MODE_ALL -> {
                    list?.let { position = (position + 1) % it.size }

                }
                MODE_RANDOM -> list?.let { position = Random().nextInt(it.size) }
            }
            playItem()
        }
    }


}



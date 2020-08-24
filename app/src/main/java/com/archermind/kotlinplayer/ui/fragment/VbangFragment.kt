package com.archermind.kotlinplayer.ui.fragment

import android.content.AsyncQueryHandler
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.Button
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.util.CursorUtil


class VbangFragment : BaseFragment() {
    override fun initview(): View? {

        return activity.layoutInflater.inflate(R.layout.fragment_vbang, null)
    }

    override fun initData() {
        loadSongs()
    }

    private fun loadSongs() {
        val Resolver = context.contentResolver
        val handler = object : AsyncQueryHandler(Resolver) {
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
//打印数据
                               CursorUtil.logCursor(cursor)
            }
        }
        handler.startQuery(0, null, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST),
                null, null, null
        )
    }
}
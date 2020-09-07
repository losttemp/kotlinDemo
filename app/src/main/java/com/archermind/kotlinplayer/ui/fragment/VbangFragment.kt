package com.archermind.kotlinplayer.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.adapter.VbangAdapter
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.model.AudioBean
import com.archermind.kotlinplayer.ui.activity.AudioPlayerActivity
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton
import java.util.ArrayList


class VbangFragment : BaseFragment() {
    override fun initview(): View? {

        return activity.layoutInflater.inflate(R.layout.fragment_vbang, null)
    }

    override fun initData() {
        handlePermission()
        //loadSongs()
    }

    private fun handlePermission() {
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        //查看是否有权限
        val checkSelfPermission = ActivityCompat.checkSelfPermission(context, permission)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
            //已经获取
            loadSongs()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                //需要弹出
                alert("我们只会访问音乐文件,不会访问隐私照片", "温馨提示") {
                    yesButton { myRequestPermission() }
                    noButton { }
                }.show()
            } else {
                //不需要弹出
                myRequestPermission()
            }
        }
    }

    private fun myRequestPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSongs()
        }
    }

    private fun loadSongs() {
        val Resolver = context.contentResolver
        val handler = object : AsyncQueryHandler(Resolver) {
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
//打印数据
                //CursorUtil.logCursor(cursor)
                (cookie as VbangAdapter).swapCursor(cursor)
            }
        }
        handler.startQuery(0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST),
                null, null, null
        )
    }

    val adapter by lazy { VbangAdapter(context, null) }
    override fun initListener() {
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val cursor = adapter.getItem(i) as Cursor
            val list: ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)
            startActivity<AudioPlayerActivity>("list" to list, "position" to i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.changeCursor(null)
    }
}
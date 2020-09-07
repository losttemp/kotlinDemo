package com.archermind.kotlinplayer.model

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import java.util.*

class AudioBean(var data: String, var size: Long, var display_name: String, var artist: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }

        /**
         * 根据特定位置上的cursor获取bean
         */
        fun getAudioBean(cursor: Cursor?): AudioBean {
            val audioBean = AudioBean("", 0, "", "")
            //判断cursor是否为空
            cursor?.let {
                audioBean.data = it.getString(it.getColumnIndex(MediaStore.Audio.Media.DATA))
                audioBean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audioBean.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                audioBean.display_name = audioBean.display_name.substring(0, audioBean.display_name.lastIndexOf("."))
                audioBean.artist = cursor.getString(it.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }
            return audioBean
        }

        fun getAudioBeans(cursor: Cursor?): ArrayList<AudioBean> {
            val list = ArrayList<AudioBean>()
            cursor?.let {
                it.moveToPosition(-1)
                while (it.moveToNext()) {
                    val audioBean = getAudioBean(it)
                    list.add(audioBean)
                }
            }
            return list
        }
    }


}
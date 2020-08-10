package com.archermind.kotlinplayer.model

import android.os.Parcel
import android.os.Parcelable

data class VidoPlayerBean(var id: Int, var title: String, var url: String) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VidoPlayerBean> {
        override fun createFromParcel(parcel: Parcel): VidoPlayerBean {
            return VidoPlayerBean(parcel)
        }

        override fun newArray(size: Int): Array<VidoPlayerBean?> {
            return arrayOfNulls(size)
        }
    }
}
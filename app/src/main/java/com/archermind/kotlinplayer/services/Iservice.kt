package com.archermind.kotlinplayer.services

import com.archermind.kotlinplayer.model.AudioBean

interface Iservice {
    fun playPre()
    fun playNext()
    fun updatePlayState()
    fun isPlaying(): Boolean?
    fun seeKTo(p1: Int)
    fun getDuration(): Int
    fun getProgress(): Int
    fun getplayMode(): Int
    fun updatePlayMode()
    fun getPlayList(): List<AudioBean>?
    fun playPosition(p2: Int)


}
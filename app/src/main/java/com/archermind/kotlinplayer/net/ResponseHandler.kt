package com.archermind.kotlinplayer.net

interface ResponseHandler<RESPONSE> {
    fun onError(type: Int, msg: String)
    fun onSucess(type: Int, response: RESPONSE)
}
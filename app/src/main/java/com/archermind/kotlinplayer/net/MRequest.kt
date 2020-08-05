package com.archermind.kotlinplayer.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

open class MRequest<RESPONSE>(val type: Int, val url: String, val handler: ResponseHandler<RESPONSE>) {
    /**
     * 解析response
     */
    fun parseResult(result: String?): RESPONSE? {
        val gson = Gson()
        //获取泛型类型
        val type = (this.javaClass
                .genericSuperclass as ParameterizedType).getActualTypeArguments()[0]

        try {
            val list = gson.fromJson<RESPONSE>(result, type)
            return list
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * 发送网络请求
     */
    fun exute() {
        NetManger.manger.sendRequest(this)
    }
}
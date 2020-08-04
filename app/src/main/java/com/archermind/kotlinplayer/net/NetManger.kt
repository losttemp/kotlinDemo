package com.archermind.kotlinplayer.net

import com.itheima.player.util.ThreadUtil
import okhttp3.*
import java.io.IOException

/**
 * 单例
 */
open class NetManger private constructor() {


    companion object {
        val manger by lazy { NetManger() }
    }

    fun <RESPONSE> sendRequest(mRequest: MRequest<RESPONSE>) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(mRequest.url)
                .get()
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            /**
             * 子线程调用
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        mRequest.handler.onError(mRequest.type, e.toString())
                    }

                })

            }

            /**
             * 子线程调用
             */
            override fun onResponse(call: Call, response: Response) {
                val result = response?.body()?.string()
                val parseResult = mRequest.parseResult(result)
                ThreadUtil.runOnMainThread(object : Runnable {
                    override fun run() {
                        mRequest.handler.onSucess(mRequest.type, parseResult)
                    }

                })
            }

        })
    }
}
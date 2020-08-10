package com.archermind.kotlinplayer.net

import com.archermind.kotlinplayer.util.URLProviderUtils
import com.itheima.player.model.bean.MvAreaBean

class MvAeraRequest(handler: ResponseHandler<List<MvAreaBean>>)
    : MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), handler) {
}
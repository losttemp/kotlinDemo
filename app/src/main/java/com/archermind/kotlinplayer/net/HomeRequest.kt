package com.archermind.kotlinplayer.net

import com.archermind.kotlinplayer.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean

class HomeRequest(type: Int, offset: Int, handler: ResponseHandler<List<HomeItemBean>>)
    : MRequest<List<HomeItemBean>>(type, URLProviderUtils.getHomeUrl(offset, 20), handler) {
}
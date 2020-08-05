package com.archermind.kotlinplayer.net

import com.archermind.kotlinplayer.util.URLProviderUtils
import com.itheima.player.model.bean.YueDanBean

class YueDanRequest(type: Int, offset: Int, handler: ResponseHandler<YueDanBean>) :
        MRequest<YueDanBean>(type, URLProviderUtils.getYueDanUrl(offset, 20), handler) {
}
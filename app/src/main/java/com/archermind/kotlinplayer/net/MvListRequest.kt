package com.archermind.kotlinplayer.net

import com.archermind.kotlinplayer.util.URLProviderUtils
import com.itheima.player.model.bean.MvPagerBean

class MvListRequest(type: Int, aera: String, offset: Int, handler: ResponseHandler<MvPagerBean>)
    : MRequest<MvPagerBean>(type, URLProviderUtils.getMVListUrl(aera, offset, 20), handler) {
}
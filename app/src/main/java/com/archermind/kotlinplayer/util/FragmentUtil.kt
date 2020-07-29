package com.archermind.kotlinplayer.util

import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.base.BaseFragment
import com.archermind.kotlinplayer.ui.fragment.HomeFragment
import com.archermind.kotlinplayer.ui.fragment.MvFragment
import com.archermind.kotlinplayer.ui.fragment.VbangFragment
import com.archermind.kotlinplayer.ui.fragment.YuedanFragment

class FragmentUtil private constructor() {
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VbangFragment() }
    val yuedanFragment by lazy { YuedanFragment() }

    companion object {
        val fragmentutil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId: Int): BaseFragment? {
        when (tabId) {
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vbang -> return vbangFragment
            R.id.tab_yuedan -> return yuedanFragment
        }
        return null
    }
}
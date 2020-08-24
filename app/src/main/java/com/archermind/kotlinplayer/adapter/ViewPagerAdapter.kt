package com.archermind.kotlinplayer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.archermind.kotlinplayer.ui.fragment.DefaultFragment

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return DefaultFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}
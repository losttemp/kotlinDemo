package com.archermind.kotlinplayer.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.archermind.kotlinplayer.ui.fragment.MvPagerFragment
import com.itheima.player.model.bean.MvAreaBean

class MvPagerAdapter(val context: Context, val list: List<MvAreaBean>, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        bundle.putString("args", list.get(position).name)
        val fragment = Fragment.instantiate(context, MvPagerFragment::class.java.name, bundle)
        return fragment
    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list?.get(position)?.name
    }
}
package com.archermind.kotlinplayer.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archermind.kotlinplayer.R
import com.archermind.kotlinplayer.ui.activity.AbountActivity

class SettingFragment : PreferenceFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if (key.equals("about")) {
            //点击了关于
            startActivity(Intent(this.activity, AbountActivity::class.java))
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}
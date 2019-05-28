package com.revolshen.notekeeper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager, private val numberOfFrag: Int): FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
                0 -> return MainFragment()
                1 -> return DetailFragment()
        }

        return null
    }
    override fun getCount(): Int {
        return numberOfFrag
    }

}
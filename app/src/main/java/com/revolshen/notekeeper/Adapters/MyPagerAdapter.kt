package com.revolshen.notekeeper.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.revolshen.notekeeper.Fragments.MainFragment

class MyPagerAdapter(fm: FragmentManager, private val numberOfFrag: Int): FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
                0 -> return MainFragment()
        }

        return null
    }
    override fun getCount(): Int {
        return numberOfFrag
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Moje notatki"
        }

        return super.getPageTitle(position)
    }

}
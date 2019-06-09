package com.revolshen.notekeeper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import java.lang.StringBuilder

class MyPagerAdapter(fm: FragmentManager, private val numberOfFrag: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
                0 -> return MainFragment()
                1 -> return ImportantNoteFragment()
        }

        return null
    }
    override fun getCount(): Int {
        return numberOfFrag
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Moje notatki"
            1 -> return "Ważne notatki"
        }

        return super.getPageTitle(position)
    }

}
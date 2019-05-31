package com.revolshen.notekeeper

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val viewPager: ViewPager
         val pagerAdapter: FragmentStatePagerAdapter
         val tabLayout: TabLayout

        viewPager = findViewById(R.id.viewPager)

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val tab1 = tabLayout.newTab()
        val tab2 = tabLayout.newTab()
        tab1.setText(R.string.mojeNotatki)
        tab2.setText(R.string.wazneNotatki)

        tabLayout.addTab(tab1)
        tabLayout.addTab(tab2)
        tabLayout.setupWithViewPager(viewPager)

        pagerAdapter = MyPagerAdapter(supportFragmentManager, 2)

        viewPager.adapter = pagerAdapter


         tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
             override fun onTabReselected(p0: TabLayout.Tab?) {}

             override fun onTabUnselected(p0: TabLayout.Tab?) {}

             override fun onTabSelected(p0: TabLayout.Tab?) {
                 viewPager.setCurrentItem(p0!!.position)
             }

         })

        newNoteBT.setOnClickListener{
            val intent = Intent(applicationContext, DetailsActivity::class.java)
            startActivity(intent)
        }



    }



}

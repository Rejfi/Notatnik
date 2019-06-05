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

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FragmentStatePagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = MyPagerAdapter(supportFragmentManager, 2)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = pagerAdapter

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setupWithViewPager(viewPager, true)



        newNoteBT.setOnClickListener{
            val intent = Intent(applicationContext, DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}

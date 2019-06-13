package com.revolshen.notekeeper.Classes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.revolshen.notekeeper.Adapters.MyPagerAdapter
import com.revolshen.notekeeper.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: FragmentPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pagerAdapter = MyPagerAdapter(supportFragmentManager, 1)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = pagerAdapter

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        //Synchronize tabLayout and ViewPager
        tabLayout.setupWithViewPager(viewPager, false)


        //Create new note after click textView
        newNoteBT.setOnClickListener {
            val intent = Intent(applicationContext, DetailsActivity::class.java)
            startActivity(intent)
        }
    }

}


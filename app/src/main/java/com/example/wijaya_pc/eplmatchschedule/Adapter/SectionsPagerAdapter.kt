package com.example.wijaya_pc.eplmatchschedule.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.wijaya_pc.eplmatchschedule.Main2Activity

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return Main2Activity.PlaceholderFragment.newInstance(position + 1)
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 2
    }
}
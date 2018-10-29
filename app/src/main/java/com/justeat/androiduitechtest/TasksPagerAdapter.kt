package com.justeat.androiduitechtest

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TasksPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val fragments = listOf(
        ActiveTasksFragment() to "Active Tasks",
        CompletedTasksFragment() to "Completed Tasks"
    )

    override fun getItem(position: Int): Fragment = fragments[position].first

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].second

}

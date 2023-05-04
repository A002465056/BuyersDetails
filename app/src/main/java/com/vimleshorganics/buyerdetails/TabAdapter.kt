package com.vimleshorganics.buyerdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val mFragmentList: List<Fragment> = ArrayList()
    private val mFragmentTitleList: List<String> = ArrayList()

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position);
    }


}
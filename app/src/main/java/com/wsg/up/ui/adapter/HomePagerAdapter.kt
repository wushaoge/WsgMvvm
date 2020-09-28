package com.wsg.up.ui.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wsg.base.fragment.BaseFragment
import com.wsg.up.ui.fragment.base.MyBaseFragment

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:48
 * 描    述：
 * 修订历史：
 * ================================================
 */
class HomePagerAdapter: FragmentPagerAdapter {

    private var mFragmentList: ArrayList<MyBaseFragment>? = null
    private var mContext: Context? = null

    constructor(
        fm: FragmentManager?,
        list: ArrayList<MyBaseFragment>
    ): super(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        mFragmentList = list
    }

    constructor(
        context: Context?,
        fm: FragmentManager?,
        list: ArrayList<MyBaseFragment>?
    ) : super(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        mFragmentList = list
        mContext = context
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList!![position]
    }

    override fun getCount(): Int {
        return mFragmentList!!.size
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentList!![position].getTitle()
    }


}
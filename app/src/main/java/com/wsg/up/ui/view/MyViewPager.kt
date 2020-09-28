package com.wsg.up.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 15:01
 * 描    述：
 * 修订历史：
 * ================================================
 */
class MyViewPager: ViewPager {
    private var scrollble = false

    constructor(context: Context): super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ): super(context, attrs)


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollble) {
            super.onTouchEvent(ev)
        } else scrollble
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (scrollble) {
            super.onInterceptTouchEvent(ev)
        } else scrollble
    }

    fun isScrollble(): Boolean {
        return scrollble
    }

    fun setScrollble(scrollble: Boolean) {
        this.scrollble = scrollble
    }


}
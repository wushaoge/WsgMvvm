package com.wsg.up.ui.view.listener

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.wsg.up.R

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2018-09-04 19:36
 * 描    述：
 * 修订历史：
 * ================================================
 */
class HomeTabSelectedListener(private val mContext: Context) :
    OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab) {
        val customView = tab.customView
        val tvTitle =
            customView!!.findViewById<View>(R.id.tv_tab_title) as TextView
        val ivImg =
            customView.findViewById<View>(R.id.iv_img) as ImageView
        tvTitle.setTextColor(mContext.resources.getColor(R.color.colorPrimary))

//        DrawableCompat.setTint(
//            ivImg.drawable,
//            ContextCompat.getColor(mContext, R.color.colorPrimary)
//        )
        ivImg.setImageDrawable(ivImg.drawable)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
        val customView = tab.customView
        val tvTitle = customView!!.findViewById<View>(R.id.tv_tab_title) as TextView
        val ivImg = customView.findViewById<View>(R.id.iv_img) as ImageView
        tvTitle.setTextColor(mContext.resources.getColor(R.color.font_gray_deep))
//        DrawableCompat.setTint(
//            ivImg.drawable,
//            ContextCompat.getColor(mContext, R.color.font_black)
//        )
//        ivImg.setImageDrawable(ivImg.drawable)

        //通过mutate（）复制加载出来的对象
        val drawable = ivImg.drawable.mutate()
        val wrappedDrawable =
            DrawableCompat.wrap(drawable)
        DrawableCompat.setTintList(
            wrappedDrawable,
            ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.font_black))
        )
        ivImg.setImageDrawable(wrappedDrawable)
    }

    override fun onTabReselected(tab: TabLayout.Tab) {}

}
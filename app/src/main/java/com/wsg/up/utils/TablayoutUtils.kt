package com.wsg.up.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.wsg.up.R
import com.wsg.up.entity.BottomResourceData
import com.wsg.up.ext.onTabSelected
import java.util.*

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2018-09-05 10:21
 * 描    述：
 * 修订历史：
 * ================================================
 */
object TablayoutUtils {
    /**
     * 首页设置tablayout布局
     * @param mContext
     * @param tlTablayout
     * @param bottomList
     */
    fun setHomeTablayoutCustomView(mContext: Context, tlTablayout: TabLayout, bottomList: ArrayList<BottomResourceData>) {
        for (i in 0 until tlTablayout.tabCount) {
            val itemView: View = LayoutInflater.from(mContext).inflate(R.layout.tab_home_item, tlTablayout, false)
            val txtView = itemView.findViewById<View>(R.id.tv_tab_title) as TextView
            txtView.text = bottomList[i].getTitle()
            val ivImg = itemView.findViewById<View>(R.id.iv_img) as ImageView

            //默认选中第一项
            if (i == 0) {
                txtView.setTextColor(mContext.getColor(R.color.colorPrimary))
                ivImg.setImageResource(bottomList[i].getResource())
            } else {
                txtView.setTextColor(mContext.getColor(R.color.font_gray_deep))
                ivImg.setImageResource(bottomList[i].getUnCheckResource())
            }
            tlTablayout.getTabAt(i)!!.customView = itemView
        }

        //设置监听
        tlTablayout.onTabSelected {
            onTabSelected {
                for (i in 0 until tlTablayout.tabCount) {
                    val itemView: View = tlTablayout.getTabAt(i)!!.customView!!
                    val txtView = itemView.findViewById<View>(R.id.tv_tab_title) as TextView
                    val ivImg = itemView.findViewById<View>(R.id.iv_img) as ImageView
                    if(i != tlTablayout.selectedTabPosition){
                        txtView.setTextColor(mContext.getColor(R.color.font_gray_deep))
                        ivImg.setImageResource(bottomList[i].getUnCheckResource())
                    }
                }
                customView?.let {
                    (it.findViewById<View>(R.id.tv_tab_title) as TextView).setTextColor(mContext.getColor(R.color.colorPrimary))
                    (it.findViewById<View>(R.id.iv_img) as ImageView).setImageResource(bottomList[tlTablayout.selectedTabPosition].getResource())
                }
            }
        }


//        tlTablayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                for (i in 0 until tlTablayout.tabCount) {
//                    val itemView: View = tlTablayout.getTabAt(i)!!.customView!!
//                    val txtView = itemView.findViewById<View>(R.id.tv_tab_title) as TextView
//                    val ivImg = itemView.findViewById<View>(R.id.iv_img) as ImageView
//                    if(i != tlTablayout.selectedTabPosition){
//                        txtView.setTextColor(mContext.getColor(R.color.font_gray_deep))
//                        ivImg.setImageResource(bottomList[i].getUnCheckResource())
//                    }
//                }
//                tab!!.customView?.let {
//                    (it.findViewById<View>(R.id.tv_tab_title) as TextView).setTextColor(mContext.getColor(R.color.colorPrimary))
//                    (it.findViewById<View>(R.id.iv_img) as ImageView).setImageResource(bottomList[tlTablayout.selectedTabPosition].getResource())
//                }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//        })
    }
}
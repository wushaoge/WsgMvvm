package com.wsg.up.ui.activity.ugc

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.jaeger.library.StatusBarUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.wsg.up.R
import com.wsg.up.entity.RecommendItem
import com.wsg.up.ui.activity.base.MyBaseActivity
import com.wsg.up.ui.adapter.UgcDetailAdapter
import com.wsg.up.ui.view.listener.AutoPlayPageChangeListener
import kotlinx.android.synthetic.main.activity_ugc_detail.*

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-27 10:24
 * 描    述：
 * 修订历史：
 * ================================================
 */
class UgcDetailActivity: MyBaseActivity() {

    private lateinit var adapter: UgcDetailAdapter

    var position = -1
    var list: List<RecommendItem>? = null

    override fun getChildTitle(): String? {
        return "详情"
    }

    override fun getBundleExtras(extras: Bundle?) {
        position = extras!!.getInt("position")
        list = extras!!.getParcelableArrayList("data")
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_ugc_detail
    }

    override fun initViewModel() {
    }

    override fun initView() {
        setHeadGone()
        StatusBarUtil.setColor(this, getColor(R.color.black))
    }

    override fun initData() {

        adapter = UgcDetailAdapter(this, list!!)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.offscreenPageLimit = 1
        viewPager.registerOnPageChangeCallback(AutoPlayPageChangeListener(viewPager, position, R.id.videoPlayer))
        viewPager.setCurrentItem(position, false)
    }

    override fun initMainNetData() {
    }

    override fun onClick(v: View?) {

    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.anl_push_bottom_out)
    }

}
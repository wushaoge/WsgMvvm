package com.wsg.up.ui.activity

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.wsg.up.R
import com.wsg.up.entity.RecommendItem
import com.wsg.up.entity.Tag
import com.wsg.up.ui.activity.base.MyBaseActivity

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-27 10:24
 * 描    述：
 * 修订历史：
 * ================================================
 */
class TestActivity: MyBaseActivity() {

    override fun getChildTitle(): String? {
        return ""
    }

    override fun getBundleExtras(extras: Bundle?) {
        val tag: Tag? = extras!!.getParcelable("tag")
        val list: ArrayList<Tag>? = intent.getParcelableArrayListExtra("data")
        val item: RecommendItem? = extras!!.getParcelable("item")
        LogUtils.e(tag?.name)
        LogUtils.e("${item?.id}水电费")
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initMainNetData() {
    }

    override fun onClick(v: View?) {
    }


}
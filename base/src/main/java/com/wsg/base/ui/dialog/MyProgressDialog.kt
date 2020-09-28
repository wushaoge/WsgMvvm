package com.wsg.base.ui.dialog

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.TextView
import com.wsg.base.R

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-21 15:25
 * 描    述：
 * 修订历史：
 * ================================================
 */
class MyProgressDialog: Dialog{

    private var tv_loadingmsg: TextView? = null

    constructor(context: Context, content: String? = null) : super(context, R.style.dialog){
        initView(content)
    }

    private fun initView(content: String?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.common_progress_dialog)
        tv_loadingmsg = findViewById<View>(R.id.tv_loadingmsg) as TextView
        setLoadingMsg(content)
    }

    /**
     * 设置提示语
     * @param content
     */
    fun setLoadingMsg(content: String?) {
        if (TextUtils.isEmpty(content)) {
            tv_loadingmsg!!.visibility = View.GONE
        } else {
            tv_loadingmsg!!.visibility = View.VISIBLE
            tv_loadingmsg!!.text = content
        }
    }
}
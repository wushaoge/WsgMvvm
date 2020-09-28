package com.wsg.up.ui.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import com.wsg.up.R
import kotlinx.android.synthetic.main.common_confirm_dialog.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-06 15:45
 * 描    述：
 * 修订历史：
 * ================================================
 */
class MyConfirmDialog : Dialog {


    constructor(context: Context, title: String = "", content: String = "您确定这么做吗", okClick: () -> Unit = {}, cancleClick: () -> Unit = {}  ): super(context, R.style.dialog){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.common_confirm_dialog)
        this.setCanceledOnTouchOutside(true)

        tv_title.visibility = View.GONE
        btn_single.visibility = View.GONE

        title.isNotEmpty().let {
            setTitle(title)
        }
        setContent(content)

        btn_confirm.setOnClickListener { okClick();dismiss() }
        btn_cancel.setOnClickListener { cancleClick();dismiss() }
        btn_single.setOnClickListener { okClick();dismiss() }
    }


    /**
     * 设置标题
     * @param str
     * @return
     */
    fun setTitle(str: String?): MyConfirmDialog? {
        tv_title.visibility = View.VISIBLE
        tv_title.text = str
        return this
    }

    /**
     * 设置内容
     * @param str
     * @return
     */
    fun setContent(str: String?): MyConfirmDialog? {
        tv_content.text = str
        return this
    }


    /**
     * 设置取消按钮文字
     * @param str
     * @return
     */
    fun setCancleBtnText(str: String?): MyConfirmDialog? {
        btn_cancel.setText(str)
        return this
    }

    /**
     * 设置确定按钮文字
     * @param str
     * @return
     */
    fun setConfirmBtnText(str: String?): MyConfirmDialog? {
        btn_confirm.setText(str)
        return this
    }

    /**
     * 设置单按钮文字
     */
    fun setSingleBtnText(str: String?): MyConfirmDialog? {
        btn_single.setText(str)
        return this
    }

    /**
     * 设置为单个按钮
     */
    fun setSingleBtnVisible(visible: Boolean): MyConfirmDialog? {
        btn_single.visibility = if (visible) View.VISIBLE else View.GONE
        btn_cancel.visibility = if (!visible) View.VISIBLE else View.GONE
        btn_confirm.visibility = if (!visible) View.VISIBLE else View.GONE
        v_single_line.visibility = if (!visible) View.VISIBLE else View.GONE
        return this
    }

    fun setOkClick(click: () -> Unit){
        btn_confirm.setOnClickListener { click();dismiss() }
    }

    fun setCancel(click: () -> Unit){
        btn_cancel.setOnClickListener { click();dismiss() }
    }

}
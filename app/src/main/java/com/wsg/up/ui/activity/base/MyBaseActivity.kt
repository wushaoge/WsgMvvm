package com.wsg.up.ui.activity.base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import com.wsg.base.activity.BaseActivity
import com.wsg.base.ui.dialog.MyProgressDialog
import com.wsg.up.utils.TypeFaceUtil

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-22 15:29
 * 描    述：
 * 修订历史：
 * ================================================
 */
abstract class MyBaseActivity: BaseActivity() {

    var dialog: MyProgressDialog? = null

    //分页使用
    var pageNo = 1
    var pageSize = 10
    var pageCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), object: LayoutInflater.Factory2{
            override fun onCreateView(
                parent: View?,
                name: String,
                context: Context,
                attrs: AttributeSet
            ): View? {

                var view  = delegate.createView(parent, name, context, attrs)
                if(view != null && view is TextView){
                    view.typeface = TypeFaceUtil.getEyeTypeFace()
                }
                return view
            }

            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                return null
            }
        })

        super.onCreate(savedInstanceState)
    }

    override fun showLoadingDialog(content: String) {
        dialog?:let {
            dialog = MyProgressDialog(mContext, content)
        }
        dialog?.show()
    }

    override fun dismissLoadingDialog() {
        dialog?.dismiss()
    }
}
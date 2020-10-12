package com.wsg.up.ui.fragment.base

import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.wsg.base.fragment.BaseFragment
import com.wsg.base.ui.dialog.MyProgressDialog

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-22 15:29
 * 描    述：
 * 修订历史：
 * ================================================
 */
abstract class MyBaseFragment: BaseFragment() {

    var baseTitle: String? = ""

    var dialog: MyProgressDialog? = null

    //分页使用
    var pageNo = 1
    var pageSize = 10
    var pageCount = 0

    override fun showLoadingDialog(content: String) {
        dialog?:let {
            dialog = MyProgressDialog(mContext, content)
        }
        dialog?.show()
    }

    override fun dismissLoadingDialog() {
        dialog?.dismiss()
    }

    open fun getTitle(): String? {
        return baseTitle
    }

    open fun setTitle(title: String?) {
        this.baseTitle = title
    }

    /**
     * 结束刷新/加载
     *
     * @param srl
     */
    open fun stopLoad(srl: SmartRefreshLayout?) {
        if (srl != null) {
            if (srl.state == RefreshState.Refreshing) {
                srl.finishRefresh()
            } else {
                srl.finishLoadMore()
            }
        }
    }
}
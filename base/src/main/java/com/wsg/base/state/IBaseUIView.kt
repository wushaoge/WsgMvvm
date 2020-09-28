package com.wsg.base.state

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-21 14:45
 * 描    述：
 * 修订历史：
 * ================================================
 */
interface IBaseUIView {

    /** 获取当前布局状态,方便在callBack中统一处理  */
    fun getBaseViewStatus(): EBaseViewStatus?

    /** 手动设置布局状态,一般情况不需要  */
    fun setBaseViewStatus(baseViewStatus: EBaseViewStatus?)

    /** 显示成功布局  */
    fun showSuccessLayout()

    /** 显示加载中布局  */
    fun showLoadingLayout()

    /** 显示失败布局  */
    fun showErrorLayout(errorMsg: String?)
}
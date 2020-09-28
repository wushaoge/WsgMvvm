package com.wsg.base.entity

import androidx.annotation.Keep

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 14:47
 * 描    述：
 * 修订历史：
 * ================================================
 */
@Keep
open class BaseData<T>(
    var code: Int = 1,
    var message: String = "",
    var data: T
) {

    /**
     * 数据是否正确，默认实现
     */
    open fun dataRight(): Boolean {
        return code == 200
    }

    /**
     * 获取错误信息，默认实现
     */
    open fun getMsg(): String {
        return message
    }
}
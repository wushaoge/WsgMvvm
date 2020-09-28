package com.wsg.base.exception

import com.wsg.base.ext.parseErrorString
import java.lang.Exception

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-14 14:07
 * 描    述：
 * 修订历史：
 * ================================================
 */
class AppException : Exception {

    var errorMsg: String

    constructor(error: String?): super(){
        errorMsg = error ?: parseError(null)
    }

    constructor(throwable: Throwable?): super(){
        errorMsg = parseError(throwable)
    }

    private fun parseError(throwable: Throwable?): String {
        return throwable.parseErrorString()
    }
}
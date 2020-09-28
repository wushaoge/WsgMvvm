package com.wsg.base.state

import com.wsg.base.exception.AppException

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 10:51
 * 描    述：
 * 修订历史：
 * ================================================
 */

class VmResult<T> {
    var onAppSuccess: (data: T) -> Unit = {}
    var onAppError: (exception: AppException) -> Unit = {}
    var onAppLoading: () -> Unit = {}
    var onAppComplete: () -> Unit = {}
}

sealed class VmState<out T> {
    object Loading: VmState<Nothing>()
    data class Success<out T>(val data: T) : VmState<T>()
    data class Error(val error: AppException) : VmState<Nothing>()
}
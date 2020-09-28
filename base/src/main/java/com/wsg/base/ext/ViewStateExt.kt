package com.wsg.base.ext

import com.blankj.utilcode.util.LogUtils
import com.wsg.base.entity.BaseData
import com.wsg.base.exception.AppException
import com.wsg.base.state.VmState

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 14:52
 * 描    述：
 * 修订历史：
 * ================================================
 */



/**
 * 处理返回值
 *
 * @param result 请求结果
 */
fun <T> VmLiveData<T>.paresVmResult(result: BaseData<T>) {
    value = if (result.dataRight()) VmState.Success(result.data) else
        VmState.Error(AppException(result.getMsg()))
}

/**
 * 异常转换异常处理
 */
fun <T> VmLiveData<T>.paresVmException(e: Throwable) {
    this.value = VmState.Error(AppException(e))
}
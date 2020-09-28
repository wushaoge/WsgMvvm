package com.wsg.base.ext

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.rxLifeScope
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.wsg.base.R
import com.wsg.base.activity.BaseActivity
import com.wsg.base.application.BaseApplication
import com.wsg.base.entity.BaseData
import com.wsg.base.fragment.BaseFragment
import com.wsg.base.model.BaseViewModel
import com.wsg.base.state.EBaseViewStatus
import com.wsg.base.state.VmResult
import com.wsg.base.state.VmState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-14 14:09
 * 描    述：
 * 修订历史：
 * ================================================
 */

/**
 * 获取vm clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}

/**
 * 跟下面的方法类似,只是调用形式上有所区别
 * 这种vmResult要提前定义好
 * 下面vmResult: VmResult<T>.() -> Unit可以直接写在参数里面
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>) {
    observe(owner) {
        when (it) {
            is VmState.Loading ->{
                vmResult.onAppLoading()
            }
            is VmState.Success -> {
                vmResult.onAppSuccess(it.data)
                vmResult.onAppComplete()
            }
            is VmState.Error -> {
                vmResult.onAppError(it.error)
                vmResult.onAppComplete()
            }
        }
    }
}

/**
 * 重写所有回调方法
 * onAppLoading
 * onAppSuccess
 * onAppError
 * onAppComplete
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserver(owner: LifecycleOwner, vmResult: VmResult<T>.() -> Unit) {
    val result = VmResult<T>();result.vmResult();observe(owner) {
        when (it) {
            is VmState.Loading ->{
                result.onAppLoading()
            }
            is VmState.Success -> {
                result.onAppSuccess(it.data);result.onAppComplete()
            }
            is VmState.Error -> {
                result.onAppError(it.error);result.onAppComplete()
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
    ) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
                activity.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.dismissLoadingDialog()
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
                activity.dismissLoadingDialog()
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
                activity.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.dismissLoadingDialog()
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
                activity.dismissLoadingDialog()
                onComplete()
            }
        }
    }
}

/**
 * 默认不带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
            }
            is VmState.Success -> {
                onSuccess(it.data)
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
            }
        }
    }
}

/**
 * 默认不带loading的网络请求
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
            }
            is VmState.Success -> {
                onSuccess(it.data)
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
                onComplete()
            }
        }
    }
}

/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
                if(activity.getBaseViewStatus() != EBaseViewStatus.SUCCESS) activity.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.showSuccessLayout()
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
                if(activity.getBaseViewStatus() != EBaseViewStatus.SUCCESS){
                    activity.showErrorLayout(it.error.errorMsg)
                }
            }
        }
    }
}

/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseActivity
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    activity: BaseActivity,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(activity) {
        when (it) {
            is VmState.Loading ->{
                if(activity.getBaseViewStatus() != EBaseViewStatus.SUCCESS) activity.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                activity.showSuccessLayout()
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) activity!!.showToast(it.error.errorMsg)
                if(activity.getBaseViewStatus() != EBaseViewStatus.SUCCESS){
                    activity.showErrorLayout(it.error.errorMsg)
                    onComplete()
                }
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
                fragment.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.dismissLoadingDialog()
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
                fragment.dismissLoadingDialog()
            }
        }
    }
}

/**
 * 带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverLoading(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
                fragment.showLoadingDialog()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.dismissLoadingDialog()
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
                fragment.dismissLoadingDialog()
                onComplete()
            }
        }
    }
}

/**
 * 不带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
            }
            is VmState.Success -> {
                onSuccess(it.data)
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
            }
        }
    }
}

/**
 * 不带loading的网络请求
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverDefault(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
            }
            is VmState.Success -> {
                onSuccess(it.data)
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
                onComplete()
            }
        }
    }
}

/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit)
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
                if(fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS) fragment.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.showSuccessLayout()
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
                if(fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS){
                    fragment.showErrorLayout(it.error.errorMsg)
                }
            }
        }
    }
}

/**
 * 主网络请求 适用于页面必须请求网络后才显示的页面,页面的初始状态isShowLoading设置为true
 * 第一个参数继承自BaseFragment
 * 第二个参数 是否toast提示错误
 * 第三个参数 成功回调
 * 第四个参数 不论成功还是失败都会回调
 */
@MainThread
inline fun <T> VmLiveData<T>.vmObserverMain(
    fragment: BaseFragment,
    tips: Boolean? = true,
    crossinline onSuccess: ((T) -> Unit),
    crossinline onComplete: (() -> Unit) = {}
) {
    observe(fragment) {
        when (it) {
            is VmState.Loading ->{
                if(fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS) fragment.showLoadingLayout()
            }
            is VmState.Success -> {
                onSuccess(it.data)
                fragment.showSuccessLayout()
                onComplete()
            }
            is VmState.Error -> {
                if(null != tips && tips) fragment.context!!.showToast(it.error.errorMsg)
                if(fragment.getBaseViewStatus() != EBaseViewStatus.SUCCESS){
                    fragment.showErrorLayout(it.error.errorMsg)
                }
                onComplete()
            }
        }
    }
}

/**
 * BaseViewModel开启协程扩展
 */
fun <T> BaseViewModel.launchVmRequest(
    request: suspend () -> BaseData<T>,
    viewState: VmLiveData<T>
) {
    viewModelScope.launch {
        runCatching {
            viewState.value = VmState.Loading
            request()
        }.onSuccess {
            viewState.paresVmResult(it)
        }.onFailure {
            viewState.paresVmException(it)
        }
    }
}


/**
 * 网络错误提示
 */
fun Throwable?.parseErrorString(): String {
    return when (this) {
        is SocketException -> BaseApplication.instance.getString(R.string.SocketException)
        is ConnectException -> BaseApplication.instance.getString(R.string.ConnectException)
        is UnknownHostException -> BaseApplication.instance.getString(R.string.UnknownHostException)
        is JsonSyntaxException -> BaseApplication.instance.getString(R.string.JsonSyntaxException)
        is SocketTimeoutException -> BaseApplication.instance.getString(R.string.SocketTimeoutException)
        is TimeoutException -> BaseApplication.instance.getString(R.string.SocketTimeoutException)
        else -> BaseApplication.instance.getString(R.string.ElseNetException)
    }
}

/**
 * 测试用,无视
 */
fun BaseActivity.launch(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return this.rxLifeScope.launch(block, {}, {showLoadingDialog()}, {dismissLoadingDialog()})
}

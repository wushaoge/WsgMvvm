package com.wsg.base.ext

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wsg.base.state.VmState

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 14:39
 * 描    述：
 * 修订历史：
 * ================================================
 */
@MainThread
inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T> {
    val wrappedObserver = Observer<T> { t -> onChanged(t) }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

typealias VmLiveData<T> = MutableLiveData<VmState<T>>
package com.wsg.base.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-24 11:23
 * 描    述：监听整个app的生命周期
 * 修订历史：
 * ================================================
 */
class ProcessLifecycleObserver: LifecycleObserver {
    /**
     * 当app变成前台进程时监听
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun enterAppListener(){
        Log.e("ProcessLifecycle","ON_CREATE")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startAppListener(){
        Log.e("ProcessLifecycle","ON_START")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeAppListener(){
        Log.e("ProcessLifecycle","ON_RESUME")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseAppListener(){
        Log.e("ProcessLifecycle","ON_PAUSE")
    }
    /**
     * 当app变成后台进程或者退出调用
     * 此方法一次app进入退出只会调用一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun exitAppListener(){
        Log.e("ProcessLifecycle","ON_STOP")
    }
}
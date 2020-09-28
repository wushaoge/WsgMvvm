package com.wsg.base.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.wsg.base.lifecycle.ProcessLifecycleObserver
import kotlin.properties.Delegates

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-14 14:38
 * 描    述：
 * 修订历史：
 * ================================================
 */
open class BaseApplication: Application() {

    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //添加应用生命周期观察者
        ProcessLifecycleOwner.get().lifecycle.addObserver(ProcessLifecycleObserver())

    }


}
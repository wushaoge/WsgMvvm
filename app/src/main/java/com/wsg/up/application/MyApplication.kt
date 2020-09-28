package com.wsg.up.application

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.didichuxing.doraemonkit.DoraemonKit
import com.readystatesoftware.chuck.ChuckInterceptor
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import com.wsg.base.application.BaseApplication
import com.wsg.up.R
import okhttp3.OkHttpClient
import rxhttp.RxHttp
import java.util.*

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-22 15:33
 * 描    述：
 * 修订历史：
 * ================================================
 */
class MyApplication: BaseApplication() {



    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, layout: RefreshLayout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, R.color.white)
            BezierRadarHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, layout: RefreshLayout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, R.color.white)
            BallPulseFooter(context)
        }
    }


    override fun onCreate() {
        super.onCreate()

        //开发者工具
        DoraemonKit.install(instance)

        //初始化Rxhttp
        initRxhttp()


        //集中管理actviity的队列
        registerActivityListener()

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        val cb: PreInitCallback = object : PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("x5webview", " 加载x5webview $arg0")
            }

            override fun onCoreInitFinished() {}
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)

    }


    open fun initRxhttp() {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ChuckInterceptor(applicationContext))
        //或者，调试模式下会有日志输出
        RxHttp.init(builder.build(), true)
    }


    private fun registerActivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    /**
                     * 监听到 Activity创建事件 将该 Activity 加入list
                     */
                    pushActivity(activity)
                }

                override fun onActivityStarted(activity: Activity?) {}
                override fun onActivityResumed(activity: Activity?) {}
                override fun onActivityPaused(activity: Activity?) {}
                override fun onActivityStopped(activity: Activity?) {}
                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}
                override fun onActivityDestroyed(activity: Activity?) {
                    if (null == mActivitys || mActivitys.isEmpty()) {
                        return
                    }
                    if (mActivitys.contains(activity)) {
                        /**
                         * 监听到 Activity销毁事件 将该Activity 从list中移除
                         */
                        popActivity(activity)
                    }
                }
            })
        }
    }

    companion object {
        /**
         * 维护Activity 的list
         */
        var mActivitys = Collections
            .synchronizedList(LinkedList<Activity>())

        /**
         * @param activity 作用说明 ：添加一个activity到管理里
         */
        fun pushActivity(activity: Activity?) {
            mActivitys.add(activity)
        }

        /**
         * @param activity 作用说明 ：删除一个activity在管理里
         */
        fun popActivity(activity: Activity?) {
            mActivitys.remove(activity)
        }


        /**
         * get current Activity 获取当前Activity（栈中最后一个压入的）
         */
        fun currentActivity(): Activity? {
            return if (mActivitys == null || mActivitys.isEmpty()) {
                null
            } else mActivitys[mActivitys.size - 1]
        }

        /**
         * 结束当前Activity（栈中最后一个压入的）
         */
        fun finishCurrentActivity() {
            if (mActivitys == null || mActivitys.isEmpty()) {
                return
            }
            val activity = mActivitys[mActivitys.size - 1]
            finishActivity(activity)
        }

        /**
         * 结束指定的Activity
         */
        fun finishActivity(activity: Activity?) {
            var activity = activity
            if (mActivitys == null || mActivitys.isEmpty()) {
                return
            }
            if (activity != null) {
                mActivitys.remove(activity)
                activity.finish()
                activity = null
            }
        }

        /**
         * 结束指定类名的Activity
         */
        fun finishActivity(cls: Class<*>) {
            if (mActivitys == null || mActivitys.isEmpty()) {
                return
            }
            for (activity in mActivitys) {
                if (activity.javaClass == cls) {
                    finishActivity(activity)
                    break
                }
            }
        }

        /**
         * 按照指定类名找到activity
         *
         * @param cls
         * @return
         */
        fun findActivity(cls: Class<*>): Activity? {
            var targetActivity: Activity? = null
            if (mActivitys != null) {
                for (activity in mActivitys) {
                    if (activity.javaClass == cls) {
                        targetActivity = activity
                        break
                    }
                }
            }
            return targetActivity
        }

        /**
         * @return 作用说明 ：获取当前最顶部activity的实例
         */
        fun getTopActivity(): Activity? {
            var mBaseActivity: Activity? = null
            synchronized(mActivitys) {
                val size = mActivitys.size - 1
                if (size < 0) {
                    return null
                }
                mBaseActivity = mActivitys[size]
            }
            return mBaseActivity
        }

        /**
         * @return 作用说明 ：获取当前最顶部的acitivity 名字
         */
        fun getTopActivityName(): String? {
            var mBaseActivity: Activity? = null
            synchronized(mActivitys) {
                val size = mActivitys.size - 1
                if (size < 0) {
                    return null
                }
                mBaseActivity = mActivitys[size]
            }
            return mBaseActivity!!.javaClass.name
        }

        /**
         * 结束所有Activity
         */
        fun finishAllActivity() {
            if (mActivitys == null) {
                return
            }
            for (activity in mActivitys) {
                activity.finish()
            }
            mActivitys.clear()
        }

        /**
         * 退出应用程序
         */
        fun appExit() {
            try {
                finishAllActivity()
            } catch (e: Exception) {
            }
        }

    }

}
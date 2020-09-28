package com.wsg.up.ui.activity

import android.Manifest
import android.view.View
import android.view.WindowManager
import com.blankj.utilcode.util.ToastUtils
import com.permissionx.guolindev.PermissionX
import com.wsg.base.ext.setViewsOnClickListener
import com.wsg.base.ext.showToast
import com.wsg.base.ext.startActivity
import com.wsg.up.MainActivity
import com.wsg.up.R
import com.wsg.up.ui.activity.base.MyBaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-29 15:33
 * 描    述：
 * 修订历史：
 * ================================================
 */
class SplashActivity: MyBaseActivity() {


    override fun getChildTitle(): String? {
        return null
    }

    override fun setContentBefore() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun initViewModel() {
    }

    override fun initView() {
        setHeadGone()

        setViewsOnClickListener(this, ll_skip)

        //申请权限
        checkPermissions()
    }

    fun checkPermissions() {

        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
            .onForwardToSettings { scope, deniedList ->
                showToast("请授予相关权限")
            }
            .request { allGranted, grantedList, deniedList ->
//                if (allGranted) {
//                    showToast("所有申请的权限都已通过")
//                } else {
//                    showToast("您拒绝了如下权限：$deniedList")
//                }
                lottie_view.playAnimation()
            }
    }

    override fun initData() {
        val list = mutableListOf(
            "pride-hard-seltzer.json",
            "lamsa-splash-screen.json",
            "ramadan-kareem-hello-doc.json",
            "logo-animation.json"
        )

        lottie_view.setAnimation(list.random())
        lottie_view.addAnimatorUpdateListener {
            if(it.animatedFraction == 1f) startActivity<MainActivity>()
        }
    }

    override fun initMainNetData() {

    }

    override fun onClick(v: View?) {
        when (v) {
            ll_skip -> {
                lottie_view.removeAllUpdateListeners()
                startActivity<MainActivity>()
            }
        }
    }
}
package com.wsg.base.activity

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.blankj.utilcode.util.BusUtils
import com.jaeger.library.StatusBarUtil
import com.wsg.base.R
import com.wsg.base.ext.hideSoftInput
import com.wsg.base.state.EBaseViewStatus
import com.wsg.base.state.IBaseUIView
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.common_base_error.*
import kotlinx.android.synthetic.main.common_head_layout.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 15:16
 * 描    述：
 * 修订历史：
 * ================================================
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener, IBaseUIView {

    lateinit var mContext: Context

    open fun setCreateBefore() {} //setCreateBefore之前的方法

    open fun setContentBefore() {} //setContentView之前的方法

    open fun isShowBarStatus(): Boolean { return true } //是否显示状态栏

    abstract fun getChildTitle(): String? //获取标题

    abstract fun getLayoutID(): Int //获取布局资源文件

    abstract fun initViewModel() //初始化ViewModel

    abstract fun initView() //初始化view

    abstract fun initData() //初始化数据

    abstract fun initMainNetData() //主协议请求数据,适用于必须请求协议才能展示的页面,在initData之前请求

    open fun isShowLoading(): Boolean { return false } //是否显示加载中布局,如果return true则必须重写initMainNetData方法

    open fun getBundleExtras(extras: Bundle?) {} //接收bundle数据

    //当前页面状态
    var myBaseViewStatus = EBaseViewStatus.SUCCESS //页面显示状态 SUCCESS, LOADING, ERROR  分别表示正常、加载中、失败

    var loadingView: View? = null //加载中布局
    var errorView: View? = null //错误布局

    var lottieLoadingView: LottieAnimationView? = null
    var lottieErrorView: LottieAnimationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setCreateBefore()
        super.onCreate(savedInstanceState)
        mContext = this
        setContentBefore()
        setContentView(R.layout.activity_base)
        BusUtils.register(this)
        intent.extras?.let { getBundleExtras(it) }
        initDefaultBaseVIew()
        setChildView(getLayoutID(), getChildTitle(), isShowLoading())
    }


    /**
     * 初始化基础布局
     */
    fun initDefaultBaseVIew(){
         //是否显示状态栏
         if(isShowBarStatus()) {
             StatusBarUtil.setColor(this, getColor(R.color.colorPrimary), 0)
         }

         ll_base_right.visibility = View.INVISIBLE
         iv_base_right.visibility = View.GONE
         tv_base_right.visibility = View.GONE
         base_main.visibility = View.VISIBLE

        rl_base_back.setOnClickListener { backClick() }
        ll_base_right.setOnClickListener { rightClick() }

    }

    /**
     * 返回事件
     */
    open fun backClick() {
        hideSoftInput()
        finish()
    }

    /**
     * 右侧布局事件
     */
    open fun rightClick() {}


    /**
     * 设置子布局+标题
     *
     * @param layoutID
     * @param baseTitle
     */
    open fun setChildView(layoutID: Int, baseTitle: String?, isShowLoading: Boolean) {
        setTitle(baseTitle)
        val view = LayoutInflater.from(this).inflate(layoutID, base_main, false)
        base_main.addView(view)
        initViewModel()
        initView()
        initData()
        initMainNetData()
        if (isShowLoading) {
            showLoadingLayout()
        } else {
            showSuccessLayout()
        }
    }

    /**
     * 去掉标题栏
     */
    open fun setHeadGone() {
        base_head.visibility = View.GONE
    }

    /**
     * 隐藏返回按钮
     */
    open fun setBackGone() {
        rl_base_back.visibility = View.GONE
    }

    /**
     * 隐藏返回按钮
     */
    open fun setBackInVisible() {
        rl_base_back.visibility = View.INVISIBLE
    }

    /**
     * 显示返回按钮
     */
    open fun setBackVisible() {
        rl_base_back.visibility = View.VISIBLE
    }

    /**
     * 设置标题
     *
     * @param baseTitle
     */
    open fun setTitle(baseTitle: String?) {
        tv_base_title.text = baseTitle
    }

    /**
     * 设置右侧文字
     * @param rightTitle
     * @param listener
     */
    open fun setRightTitle(
        rightTitle: String?,
        listener: View.OnClickListener?
    ) {
        ll_base_right.visibility = View.VISIBLE
        tv_base_right.visibility = View.VISIBLE
        tv_base_right.text = rightTitle
        tv_base_right.setOnClickListener(listener)
    }

    /**
     * 设置右侧文字
     * @param rightTitle
     */
    protected open fun setRightTitle(rightTitle: String?) {
        ll_base_right.visibility = View.VISIBLE
        tv_base_right.visibility = View.VISIBLE
        tv_base_right.text = rightTitle
    }

    /**
     * 设置右侧图标
     *
     * @param resource
     */
    open fun setRightImg(resource: Int) {
        ll_base_right.visibility = View.VISIBLE
        iv_base_right.visibility = View.VISIBLE
        iv_base_right.setImageResource(resource)
    }

    /**
     * 设置出错提示
     * @param error
     */
    open fun setErrorText(error: String?) {
        tv_error?.text = error
        tv_error_hint?.text = error
    }


    /**
     * 显示加载中界面
     */
    override fun showLoadingLayout(){
        loadingView?:let {
            loadingView = vs_loading.inflate()
            lottieLoadingView = loadingView?.findViewById(R.id.lottie_loading_view)
            lottieLoadingView!!.setAnimation("whale.zip")
            lottieLoadingView!!.repeatCount = ValueAnimator.INFINITE
            lottieLoadingView!!.playAnimation()
        }

        //显示界面
        base_main.visibility = View.GONE
        loadingView?.visibility = View.VISIBLE
        errorView?.visibility = View.GONE

        //暂停动画防止卡顿
        lottieErrorView?.pauseAnimation()

        //更改状态
        myBaseViewStatus = EBaseViewStatus.LOADING
    }

    /**
     * 显示子布局,隐藏加载中和错误布局
     */
    override fun showSuccessLayout(){
        //显示界面
        base_main.visibility = View.VISIBLE
        loadingView?.visibility = View.GONE
        errorView?.visibility = View.GONE

        //暂停动画防止卡顿
        lottieLoadingView?.pauseAnimation()
        lottieErrorView?.pauseAnimation()

        //更改状态
        myBaseViewStatus = EBaseViewStatus.SUCCESS
    }

    /**
     * 显示错误布局,隐藏子布局
     *
     * @param error
     */
    override fun showErrorLayout(errorMsg: String?) {
        errorView?:let {
            errorView = vs_error.inflate()
            lottieErrorView = errorView?.findViewById(R.id.lottie_error_view)
            lottieErrorView!!.setAnimation("halloween_smoothymon.json")
            lottieErrorView!!.repeatCount = ValueAnimator.INFINITE
            lottieErrorView!!.playAnimation()

            tv_reload.setOnClickListener { initMainNetData() }
        }

        //显示界面
        base_main.visibility = View.GONE
        loadingView?.visibility = View.GONE
        errorView?.visibility = View.VISIBLE

        if (!TextUtils.isEmpty(errorMsg)) {
            tv_error.text = errorMsg
            tv_error_hint.text = errorMsg
        }

        //暂停动画防止卡顿
        lottieLoadingView?.pauseAnimation()

        //更改状态
        myBaseViewStatus = EBaseViewStatus.ERROR
    }

    override fun getBaseViewStatus(): EBaseViewStatus? {
        return myBaseViewStatus
    }

    override fun setBaseViewStatus(baseViewStatus: EBaseViewStatus?) {
        when (baseViewStatus) {
            EBaseViewStatus.LOADING -> { showLoadingLayout() }
            EBaseViewStatus.SUCCESS -> { showSuccessLayout() }
            EBaseViewStatus.ERROR -> { showErrorLayout("") }
        }
    }

    override fun onResume() {
        super.onResume()
        if(getBaseViewStatus() == EBaseViewStatus.LOADING){
            lottieLoadingView?.resumeAnimation()
        }
        if(getBaseViewStatus() == EBaseViewStatus.ERROR){
            lottieErrorView?.resumeAnimation()
        }
    }

    override fun onPause() {
        super.onPause()
        lottieLoadingView?.pauseAnimation()
        lottieErrorView?.pauseAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        BusUtils.unregister(this)
    }

    abstract fun showLoadingDialog(content:  String = "加载中")

    abstract fun dismissLoadingDialog()
}
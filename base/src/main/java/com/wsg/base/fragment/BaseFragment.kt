package com.wsg.base.fragment

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.LogUtils
import com.wsg.base.R
import com.wsg.base.state.EBaseViewStatus
import com.wsg.base.state.IBaseUIView
import kotlinx.android.synthetic.main.common_base_error.*
import kotlinx.android.synthetic.main.fragment_base.*

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-16 15:17
 * 描    述：
 * 修订历史：
 * ================================================
 */
abstract class BaseFragment : Fragment(), View.OnClickListener, IBaseUIView {

    lateinit var mContext: Context

    var isFirstLoad: Boolean = true //是否是第一次加载

    var baseView: View? = null  //公共布局view
    var contentView: View? = null  //显示的contentView

    //当前页面状态
    var myBaseViewStatus = EBaseViewStatus.SUCCESS //页面显示状态 SUCCESS, LOADING, ERROR  分别表示正常、加载中、失败

    var loadingView: View? = null //加载中布局
    var errorView: View? = null //错误布局

    var lottieLoadingView: LottieAnimationView? = null
    var lottieErrorView: LottieAnimationView? = null

    abstract fun getLayoutID(): Int //获取布局资源文件

    abstract fun initViewModel() //初始化ViewModel

    abstract fun initView() //初始化view

    abstract fun initData() //初始化数据

    abstract fun initMainNetData() //主协议请求数据,适用于必须请求协议才能展示的页面,在initData之前请求

    abstract fun initLazyData() //懒加载数据，如：网络请求获取数据

    open fun isShowLoading(): Boolean { return false } //是否显示加载中布局,如果return true则必须重写initMainNetData方法

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        BusUtils.register(this)
        isFirstLoad = true
        baseView = inflater.inflate(R.layout.fragment_base, container, false) //用布局填充器填充布局
        contentView = inflater.inflate(getLayoutID(), container, false)
        return baseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        base_main.addView(contentView) //将显示布局添加进来
        initViewModel()
        initView() //初始化控件
        if (isShowLoading()) {
            showLoadingLayout()
        } else {
            showSuccessLayout()
        }
        initData()
        initMainNetData()
    }

    //以前的懒加载形式  FragmentPagerAdapter设置为  BEHAVIOR_SET_USER_VISIBLE_HINT 就还是以前的懒加载
    //如果            FragmentPagerAdapter设置为  BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 就要加个tag在onresume里做懒加载
//    /**
//     * 懒加载
//     * 如果不是第一次加载、没有初始化控件、不对用户可见就不加载
//     */
//    protected open fun lazyLoad() {
//        if (!isFirstLoad || !isInitView || !isShowVisible) {
//            return
//        }
//        initLazyData() //初始化数据
//        isFirstLoad = false //已经不是第一次加载
//    }
//
//    /**
//     * 是否对用户可见
//     * @param isVisibleToUser true:表示对用户可见，反之则不可见
//     */
//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (isVisibleToUser) {
//            isShowVisible = true
//            //这里根据需求，如果不要每次对用户可见的时候就加载就不要调用lazyLoad()这个方法，根据个人需求
//            lazyLoad()
//        } else {
//            isShowVisible = false
//        }
//    }

    /**
     * 显示加载中界面
     */
    override fun showLoadingLayout(){
        loadingView?:let {
            loadingView = vs_loading.inflate()
            lottieLoadingView = loadingView?.findViewById(R.id.lottie_loading_view)
            lottieLoadingView!!.setAnimation("whale.zip")
            lottieLoadingView!!.repeatCount = ValueAnimator.INFINITE
        }

        //显示界面
        base_main.visibility = View.GONE
        loadingView?.visibility = View.VISIBLE
        errorView?.visibility = View.GONE

        //播放错误动画
        lottieLoadingView!!.playAnimation()
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

        //播放错误动画
        lottieErrorView?.playAnimation()
        //暂停加载中动画防止卡顿
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()
        if(getBaseViewStatus() == EBaseViewStatus.LOADING){
            lottieLoadingView?.resumeAnimation()
        }
        if(getBaseViewStatus() == EBaseViewStatus.ERROR){
            lottieErrorView?.resumeAnimation()
        }
        if(isFirstLoad){
            initLazyData() //初始化数据
            isFirstLoad = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true
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
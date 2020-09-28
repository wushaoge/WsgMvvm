package com.wsg.up.ui.activity

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.rxLifeScope
import com.blankj.utilcode.util.LogUtils
import com.wsg.base.common.toJsonString
import com.wsg.base.ext.*
import com.wsg.base.state.VmResult
import com.wsg.up.R
import com.wsg.up.entity.ArticleData
import com.wsg.up.repository.WanAndroidRepository
import com.wsg.up.ui.activity.base.MyBaseActivity
import com.wsg.up.utils.Constants
import com.wsg.up.viewmodel.WanAndroidViewModel
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rxhttp.RxHttp
import rxhttp.toClass

class MainActivity2 : MyBaseActivity() {

    lateinit var mViewModel: WanAndroidViewModel


    override fun isShowLoading(): Boolean {
        return false
    }

    override fun getChildTitle(): String? {
        return "首页"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main2
    }

    override fun initViewModel() {
    }

    override fun initView() {
        mViewModel = ViewModelProvider(this).get(WanAndroidViewModel::class.java)

        setViewsOnClickListener(this, tv_test)

        //以下是瞎鸡儿写的
        //kotlin internal 关键字使用
        lifecycleScope.launch {

        }
        MainScope().launch {

        }

    }

    override fun initData() {
    }

    override fun initMainNetData() {
        val result = VmResult<ArticleData>()
        result.onAppLoading = {
            showLoadingDialog()
            LogUtils.e("开始2")
        }

        result.onAppSuccess = {
            LogUtils.json(it)
        }

        result.onAppError = {
            LogUtils.e(it.message)
        }

        result.onAppComplete = {
            dismissLoadingDialog()
            LogUtils.e("完成2")
        }

//        mViewModel.articleData.vmObserver2(this, result)
//
//        mViewModel.articleData.vmObserver(this){
//            onAppLoading = { showLoadingDialog(); LogUtils.e("开始") }
//            onAppSuccess = { LogUtils.json(it) }
//            onAppError = {
//                LogUtils.e(it.parseErrorString())
//                LogUtils.e(it.javaClass.typeName)
//                LogUtils.e(it.toString())
//                LogUtils.e(it.errorMsg)
//            }
//            onAppComplete = { dismissLoadingDialog(); LogUtils.e("完成") }
//        }

        mViewModel.articleData.vmObserverLoading(this){
            LogUtils.json(it)
        }

        val job = rxLifeScope.launch {
            WanAndroidRepository().getArticle(1)
        }

//        job.cancel()

        rxLifeScope.launch({
            //协程代码块，运行在UI线程
            var url = Constants.WAN_ARTICLE + "1/json"

            url = "http://gank.io/api/random/data/福利/20"

//            url = "http://www.sdf.com"

            val data = RxHttp.get(url)
                .toClass<ArticleData>()
                .await()
            //可直接更新UI
            LogUtils.e(data.toJsonString())
//            throw JsonSyntaxException("json错误")
        }, {
            //异常回调，这里可以拿到Throwable对象
            LogUtils.e(it.message)
            LogUtils.e(it.javaClass.typeName)
//            LogUtils.e(it.parseErrorString())
        }, {
            //开始回调，可以开启等待弹窗，运行在UI线程
            LogUtils.e("开始3")
        }, {
            //结束回调，可以销毁等待弹窗，运行在UI线程
            LogUtils.e("完成3")
        })



        test {
            v2 = "2333"
        }
        test2(View2())
    }


    override fun onClick(v: View?) {
        when (v) {
            tv_test -> {
                mViewModel.getArticleData(1)
            }

        }
    }


    fun test(init: View2.() -> Unit){
        init(View2())
        val t = View2()
        t.apply(init)
        t.a()
    }

    fun test2(init: View2){
        init.a()
    }

}

class View2{
    var v2: String = ""

    public fun a(){

    }
}
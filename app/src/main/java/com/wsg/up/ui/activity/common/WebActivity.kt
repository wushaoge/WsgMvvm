package com.wsg.up.ui.activity.common

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.wsg.base.state.EBaseViewStatus
import com.wsg.up.R
import com.wsg.up.ui.activity.base.MyBaseActivity
import kotlinx.android.synthetic.main.activity_web.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-27 10:24
 * 描    述：
 * 修订历史：
 * ================================================
 */
class WebActivity: MyBaseActivity() {

    var webTitle: String? = ""
    var url = "https://www.acfun.cn/"

    var isLoadSuccess = true

    override fun getChildTitle(): String? {
        return webTitle
    }

    override fun getBundleExtras(extras: Bundle?) {
        webTitle = intent.getStringExtra("title")
        url = intent.getStringExtra("url")
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_web
    }

    override fun initViewModel() {
    }

    override fun initView() {
        initProgressBar()

        webTitle?.apply { getString(R.string.app_name) }

        webView.webViewClient = object : WebViewClient() {
            // 点击超链接的时候重新在原来进程上加载URL
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                LogUtils.e(url)
                view.loadUrl(url)
                return true
            }

            override fun onReceivedError(
                webView: WebView?,
                webResourceRequest: WebResourceRequest,
                webResourceError: WebResourceError?
            ) {
                super.onReceivedError(webView, webResourceRequest, webResourceError)
                LogUtils.e("加载出错!!!")
                if (webResourceRequest.isForMainFrame) {
                    isLoadSuccess = false
                    webView?.stopLoading()
                    webView?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
                    showWebViewError()
                }
            }

            // webview加载完成
            override fun onPageFinished(view: WebView, url: String?) {
                LogUtils.e("加载完成!!!,isLoadSuccess=$isLoadSuccess")
                pb_progress.visibility = View.GONE
                if (isLoadSuccess) {
                    val title: String = view.getTitle()
                    if (title != null && title != "") {
                        updateTitle(title)
                    }
                    setBaseViewStatus(EBaseViewStatus.SUCCESS)
                } else {
                    showWebViewError()
                }
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                pb_progress.progress = newProgress
                if (newProgress >= 100) {
                    pb_progress.visibility = View.GONE
                }
            }

            override fun onReceivedTitle(webView: WebView?, s: String?) {
                super.onReceivedTitle(webView, s)
                if (!TextUtils.isEmpty(s)) {
                    updateTitle(s)
                }
            }
        }

        webView.loadUrl(url)
    }

    override fun initData() {
    }

    override fun initMainNetData() {

    }

    override fun onClick(v: View?) {
    }

    override fun backClick() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
    }

    /**
     * 初始化进度条
     */
    private fun initProgressBar() {
        pb_progress.setMax(100)
        pb_progress.setProgressDrawable(resources.getDrawable(R.drawable.web_progress))
    }

    fun updateTitle(title: String?) {
        if (isLoadSuccess) {
            setTitle(title)
        } else {
            setTitle(title)
        }
    }

    fun showWebViewError() {
        setBaseViewStatus(EBaseViewStatus.ERROR)
        setErrorText("加载网页出错")
    }

    /**
     * 设置回退
     * 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (isLoadSuccess) {
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack() // goBack()表示返回WebView的上一页面
                return true
            }
        } else {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

}
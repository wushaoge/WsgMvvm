package com.wsg.up.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wsg.base.ext.VmLiveData
import com.wsg.base.ext.launchVmRequest
import com.wsg.base.model.BaseViewModel
import com.wsg.up.entity.ArticleData
import com.wsg.up.entity.BannerData
import com.wsg.up.repository.WanAndroidRepository

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-23 09:38
 * 描    述：
 * 修订历史：
 * ================================================
 */
class WanAndroidViewModel: BaseViewModel() {
    private val wanAndroidRepository by lazy { WanAndroidRepository() }

    val articleData: VmLiveData<ArticleData> = MutableLiveData()
    val bannerData: VmLiveData<BannerData> = MutableLiveData()


    fun getArticleData(page: Int){
        launchVmRequest({
            wanAndroidRepository.getArticle(page)
        }, articleData)
    }

    fun getBannerData(){
        launchVmRequest({
            wanAndroidRepository.getBanner()
        }, bannerData)
    }


}
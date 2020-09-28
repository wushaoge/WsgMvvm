package com.wsg.up.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wsg.base.ext.VmLiveData
import com.wsg.base.ext.launchVmRequest
import com.wsg.base.model.BaseViewModel
import com.wsg.up.entity.ArticleData
import com.wsg.up.entity.BannerData
import com.wsg.up.entity.RecommendData
import com.wsg.up.repository.EyeRepository
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
class EyeViewModel: BaseViewModel() {
    private val eyeRepository by lazy { EyeRepository() }

    /**
     * 刷新和加载更多
     * 这里其实可以只用一个data
     * 为什么我用两个~~因为懒
     */
    val refreshData: VmLiveData<RecommendData> = MutableLiveData()
    val loadMoreData: VmLiveData<RecommendData> = MutableLiveData()


    fun onRefresh(){
        launchVmRequest({
            eyeRepository.onRefresh()
        }, refreshData)
    }

    fun onLoadMore(url: String){
        launchVmRequest({
            eyeRepository.loadMore(url)
        }, loadMoreData)
    }

}
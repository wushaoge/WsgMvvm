package com.wsg.up.repository

import com.wsg.base.entity.BaseData
import com.wsg.up.entity.ArticleData
import com.wsg.up.entity.BannerData
import com.wsg.up.entity.RecommendData
import com.wsg.up.utils.Constants
import rxhttp.RxHttp
import rxhttp.toClass
import java.net.ConnectException

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-23 09:44
 * 描    述：
 * 修订历史：
 * ================================================
 */
class EyeRepository {

    suspend fun getCommunityData(url: String): BaseData<RecommendData> {
        val data = RxHttp.get(url)
            .toClass<RecommendData>()
            .await()
            ?: throw ConnectException()

        //为null说明后端错误
        return BaseData(200, "", data)
    }


    suspend fun onRefresh(): BaseData<RecommendData> = getCommunityData(Constants.EYE_COMMUNITY)

    suspend fun loadMore(url: String): BaseData<RecommendData> = getCommunityData(url)
}
package com.wsg.up.repository

import com.wsg.base.entity.BaseData
import com.wsg.up.entity.ArticleData
import com.wsg.up.entity.BannerData
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
class WanAndroidRepository {

    suspend fun getArticle(page: Int): BaseData<ArticleData> {
        var url = Constants.WAN_ARTICLE + page + "/json"
//        url = "http://gank.io/api/random/data/福利/20"

        val data = RxHttp.get(url)
            .toClass<ArticleData>()
            .await()
            ?: throw ConnectException()
        //为null说明后端错误
        return BaseData(200, "", data)
    }

    suspend fun getBanner(): BaseData<BannerData> {
        var url = Constants.WAN_BANNER + "/json"

        var data = RxHttp.get(url)
            .toClass<BannerData>()
            .await()
            ?: throw ConnectException()

        return BaseData(200, "", data)
    }

}
package com.wsg.up.utils

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-23 09:41
 * 描    述：
 * 修订历史：
 * ================================================
 */
class Constants {

    companion object{

        @DefaultDomain //设置为默认域名
        const val WAN_ANDROID_BASEURL = "http://www.wanandroid.com/"


        /**
         * 玩安卓首页文章
         */
        const val WAN_ARTICLE = "article/list/"

        /**
         * 玩安卓Banner
         */
        const val WAN_BANNER = "banner"


        /**
         * 开眼推荐
         */
        const val EYE_COMMUNITY = "http://baobab.kaiyanapp.com/api/v7/community/tab/rec"

    }

}
package com.wsg.up.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.wsg.base.common.toJsonString
import kotlinx.android.parcel.Parcelize

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-23 09:47
 * 描    述：玩安卓响应实体类
 * 修订历史：
 * ================================================
 */
@Keep
@Parcelize
data class ArticleData(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}
@Keep
@Parcelize
data class Data(
    val curPage: Int,
    val datas: List<Datas>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}
@Keep
@Parcelize
data class Datas(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Parcelable {
    override fun toString(): String {
        return this.toJsonString()
    }
}
@Keep
@Parcelize
data class Tag(
    val name: String,
    val url: String
) : Parcelable

@Keep
@Parcelize
data class BannerData(
    val `data`: List<BannerChildData>,
    val errorCode: Int,
    val errorMsg: String
) : Parcelable

@Keep
@Parcelize
data class BannerChildData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
) : Parcelable






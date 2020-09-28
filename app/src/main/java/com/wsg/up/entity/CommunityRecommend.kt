package com.wsg.up.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName



/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-18 09:50
 * 描    述：
 * 修订历史：
 * ================================================
 */

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class RecommendData(
    @SerializedName("adExist")
    val adExist: Boolean?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("itemList")
    val itemList: List<RecommendItem>?,
    @SerializedName("nextPageUrl")
    val nextPageUrl: String?,
    @SerializedName("total")
    val total: Int?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class RecommendItem(
    @SerializedName("adIndex")
    val adIndex: Int?,
    @SerializedName("data")
    val `data`: DataRx?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("type")
    val type: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class DataRx(
    @SerializedName("content")
    val content: Content?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("dataType")
    val dataType: String?,
    @SerializedName("header")
    val header: Header?,
    @SerializedName("itemList")
    val itemList: List<ItemX>?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Content(
    @SerializedName("adIndex")
    val adIndex: Int?,
    @SerializedName("data")
    val `data`: DataX?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("type")
    val type: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Header(
    @SerializedName("actionUrl")
    val actionUrl: String?,
    @SerializedName("followType")
    val followType: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("iconType")
    val iconType: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("issuerName")
    val issuerName: String?,
    @SerializedName("showHateVideo")
    val showHateVideo: Boolean?,
    @SerializedName("tagId")
    val tagid: Long?,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("topShow")
    val topShow: Boolean?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class ItemX(
    @SerializedName("adIndex")
    val adIndex: Int?,
    @SerializedName("data")
    val `data`: DataXX?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("type")
    val type: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class DataX(
    @SerializedName("addWatermark")
    val addWatermark: Boolean?,
    @SerializedName("area")
    val area: String?,
    @SerializedName("checkStatus")
    val checkStatus: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("collected")
    val collected: Boolean?,
    @SerializedName("consumption")
    val consumption: Consumption?,
    @SerializedName("cover")
    val cover: Cover?,
    @SerializedName("createTime")
    val createTime: Long?,
    @SerializedName("dataType")
    val dataType: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("ifMock")
    val ifMock: Boolean?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("library")
    val library: String?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("reallyCollected")
    val reallyCollected: Boolean?,
    @SerializedName("recentOnceReply")
    val recentOnceReply: RecentOnceReply?,
    @SerializedName("releaseTime")
    val releaseTime: Long?,
    @SerializedName("resourceType")
    val resourceType: String?,
    @SerializedName("selectedTime")
    val selectedTime: Long?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tags")
    val tags: List<TagRx>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("uid")
    val uid: Long?,
    @SerializedName("updateTime")
    val updateTime: Long?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urls")
    val urls: List<String>?,
    @SerializedName("urlsWithWatermark")
    val urlsWithWatermark: List<String>?,
    @SerializedName("validateResult")
    val validateResult: String?,
    @SerializedName("validateStatus")
    val validateStatus: String?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("playUrl")
    val playUrl: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Consumption(
    @SerializedName("collectionCount")
    val collectionCount: Int?,
    @SerializedName("realCollectionCount")
    val realCollectionCount: Int?,
    @SerializedName("replyCount")
    val replyCount: Int?,
    @SerializedName("shareCount")
    val shareCount: Int?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Cover(
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("feed")
    val feed: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Owner(
    @SerializedName("actionUrl")
    val actionUrl: String?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("birthday")
    val birthday: Long?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expert")
    val expert: Boolean?,
    @SerializedName("followed")
    val followed: Boolean?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("ifPgc")
    val ifPgc: Boolean?,
    @SerializedName("job")
    val job: String?,
    @SerializedName("library")
    val library: String?,
    @SerializedName("limitVideoOpen")
    val limitVideoOpen: Boolean?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("registDate")
    val registDate: Long?,
    @SerializedName("releaseDate")
    val releaseDate: Long?,
    @SerializedName("uid")
    val uid: Long?,
    @SerializedName("university")
    val university: String?,
    @SerializedName("userType")
    val userType: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class RecentOnceReply(
    @SerializedName("actionUrl")
    val actionUrl: String?,
    @SerializedName("dataType")
    val dataType: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("nickname")
    val nickname: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class TagRx(
    @SerializedName("actionUrl")
    val actionUrl: String?,
    @SerializedName("bgPicture")
    val bgPicture: String?,
    @SerializedName("communityIndex")
    val communityIndex: Int?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("haveReward")
    val haveReward: Boolean?,
    @SerializedName("headerImage")
    val headerImage: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("ifNewest")
    val ifNewest: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("tagRecType")
    val tagRecType: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class DataXX(
    @SerializedName("actionUrl")
    val actionUrl: String?,
    @SerializedName("adTrack")
    val adTrack: List<AdTrack>?,
    @SerializedName("autoPlay")
    val autoPlay: Boolean?,
    @SerializedName("bgPicture")
    val bgPicture: String?,
    @SerializedName("dataType")
    val dataType: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("header")
    val header: HeaderX?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("label")
    val label: Label?,
    @SerializedName("labelList")
    val labelList: List<LabelX>?,
    @SerializedName("shade")
    val shade: Boolean?,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class AdTrack(
    @SerializedName("clickUrl")
    val clickUrl: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("monitorPositions")
    val monitorPositions: String?,
    @SerializedName("needExtraParams")
    val needExtraParams: List<String>?,
    @SerializedName("organization")
    val organization: String?,
    @SerializedName("playUrl")
    val playUrl: String?,
    @SerializedName("viewUrl")
    val viewUrl: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class HeaderX(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("textAlign")
    val textAlign: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Label(
    @SerializedName("card")
    val card: String?,
    @SerializedName("text")
    val text: String?
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class LabelX(
    @SerializedName("text")
    val text: String?
) : Parcelable
package com.wsg.base.common

import com.google.gson.Gson


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-14 14:38
 * 描    述：扩展类
 * 修订历史：
 * ================================================
 */

/**
 * 转换String
 */
fun Any?.toJsonString(): String {
    return Gson().toJson(this)
}

/**
 * 转换成对象
 */
inline fun <reified T> String.toJsonObject(): T {
    return Gson().fromJson(this, T::class.java)
}
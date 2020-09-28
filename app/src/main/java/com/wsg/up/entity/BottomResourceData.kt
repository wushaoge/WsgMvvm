package com.wsg.up.entity

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:22
 * 描    述：
 * 修订历史：
 * ================================================
 */
class BottomResourceData(title: String, resouce: Int, uncheckResouce: Int){
    var getTitle: () -> String = { title }
    var getResource:() -> Int = { resouce }
    var getUnCheckResource:() -> Int = { uncheckResouce }
}
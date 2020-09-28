package com.wsg.up.ui.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wsg.up.R
import com.wsg.up.entity.Datas

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2018-12-13 17:03
 * 描    述：
 * 修订历史：
 * ================================================
 */
class ArticleAdapter(private val mContext: Context) : BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.item_home_article) {
    override fun convert(
        helper: BaseViewHolder,
        item: Datas
    ) {
        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.tv_create_time, item.niceDate + "  " + item.author)
    }

}
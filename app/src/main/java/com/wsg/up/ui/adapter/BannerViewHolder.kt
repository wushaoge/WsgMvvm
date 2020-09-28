package com.wsg.up.ui.adapter

import android.view.View
import android.widget.ImageView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.wsg.up.R
import com.wsg.up.entity.BannerChildData
import com.zhpan.bannerview.BaseViewHolder

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020年07月30日11:08:53
 * 描    述：
 * 修订历史：
 * ================================================
 */
class BannerViewHolder<T>(itemView: View) :
    BaseViewHolder<BannerChildData>(itemView) {

    override fun bindData(data: BannerChildData?, position: Int, pageSize: Int) {
        val imageView = findView<ImageView>(R.id.banner_image)
        imageView.load(data?.imagePath) {
            crossfade(true)
            transformations(RoundedCornersTransformation(5f))
        }
    }
}
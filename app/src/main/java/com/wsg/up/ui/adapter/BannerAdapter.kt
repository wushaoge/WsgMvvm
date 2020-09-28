package com.wsg.up.ui.adapter

import android.view.View
import com.wsg.up.R
import com.wsg.up.entity.BannerChildData
import com.wsg.up.entity.BannerData
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * <pre>
 * Created by zhpan on 2020/4/6.
 * Description:
</pre> *
 */
class BannerAdapter() : BaseBannerAdapter<BannerChildData, BaseViewHolder<BannerChildData>>() {

    override fun onBind(
        holder: BaseViewHolder<BannerChildData>?,
        data: BannerChildData?,
        position: Int,
        pageSize: Int
    ) {
        holder!!.bindData(data, position, pageSize)
    }

    override fun createViewHolder(
        itemView: View?,
        viewType: Int
    ): BannerViewHolder<BannerChildData>? {
        return BannerViewHolder(itemView!!)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }


}
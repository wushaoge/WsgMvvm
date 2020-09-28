package com.wsg.up.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.content.ContextCompat
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wsg.base.common.toJsonString
import com.wsg.base.ext.dp2px
import com.wsg.up.R
import com.wsg.up.entity.Datas
import com.wsg.up.entity.RecommendItem
import com.wsg.up.ext.gone
import com.wsg.up.ext.invisible
import com.wsg.up.ext.setDrawable
import com.wsg.up.ext.visible
import com.wsg.up.ui.activity.TestActivity
import com.wsg.up.ui.activity.ugc.UgcDetailActivity
import java.util.ArrayList

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2018-12-13 17:03
 * 描    述：
 * 修订历史：
 * ================================================
 */
class CommunityAdapter(private val mContext: Context) : BaseQuickAdapter<RecommendItem, BaseViewHolder>(R.layout.item_find) {

    /**
     * 列表左or右间距
     */
    val bothSideSpace = dp2px(14f)

    /**
     * 列表中间内间距，左or右。
     */
    val middleSpace = dp2px(3f)

    /**
     * 通过获取屏幕宽度来计算出每张图片最大的宽度。
     *
     * @return 计算后得出的每张图片最大的宽度。
     */
    val maxImageWidth: Int
        get() {
            val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay?.getMetrics(metrics)
            val columnWidth = metrics.widthPixels
            return (columnWidth - ((bothSideSpace * 2) + (middleSpace * 2))) / 2
        }

    override fun convert(
        helper: BaseViewHolder,
        item: RecommendItem
    ) {
        val ivBgPicture = helper.getView<ImageView>(R.id.ivBgPicture)
        val tvChoiceness = helper.getView<TextView>(R.id.tvChoiceness)
        val ivPlay = helper.getView<ImageFilterView>(R.id.ivPlay)
        val ivLayers = helper.getView<ImageView>(R.id.ivLayers)
        val tvDescription = helper.getView<TextView>(R.id.tvDescription)
        val ivAvatar = helper.getView<ImageFilterView>(R.id.ivAvatar)
        val ivRoundAvatar = helper.getView<ImageFilterView>(R.id.ivRoundAvatar)
        val tvNickName = helper.getView<TextView>(R.id.tvNickName)
        val tvCollectionCount = helper.getView<TextView>(R.id.tvCollectionCount)


        helper.setGone(R.id.tvChoiceness, true)
        helper.setGone(R.id.ivPlay, true)
        helper.setGone(R.id.ivLayers, true)

        if (item.data?.content?.data?.library == "DAILY") tvChoiceness.visible()

        if (item.data?.header?.iconType ?: "".trim() == "round") {
            ivAvatar.invisible()
            ivRoundAvatar.visible()
            ivRoundAvatar.load(item.data?.content?.data?.owner?.avatar)
        } else {
            ivRoundAvatar.gone()
            ivAvatar.visible()
            ivAvatar.load(item.data?.content?.data?.owner?.avatar)
        }

        ivBgPicture.run {
            var width: Int = (if(null != item.data?.content?.data) item.data?.content?.data?.width else 0)!!
            var height = (if(null != item.data?.content?.data) item.data?.content?.data?.height else 0)!!

            val imageHeight = calculateImageHeight(width, height)
            layoutParams.width = maxImageWidth
            layoutParams.height = imageHeight
            this.load(item.data?.content?.data?.cover?.feed){
                transformations(RoundedCornersTransformation(10f))
            }
        }
        tvCollectionCount.text = item.data?.content?.data?.consumption?.collectionCount.toString()
        val drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_favorite_border_black_20dp)
        tvCollectionCount.setDrawable(drawable, 17f, 17f, 2)
        tvDescription.text = item.data?.content?.data?.description
        tvNickName.text = item.data?.content?.data?.owner?.nickname

        when (item.data?.content?.type) {
            "video" -> {
                ivPlay.visible()
                helper.itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("position", helper.adapterPosition)
                    bundle.putParcelableArrayList("data", data as ArrayList<RecommendItem>)
                    val intent = Intent(context, UgcDetailActivity::class.java)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                    (context as Activity).overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
                }
            }
            "ugcPicture" -> {
                if (!item.data.content.data?.urls.isNullOrEmpty() && item.data.content.data?.urls!!.size > 1) ivLayers.visible()
                helper.itemView.setOnClickListener {
                    var bundle = Bundle()
                    bundle.putInt("position", helper.adapterPosition)
                    bundle.putParcelableArrayList("data", data as ArrayList<Parcelable>)
                    var intent = Intent(context, UgcDetailActivity::class.java)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                    (context as Activity).overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
                }
            }
        }


    }


    /**
     * 根据屏幕比例计算图片高
     *
     * @param originalWidth   服务器图片原始尺寸：宽
     * @param originalHeight  服务器图片原始尺寸：高
     * @return 根据比例缩放后的图片高
     */
    private fun calculateImageHeight(originalWidth: Int, originalHeight: Int): Int {
        //服务器数据异常处理
        if (originalWidth == 0 || originalHeight == 0) {
            return maxImageWidth
        }
        return maxImageWidth * originalHeight / originalWidth
    }
}
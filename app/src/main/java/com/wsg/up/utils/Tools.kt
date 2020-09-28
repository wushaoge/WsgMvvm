package com.wsg.up.utils

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.wsg.up.R

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-03 15:22
 * 描    述：
 * 修订历史：
 * ================================================
 */
class Tools {

    companion object{

        /**
         * 加载列表的空布局
         * @param context
         * @return
         */
        fun getEmptyView(context: Context): View {
            val mLayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val emptyView: View =
                mLayoutInflater.inflate(R.layout.common_empty, null, true)
            val lottieLoadingView =
                emptyView.findViewById<View>(R.id.lottie_empty_view) as LottieAnimationView
            lottieLoadingView.setAnimation("halloween_smoothymon.json")
            lottieLoadingView.repeatCount = ValueAnimator.INFINITE
            lottieLoadingView.playAnimation()
            return emptyView
        }





    }

}
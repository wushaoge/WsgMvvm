package com.wsg.up.ext

import android.view.View
import android.view.animation.AlphaAnimation

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-18 15:26
 * 描    述：
 * 修订历史：
 * ================================================
 */

/**
 * 显示view
 */
fun View?.visible() {
    this?.visibility = View.VISIBLE
}

/**
 * 占位隐藏view
 */
fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

/**
 * 隐藏view
 */
fun View?.gone() {
    this?.visibility = View.GONE
}


/**
 * 显示view，带有渐显动画效果。
 *
 * @param duration 毫秒，动画持续时长，默认500毫秒。
 */
fun View?.visibleAlphaAnimation(duration: Long = 500L) {
    this?.visibility = View.VISIBLE
    this?.startAnimation(AlphaAnimation(0f, 1f).apply {
        this.duration = duration
        fillAfter = true
    })
}

/**
 * 占位隐藏view，带有渐隐动画效果。
 *
 * @param duration 毫秒，动画持续时长，默认500毫秒。
 */
fun View?.invisibleAlphaAnimation(duration: Long = 500L) {
    this?.visibility = View.INVISIBLE
    this?.startAnimation(AlphaAnimation(1f, 0f).apply {
        this.duration = duration
        fillAfter = true
    })
}
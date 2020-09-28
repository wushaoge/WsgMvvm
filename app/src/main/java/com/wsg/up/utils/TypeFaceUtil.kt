package com.wsg.up.utils

import android.graphics.Typeface
import com.wsg.base.application.BaseApplication

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-11 10:03
 * 描    述：
 * 修订历史：
 * ================================================
 */
object TypeFaceUtil {

    var typeface: Typeface? = null

    fun getEyeTypeFace(): Typeface {
        typeface = if(typeface == null){
            Typeface.createFromAsset(BaseApplication.instance.assets, "fonts/eye.ttf")
        }else{
            Typeface.DEFAULT
        }
        return typeface!!
    }


    fun getAcfunTypeFace(): Typeface {
        typeface = if(typeface == null){
            Typeface.createFromAsset(BaseApplication.instance.assets, "fonts/acfun.ttf")
        }else{
            Typeface.DEFAULT
        }
        return typeface!!
    }
}
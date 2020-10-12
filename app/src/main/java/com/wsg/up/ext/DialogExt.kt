package com.wsg.up.ext

import android.content.Context
import com.wsg.base.ui.dialog.MyProgressDialog
import com.wsg.up.ui.view.dialog.MyConfirmDialog

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-08-06 11:16
 * 描    述：
 * 修订历史：
 * ================================================
 */
fun Context.showProgressDialog(progressDialog: MyProgressDialog.() -> Unit){
    val myProgressDialog = MyProgressDialog(this)
    myProgressDialog.apply(progressDialog)
    myProgressDialog.show()
}

fun Context.showConfirmDialog(setting: ConfirmDialogCallBackClick.() -> Unit){
    val dialog = MyConfirmDialog(this)
    ConfirmDialogCallBackClick(dialog).apply(setting)
    dialog.show()
}

class ConfirmDialogCallBackClick(dialog: MyConfirmDialog){
    var dialog = dialog

    var content = ""
       set(value) {dialog.setContent(value)}

    fun onOkClick(okClick: () -> Unit){
        dialog.setOkClick(okClick)
    }

    fun onCancelClick(cancelClick: () -> Unit){
        dialog.setCancel { cancelClick() }
    }

    fun title(title:() -> String){
        dialog.setTitle(title())
    }


}
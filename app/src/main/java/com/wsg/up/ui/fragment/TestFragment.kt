package com.wsg.up.ui.fragment
import android.os.Bundle
import android.view.View
import com.wsg.up.R
import com.wsg.up.ui.fragment.base.MyBaseFragment


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:33
 * 描    述：
 * 修订历史：
 * ================================================
 */
class TestFragment: MyBaseFragment(){

    companion object{
        fun newInstance(title: String?): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            fragment.setTitle(title)
            args.putString("", title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun isShowLoading(): Boolean {
        return true
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun initViewModel() {

    }

    override fun initView() {


    }

    override fun initData() {
    }

    override fun initMainNetData() {
    }

    override fun initLazyData() {

    }


    override fun onClick(v: View?) {

    }

}
package com.wsg.up.ui.fragment
import android.os.Bundle
import android.view.View
import com.wsg.base.ext.setViewsOnClickListener
import com.wsg.base.ext.showToast
import com.wsg.up.R
import com.wsg.up.ext.showConfirmDialog
import com.wsg.up.ext.showProgressDialog
import com.wsg.up.ui.fragment.base.MyBaseFragment
import kotlinx.android.synthetic.main.fragment_channel.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:33
 * 描    述：测试用可以无视
 * 修订历史：
 * ================================================
 */
class ChannelFragment: MyBaseFragment(){

    companion object{
        fun newInstance(title: String?): ChannelFragment {
            val fragment = ChannelFragment()
            val args = Bundle()
            fragment.setTitle(title)
            args.putString("", title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_channel
    }

    override fun initViewModel() {

    }

    override fun initView() {
        setViewsOnClickListener(this, ll_zhengyi, ll_fanju, ll_donghua, ll_yinyue, ll_wudao, ll_youxi, ll_yule, ll_keji, ll_yingshi, ll_tiyu, ll_yutang, ll_shenghuo, ll_guichu, ll_xuniouxiang)
    }

    override fun initData() {
    }

    override fun initMainNetData() {
    }

    override fun initLazyData() {

    }

    override fun onClick(v: View?) {
        when (v) {
            ll_zhengyi -> {
//                MyProgressDialog(mContext, "加载中").show()
                mContext.showProgressDialog {
                    setLoadingMsg("啦啦啦")
                }
            }
            ll_fanju -> {
//                MyConfirmDialog(mContext, "拨打电话", "斯蒂芬斯蒂芬", { mContext.showToast("点击了确定") }, ::test ).show()
                mContext.showConfirmDialog {
                    title {
                        "标题题题"
                    }
                    content  = "多线程必须从"
                    onOkClick {
                        showToast("点击了确认")
                    }
                    onCancelClick {
                        test()
                    }
                }
            }
            ll_donghua -> {

            }
        }
    }



    fun test(){
        showToast("点击了取消")
    }

}
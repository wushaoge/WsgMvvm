package com.wsg.up

import android.view.KeyEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ToastUtils
import com.wsg.up.application.MyApplication
import com.wsg.up.entity.BottomResourceData
import com.wsg.up.ui.activity.base.MyBaseActivity
import com.wsg.up.ui.adapter.HomePagerAdapter
import com.wsg.up.ui.fragment.FindFragment
import com.wsg.up.ui.fragment.HomeFragment
import com.wsg.up.ui.fragment.base.MyBaseFragment
import com.wsg.up.utils.TablayoutUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MyBaseActivity() {

    override fun isShowLoading(): Boolean {
        return false
    }

    override fun getChildTitle(): String? {
        return "首页"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel() {
        
    }

    override fun initView() {

        setBackInVisible()

        var bottomList = ArrayList<BottomResourceData>()
        bottomList.add(BottomResourceData("首页", R.drawable.ic_xiaohuolong, R.drawable.ic_xiaohuolong_uncheck))
        bottomList.add(BottomResourceData("发现", R.drawable.ic_pikaqiu, R.drawable.ic_pikaqiu_uncheck))
//        bottomList.add(BottomResourceData("ACFUN", R.drawable.ic_menghuan, R.drawable.ic_menghuan_uncheck))
//        bottomList.add(BottomResourceData("ACFUN", R.drawable.ic_kabi, R.drawable.ic_kabi_uncheck))

        val mFragmentList = ArrayList<MyBaseFragment>()
        HomeFragment.newInstance("")?.let { mFragmentList.add(it) }
        FindFragment.newInstance("")?.let { mFragmentList.add(it) }
//        ChannelFragment.newInstance("")?.let { mFragmentList.add(it) }
//        ChannelFragment.newInstance("")?.let { mFragmentList.add(it) }

        vp_content.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setTitle(bottomList[position].getTitle())
            }
        })

        vp_content.adapter = HomePagerAdapter(supportFragmentManager, mFragmentList)
        vp_content.offscreenPageLimit = mFragmentList.size
        tl_tablayout.setupWithViewPager(vp_content)
        //重新设置布局
        TablayoutUtils.setHomeTablayoutCustomView(mContext, tl_tablayout, bottomList)

    }

    override fun initData() {

    }


    override fun initMainNetData() {

    }


    override fun onClick(v: View?) {

    }


    /**
     * 返回按键点击间隔
     */
    private var exitTime = 0L

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                exitTime = System.currentTimeMillis()
                ToastUtils.showLong("再按一次退出" + getString(R.string.app_name))
            } else {
                MyApplication.appExit()
                return super.onKeyDown(keyCode, event)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
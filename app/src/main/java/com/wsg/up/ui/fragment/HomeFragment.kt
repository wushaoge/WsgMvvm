package com.wsg.up.ui.fragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ColorUtils.getColor
import com.blankj.utilcode.util.LogUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.wsg.base.ext.*
import com.wsg.up.R
import com.wsg.up.entity.ArticleData
import com.wsg.up.entity.BannerChildData
import com.wsg.up.entity.Datas
import com.wsg.up.ui.activity.common.WebActivity
import com.wsg.up.ui.adapter.ArticleAdapter
import com.wsg.up.ui.adapter.BannerAdapter
import com.wsg.up.ui.fragment.base.MyBaseFragment
import com.wsg.up.utils.Tools
import com.wsg.up.viewmodel.WanAndroidViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:33
 * 描    述：
 * 修订历史：
 * ================================================
 */
class HomeFragment: MyBaseFragment(), OnRefreshListener, OnLoadMoreListener {

    lateinit var mViewModel: WanAndroidViewModel

    lateinit var mViewPager: BannerViewPager<BannerChildData, BaseViewHolder<BannerChildData>>

    lateinit var articleAdapter: ArticleAdapter

    companion object{

        fun newInstance(title: String?): HomeFragment {
            val fragment = HomeFragment()
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
        mViewModel = getViewModel()

        mViewModel.bannerData.vmObserverMain(this){
            mViewPager.refreshData(it.data)
        }
        mViewModel.articleData.vmObserverDefault(this,
            onSuccess = {
                setArticleData(it)
            },
            onComplete = {
                stopLoad(srl_content)
            }
        )
    }

    override fun initView() {
        srl_content.setOnRefreshListener(this)
        srl_content.setOnLoadMoreListener(this)

        mViewPager = baseView!!.findViewById(R.id.banner)

        mViewPager.apply {
            adapter = BannerAdapter()
            setAutoPlay(true)
            setLifecycleRegistry(lifecycle)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setIndicatorSliderGap(20)
            setIndicatorSlideMode(IndicatorSlideMode.WORM)
            setIndicatorSliderRadius(15, 15)
            setIndicatorSliderColor(getColor(R.color.red_normal_color), getColor(R.color.red_checked_color))
            setOnPageClickListener {
                LogUtils.e("第{$it}个，${(banner.getData()[it] as BannerChildData).url}")
                startActivity<WebActivity>("url" to (banner.getData()[it] as BannerChildData).url)
            }
        }.create()

        articleAdapter = ArticleAdapter(mContext)
        articleAdapter.setOnItemClickListener { adapter, view, position ->
            LogUtils.e((adapter.data[position] as Datas).link)
            startActivity<WebActivity>("url" to (adapter.data[position] as Datas).link)
        }
        rv_content.adapter = articleAdapter

    }

    override fun initData() {
    }

    override fun initMainNetData() {
        mViewModel.getBannerData()
        getArticleData()
    }

    override fun initLazyData() {

    }

    fun getArticleData(){
        mViewModel.getArticleData(pageNo)
    }

    fun setArticleData(articleData: ArticleData){
        val data= articleData?.data?.datas
        if (pageNo === 1) {
            if (data.isNotEmpty()) {
                articleAdapter.setList(data!!)
            } else {
                articleAdapter.setNewInstance(null)
                articleAdapter.setEmptyView(Tools.getEmptyView(mContext))
            }
        } else {
            articleAdapter.removeEmptyView()
            if (data.isNotEmpty()) {
                articleAdapter.addData(data)
            } else {
                mContext.showToast("暂无更多数据")
            }
        }
    }

    override fun onClick(v: View?) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        pageNo = 1
        getArticleData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        ++pageNo
        getArticleData()
    }
}
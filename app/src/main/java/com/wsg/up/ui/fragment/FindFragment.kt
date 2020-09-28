package com.wsg.up.ui.fragment
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.wsg.base.entity.BaseData
import com.wsg.base.ext.getViewModel
import com.wsg.base.ext.showToast
import com.wsg.base.ext.vmObserverDefault
import com.wsg.base.ext.vmObserverMain
import com.wsg.up.R
import com.wsg.up.ui.adapter.CommunityAdapter
import com.wsg.up.ui.fragment.base.MyBaseFragment
import com.wsg.up.utils.Tools
import com.wsg.up.viewmodel.EyeViewModel
import kotlinx.android.synthetic.main.fragment_find.rv_content
import kotlinx.android.synthetic.main.fragment_find.*


/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-27 16:33
 * 描    述：
 * 修订历史：
 * ================================================
 */
class FindFragment: MyBaseFragment(), OnRefreshListener, OnLoadMoreListener {

    lateinit var mViewModel: EyeViewModel

    lateinit var adapter: CommunityAdapter

    var nextPage = ""

    companion object{
        fun newInstance(title: String?): FindFragment {
            val fragment = FindFragment()
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
        return R.layout.fragment_find
    }

    override fun initViewModel() {
        mViewModel = getViewModel()
    }

    override fun initView() {
        srl_content.setOnRefreshListener(this)
        srl_content.setOnLoadMoreListener(this)

        adapter = CommunityAdapter(mContext)
        val mainLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mainLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        rv_content.layoutManager = mainLayoutManager
        rv_content.adapter = adapter
        rv_content.setHasFixedSize(true)
        rv_content.itemAnimator = null
    }

    override fun initData() {
        mViewModel.refreshData.vmObserverMain(this,
            onSuccess = {
                nextPage = it.nextPageUrl!!
                val data = it.itemList!!.filter { it.type == "communityColumnsCard"}
                if (data.isNotEmpty()) {
                    adapter.setList(data!!)
                } else {
                    adapter.setNewInstance(null)
                    adapter.setEmptyView(Tools.getEmptyView(mContext))
                }
            },
            onComplete = {
                stopLoad(srl_content)
            }
        )

        mViewModel.loadMoreData.vmObserverDefault(this,
            onSuccess = {
                nextPage = it.nextPageUrl!!
                adapter.removeEmptyView()
                val data = it.itemList!!.filter { it.type == "communityColumnsCard"}
                if (data.isNotEmpty()) {
                    adapter.addData(data)
                } else {
                    mContext.showToast("暂无更多数据")
                }
            },
            onComplete = {
                stopLoad(srl_content)
            }
        )
    }

    override fun initMainNetData() {
        mViewModel.onRefresh()
    }

    override fun initLazyData() {

    }

    override fun onClick(v: View?) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.onRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if(nextPage.isNotEmpty()){
            mViewModel.onLoadMore(nextPage)
        }
    }


}



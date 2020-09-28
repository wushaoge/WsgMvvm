package com.wsg.up.ext

import com.google.android.material.tabs.TabLayout

/**
 * ================================================
 * 作    者：wushaoge
 * 版    本：1.0
 * 创建日期：2020-07-30 16:07
 * 描    述：
 * 修订历史：
 * ================================================
 */
//封装
fun TabLayout.onTabSelected(tabSelect: TabSelect.() -> Unit) {
    //以下四种方式是等价的

    //第一种
//    tabSelect.invoke(TabSelect(this))
//
//    //第二种
//    tabSelect(TabSelect(this))
//
//    //第三种
//    val mTabSelect = TabSelect(this)
//    mTabSelect.apply(tabSelect) //第三种(写法1)
//    mTabSelect.tabSelect()      //第三种(写法2)

    //第四种
    TabSelect(this).tabSelect()
}

class TabSelect(tab: TabLayout) {
    private var tabReselected: ((tab: TabLayout.Tab) -> Unit)? = null
    private var tabUnselected: ((tab: TabLayout.Tab) -> Unit)? = null
    private var tabSelected: ((tab: TabLayout.Tab) -> Unit)? = null

    fun onTabReselected(tabReselected: (TabLayout.Tab.() -> Unit)) {
        this.tabReselected = tabReselected
    }

    fun onTabUnselected(tabUnselected: (TabLayout.Tab.() -> Unit)) {
        this.tabUnselected = tabUnselected
    }

    fun onTabSelected(tabSelected: (TabLayout.Tab.() -> Unit)) {
        this.tabSelected = tabSelected
    }

    init {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.apply { tabReselected?.invoke(tab) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.apply { tabUnselected?.invoke(tab) }
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.apply { tabSelected?.invoke(tab) }
            }

        })
    }
}
package com.animescrap.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId){

    private var enableItem = true

    override fun onResume() {
        super.onResume()
        enableItem = true
    }

    override fun onPause() {
        super.onPause()
        enableItem = false
    }

    protected fun ignoreReplaceFragment(itemListener: (() -> Unit)) {
        if (enableItem) {
            itemListener.invoke()
        }
    }
}
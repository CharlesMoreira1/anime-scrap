package com.animescrap.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId){

    protected var enableAddListItem = true

    override fun onResume() {
        super.onResume()
        enableAddListItem = true
    }

    override fun onPause() {
        super.onPause()
        enableAddListItem = false
    }
}
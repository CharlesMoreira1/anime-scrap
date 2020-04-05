package com.animescrap.core.rule

import com.animescrap.data.source.remote.ApiClientSoup
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebJSoupRule(private val url: String) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        ApiClientSoup.urlTest = url
        ApiClientSoup.isRunningTest = true
    }
}
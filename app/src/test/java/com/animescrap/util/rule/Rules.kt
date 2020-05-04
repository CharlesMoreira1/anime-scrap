package com.animescrap.util.rule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.RuleChain

val instantLiveDataAndCoroutineRules: RuleChain
    get() = RuleChain
        .outerRule(InstantCoroutineDispatcherRule())
        .around(InstantTaskExecutorRule())
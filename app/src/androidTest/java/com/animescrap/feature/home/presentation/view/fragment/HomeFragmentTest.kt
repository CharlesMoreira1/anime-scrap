package com.animescrap.feature.home.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.animescrap.core.rule.MockWebJSoupRule
import com.animescrap.core.util.getHtml
import com.animescrap.feature.home.presentation.robot.HomeRobot
import com.animescrap.feature.home.presentation.ui.fragment.HomeFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{

    @get:Rule
    val mockWebJSoupRule = MockWebJSoupRule(getHtml("new_episode.html"))

    @Test
    fun shouldScrolling(){
        launchFragmentInContainer<HomeFragment>()

        HomeRobot {
            waitForResourcesToLoad(2000)
            performScroll(10)
        }
    }
}
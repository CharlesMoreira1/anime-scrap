package com.animescrap.feature.home.presentation.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.animescrap.R

class HomeRobot(data: HomeRobot.() -> Unit) {

    init {
        data.invoke(this)
    }

    fun performScroll(position: Int) {
        onView(withId(R.id.recycler_home))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    fun waitForResourcesToLoad(millis: Long){
        Thread.sleep(millis)
    }
}
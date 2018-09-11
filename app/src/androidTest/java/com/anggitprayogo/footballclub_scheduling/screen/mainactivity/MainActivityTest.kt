package com.anggitprayogo.footballclub_scheduling.screen.mainactivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import com.anggitprayogo.footballclub_scheduling.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testScheduleRecyclerBehavior() {

        Thread.sleep(5000)

        //Cek apakah recycelrview dengan id rv_prev_schedule muncul
        onView(withId(R.id.rv_prev_schedule))
                .check(matches(isDisplayed()))

        onView(withId(R.id.rv_prev_schedule))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        onView(withId(R.id.rv_prev_schedule))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,click()))



        Thread.sleep(5000)

        onView(withId(R.id.tv_date))
                .check(matches(isDisplayed()))


    }

}
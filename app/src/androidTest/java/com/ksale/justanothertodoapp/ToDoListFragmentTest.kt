package com.ksale.justanothertodoapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ToDoListFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInContainer<ToDoListFragment>(
            themeResId = R.style.Base_Theme_AppCompat
        )
    }

    @Test
    fun shouldShowFABButtonTest() {
        onView(withId(R.id.fabAddToDo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowNoDataLayoutInitially() {
        onView(withId(R.id.tvNoData))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ivNoData))
            .check(matches(isDisplayed()))
    }
}
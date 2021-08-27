package com.ksale.justanothertodoapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ToDoListFragmentTest {

    lateinit var toDoFragmentScenario: FragmentScenario<ToDoListFragment>
    lateinit var navHostController: TestNavHostController

    @Before
    fun setUp() {
        // Create Test nav host controller
        navHostController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        toDoFragmentScenario = launchFragmentInContainer(themeResId = R.style.Base_Theme_AppCompat) {
            ToDoListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navHostController.setGraph(R.navigation.todo_navigation)
                        Navigation.setViewNavController(fragment.requireView(), navHostController)
                    }
                }
            }
        }
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

    @Test
    fun checkFABClick() {
        onView(withId(R.id.fabAddToDo))
            .perform(click())
    }

    @Test
    fun checkIfFABClickNavigatesToAddFragment() {
        onView(withId(R.id.fabAddToDo))
            .perform(click())
        assertTrue("Should be same destination",navHostController.currentDestination?.id == R.id.addToDoFragment)
    }
}
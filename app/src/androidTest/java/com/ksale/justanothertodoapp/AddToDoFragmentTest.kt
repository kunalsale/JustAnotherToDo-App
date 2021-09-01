package com.ksale.justanothertodoapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.*
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddToDoFragmentTest {

    lateinit var addToDoFragmentScenario: FragmentScenario<AddToDoFragment>
    lateinit var navHostController: TestNavHostController

    @Before
    fun setUp() {
        // Create Test nav host controller
        navHostController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        addToDoFragmentScenario = launchFragmentInContainer(themeResId = R.style.Base_Theme_AppCompat) {
            AddToDoFragment().also { fragment ->
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
    fun checkIfToolbarTitleIsAddToDo() {
        assertTrue(navHostController.currentDestination?.label == "Add To Do")
    }

    @Test
    fun checkIfListPageOpensOnBackPress() {
        navHostController.navigateUp()
        assertTrue(navHostController.currentDestination?.id == R.id.toDoListFragment)
    }

    @Test
    fun shouldShowTheTitleEditText() {
        onView(withId(R.id.edtTitle))
            .check(matches(allOf(isDisplayed(), withHint("Title"))))
    }

    @Test
    fun shouldShowDescriptionWihtHint() {
        onView(withId(R.id.edtDescription))
            .check(matches(allOf(isDisplayed(), withHint("Description"))))
    }

    @Test
    fun shouldShowSpinnerForPriority() {
        onView(withId(R.id.spinnerPriority))
            .check(matches(allOf(isDisplayed(), withSpinnerText("High Priority"))))
    }

    @Test
    fun shouldShowLowPriorityOnClick() {
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Low Priority")))
            .perform(click())
    }
}
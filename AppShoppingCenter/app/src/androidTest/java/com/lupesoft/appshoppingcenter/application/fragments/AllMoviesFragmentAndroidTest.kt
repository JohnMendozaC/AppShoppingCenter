package com.lupesoft.appshoppingcenter.application.fragments
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.application.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.internal.wait
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import java.security.AccessController.getContext

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AllMoviesFragmentAndroidTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun click_AddVehicle() {
        //Arrange
        Thread.sleep(300)
        onView(withId(R.id.movie_list))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        //Act
        onView(withText("Add movie"))
            .perform(click());

        //Assert
        onView(withText("Confirm"))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun click_RemoveVehicle() {
        //Arrange
        Thread.sleep(300)
        onView(withId(R.id.movie_list))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        //Act
        onView(withText("Remove movie"))
            .perform(click());

        //Assert
        onView(withText("Confirm"))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun click_AboutVehicle() {
        //Arrange
        Thread.sleep(300)
        onView(withId(R.id.movie_list))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        //Act
        onView(withText("About of movie"))
            .perform(click());

        //Assert
        onView(withId(R.id.card_team))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}
package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;

import androidx.annotation.UiThread;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class MainActivityUiTest {

    private MainActivity mainActivity;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void testCheckPageAndPageTitle()
    {
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()));
        onView(withText("Cardiac")).check(matches(isDisplayed()));
        onView(withText("Recorder")).check(matches(isDisplayed()));

    }
    @Test
    public void testCheckTabLayoutAndViewPagerDisplayed()
    {
        onView(withId(R.id.tabLayout)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.viewPagerId)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    @UiThread
    public void testSwipePage()
    {
        onView(withId(R.id.viewPagerId)).check(matches(isDisplayed()));
        onView(withText("Measure")).check(matches(isDisplayed()));
        onView(withId(R.id.viewPagerId)).perform(swipeLeft());
        onView(withId(R.id.historyLayout)).check(matches(isDisplayed()));
        onView(withText("History")).check(matches(isDisplayed()));
        onView(withId(R.id.viewPagerId)).perform(swipeRight());
    }



}

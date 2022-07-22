package com.example.cardiacrecorder;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class SplashScreenActivityActivityUiTest {

    @Rule
    public ActivityScenarioRule<SplashScreenActivity> activityRule =
            new ActivityScenarioRule<SplashScreenActivity>(SplashScreenActivity.class);
    @Test
    public void testSplashScreenText()
    {
        onView(withText("Cardiac")).check(matches(isDisplayed()));
        onView(withText("Recorder")).check(matches(isDisplayed()));
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        onView(withText("Powered by")).check(matches(isDisplayed()));
        onView(withText("KUET CSE")).check(matches(isDisplayed()));

    }

}


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
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest




public class MeasureActivityUiTest {



    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void testDataInsert()
    {
        onView(withId(R.id.floatingActionButton)).perform(click());//add a new record
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("80")); //Type systolic value
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("70")); //Type diastolic value
        onView(withId(R.id.pulse)).perform(ViewActions.typeText("80")); //Type pulse value
        Espresso.pressBack();
        onView(withId(R.id.notes)).perform(ViewActions.typeText("Resting")); //Type a comment
        Espresso.pressBack();
        onView(withId(R.id.saveButton)).perform(click());//click save button
    }

}

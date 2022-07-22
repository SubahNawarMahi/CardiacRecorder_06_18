package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
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

/**
 * class for UI test on History Activity
 */
@RunWith(JUnit4.class)
@LargeTest

public class HistoryActivityUiTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    /**
     * this method tests UI test for history activity
     * first a data is inserted and then on history activity
     * check if data exists and long press on data change
     * screen this is checked
     */
    @Test
    public void testHistoryPageUpdate()
    {
        onView(withId(R.id.floatingActionButton)).perform(click());//add a new record
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("81")); //Type systolic value
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("71")); //Type diastolic value
        onView(withId(R.id.pulse)).perform(ViewActions.typeText("91")); //Type pulse value
        Espresso.pressBack();
        onView(withId(R.id.notes)).perform(ViewActions.typeText("Resting1")); //Type a comment
        Espresso.pressBack();
        onView(withId(R.id.saveButton)).perform(click());//click save button
        onView(withId(R.id.viewPagerId)).perform(swipeLeft());//go to history page
//        onData(anything()).inAdapterView(withId(R.id.recordList)).atPosition(0).check(matches((withText("80"))));

        onData(anything()).inAdapterView(withId(R.id.recordList)).atPosition(0).perform(ViewActions.longClick());
        onView(withId(R.id.popUpDialog)).check(matches(isDisplayed()));//popup dialog will show up

        onView(withId(R.id.update)).perform(click());//press update button
        onView(withId(R.id.sfs)).check(matches(isDisplayed()));

        onView(withId(R.id.systolic)).perform(ViewActions.typeText("90")); //Type systolic value
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("79")); //Type diastolic value
        onView(withId(R.id.pulse)).perform(ViewActions.typeText("60")); //Type pulse value
        Espresso.pressBack();
        onView(withId(R.id.notes)).perform(ViewActions.typeText("Tired")); //Type a comment
        Espresso.pressBack();
        onView(withId(R.id.saveButton)).perform(click());//click save button

        onView(withId(R.id.viewPagerId)).perform(swipeLeft());

//        onData(anything()).inAdapterView(withId(R.id.recordList)).atPosition(0).
//                check(matches((withText("Tired")))); //Check the content on the list

        onData(anything()).inAdapterView(withId(R.id.recordList)).atPosition(0).perform(ViewActions.longClick());
        onView(withId(R.id.popUpDialog)).check(matches(isDisplayed()));//popup dialog will show up

        onView(withId(R.id.delete)).perform(click());//press delete button



    }
}
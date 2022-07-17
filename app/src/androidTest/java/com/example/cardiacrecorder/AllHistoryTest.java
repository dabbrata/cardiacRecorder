package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class AllHistoryTest {
    @Rule
    public ActivityScenarioRule<AllHistory> activityRule =
            new ActivityScenarioRule<>(AllHistory.class);

    /**
     * ui test for all history page..,
     * the back button is checked.
     * then back to main activity
     * then click history button to move to all history activity again
     */
    @Test
    public void testItem(){
        onView(withId(R.id.recId)).check(matches(isDisplayed()));
        onView(withId(R.id.imageBackButton)).perform(click());
        onView(withId(R.id.button3)).perform(click());
    }

}

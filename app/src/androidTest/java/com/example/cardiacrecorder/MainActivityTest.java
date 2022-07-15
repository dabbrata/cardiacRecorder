package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testUserInfo()
    {
        onView(withId(R.id.textInputEditText)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.textInputEditText2)).perform(ViewActions.typeText("90"));
        onView(withId(R.id.textInputEditText3)).perform(ViewActions.typeText("140"));
        onView(withId(R.id.textInputEditText4)).perform(ViewActions.typeText("60"));
        onView(withId(R.id.button2)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.refreshButton)).perform(click());
        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.recId)).check(matches(isDisplayed()));
    }
}

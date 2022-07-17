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

/**
 * ui testing of main activity to take input from users.
 */
public class MainActivityTest {
    /**
     * After main activity it will go to history activity
     * The recyler view ui test of history activity causes some errors
     * After providing correct code,the app was not run
     * Thats why the profile activity still remain without ui testing in this project
     * Because the recyler view are related with profile activity and
     * Profile activity related with update activity
     * Becasue some value are passes from one to another activity
     * As a result the issues of ui test of recyler view causes another issues to another activity.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * here firstly check input field and teke input of user name
     * then the other input field bpm,systolic pressure and dyastolic pressure will be filled
     * the pressback inbuild function of expresso framework will be called
     * after doing all these procedures, it will check to go to all history activity
     * If the id of allhistory activity is similar with displayed activity ,then
     * it will be considered correct of the ui design of the application
     * @return void type testUserInfo method
     *
     */

    @Test
    public void testUserInfo()
    {
        onView(withId(R.id.textInputEditText)).perform(ViewActions.typeText("Erikson"));
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

    /**
     * In mainactivity page, the cross button denote the exit of the application
     * If the cross image button is clicked , then it will checked with the id of the button
     * if there are some problems the system will be crushes
     * @return void testExit method
     */
    @Test
    public void testExit()
    {
        onView(withId(R.id.crossButton)).perform(click());
    }

    /**
     * In mainactivity page, the android backpress button denote the exit of the application.
     * If the back button is clicked , then it will be called from Espresso framework and show alert
     * for exit the app.
     * if there are some problems in the alert dialog, the system will be crushes.
     * @return void testExitBack method
     */
    @Test
    public void testExitBack()
    {
        Espresso.pressBack();
    }
}

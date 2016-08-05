package com.example.weatherapp;

import android.app.Application;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.*;//allOf;
import static android.support.test.espresso.Espresso.*;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class ApplicationTest extends ApplicationTestCase<Application> {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    public ApplicationTest() {
        super(Application.class);
    }

    /*@Test
    public void sleepAfterLaunch(){
        SystemClock.sleep(1000);
    }

    public void sleepAfterLaunch2(){
        SystemClock.sleep(1000);
    }*/

    @Test
    public void testForecastDisplayed(){
        /*onView(withId(R.id.button_for_settings))      // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion*/
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform (click());
        onView(withText("Forecast Preference")).check(matches(isDisplayed()));
    }

    @Test
    public void testChangeSaved(ViewAction click){
        /*onView(withId(R.id.button_for_settings))      // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion*/
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(click());
        onView(withText("Set location")).perform(click());
        onView(withId(android.R.id.edit))
                .perform(click())
                .perform(clearText())
                .perform(typeText("Mississauga, Ontario"));

        onView(withId(android.R.id.button1)).perform(click());
        onView(withText("Mississauga, Ontario")).check(matches(isDisplayed()));
    }

    @Test
    public void testSnackbarDisplayed(){
        onView(withText("Tomorrow")).perform(swipeDown());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Mississauga,CA")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTemperatureOnData() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(click());
        onView(withText("Temperature Units")).perform(click());
        //onView(withText("Imperial")).perform(click());
        onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo("STR"), is("Imperial"))))
                .perform(click());
        onView(withText("Imperial")).check(matches(isDisplayed()));
    }


}
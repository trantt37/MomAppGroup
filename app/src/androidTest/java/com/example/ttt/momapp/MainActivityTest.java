package com.example.ttt.momapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ttt.momapp", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule<MainActivity> mButtonTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void MainActivityItemsPresent() throws Exception{
        onView(withId(R.id.items))
                .check(matches(isDisplayed()));
    }
    @Test
    public void MainActivityEditPresent() throws Exception{
        onView(withId(R.id.edit))
                .check(matches(isDisplayed()));
    }
    @Test
    public void MainActivityAddPresent() throws Exception{
        onView(withId(R.id.add))
                .check(matches(isDisplayed()));
    }

}
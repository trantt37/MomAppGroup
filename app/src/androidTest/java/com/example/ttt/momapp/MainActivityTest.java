package com.example.ttt.momapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
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
    //test to show dropdown menu "clothing" and all mandatory fields filled

    @Test

    public void clothingCategoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Dress"));

        onView(withId(R.id.quantity_editText)).perform(typeText("1"));

        onView(withId(R.id.location_editText)).perform(typeText("Amanda’s closet"));

        onView(withId(R.id.price_editText)).perform(scrollTo()).perform(typeText("12.73"));

        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());

        //onView(withId(R.id.categoryDropdown)).atPosition(2).perform(click());
        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Clothing"))).perform(click());
        onView(withId(R.id.categoryDropdown)).check(matches(withSpinnerText(containsString("Clothing"))));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Amanda’s prom dress"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



//test to show dropdown menu "electronics" and all mandatory fields filled

    @Test

    public void electronicCategoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("iPhone"));

        onView(withId(R.id.quantity_editText)).perform(typeText("1"));

        onView(withId(R.id.location_editText)).perform(typeText("bedside table"));

        onView(withId(R.id.price_editText)).perform(scrollTo()).perform(typeText("200.00"));

        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());

        //onView(withId(R.id.categoryDropdown)).atPosition(3).perform(click());
        onView(withId(R.id.categoryDropdown)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Electronics"))).perform(click());
        onView(withId(R.id.categoryDropdown)).check(matches(withSpinnerText(containsString("Electronics"))));
        onView(withId(R.id.miscNotes_editText)).perform(typeText("Anisha’s iPhone"));

        onView(withId(R.id.Bsave)).perform(click());

    }



//test to show dropdown menu "other" and all mandatory fields filled

    @Test

    public void otherCategoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("tissues"));

        onView(withId(R.id.quantity_editText)).perform(typeText("5"));

        onView(withId(R.id.location_editText)).perform(typeText("bathroom"));

        onView(withId(R.id.price_editText)).perform(typeText("5.00"));

        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());

        //onView(withId(R.id.categoryDropdown)).atPosition(4).perform(click());
        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Other"))).perform(click());
        onView(withId(R.id.categoryDropdown)).check(matches(withSpinnerText(containsString("Other"))));
        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Timmy’s tissues"));

        onView(withId(R.id.Bsave)).perform(click());

    }



//test to show dropdown menu in initial "food" category

    @Test

    public void foodCategoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Banana"));

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.location_editText)).perform(typeText("kitchen counter"));

        onView(withId(R.id.price_editText)).perform(typeText("2.73"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Anisha’s Favorite Bananas"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



    @Test

    public void addTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Dress"));

        onView(withId(R.id.BaddItem)).perform(scrollTo()).perform(click());

        onView(withId(R.id.quantity_editText)).perform(typeText("1"));

        onView(withId(R.id.location_editText)).perform(typeText("Amanda’s closet"));

        onView(withId(R.id.price_editText)).perform(typeText("12.73"));

        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());

        //onView(withId(R.id.categoryDropdown)).atPosition(2).perform(click());
        onView(withId(R.id.categoryDropdown)).perform(scrollTo()).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Clothing"))).perform(click());
        onView(withId(R.id.categoryDropdown)).check(matches(withSpinnerText(containsString("Clothing"))));
        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Amanda’s prom dress"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



//test to show subtract feature

    @Test

    public void subtractTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Banana"));

        onView(withId(R.id.quantity_editText)).perform(typeText("5"));

        onView(withId(R.id.BsubtractItem)).perform(click());

        onView(withId(R.id.location_editText)).perform(typeText("kitchen counter"));

        onView(withId(R.id.price_editText)).perform(typeText("2.73"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Anisha’s Favorite Bananas"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



//test to show that name field is mandatory (look for popup)

    @Test

    public void nameMandatoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.location_editText)).perform(typeText("fridge"));

        onView(withId(R.id.price_editText)).perform(typeText("2.73"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("for the birthday"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



//test to show that quantity field does not need to be altered

    @Test

    public void quantityZeroTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Apple"));

        onView(withId(R.id.location_editText)).perform(typeText("fridge"));

        onView(withId(R.id.price_editText)).perform(typeText("2.73"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Amanda’s Favorite Apples"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }





//test to show that location is mandatory

    @Test

    public void locationMandatoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Brownies"));

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.BaddItem)).perform(scrollTo()).perform(click());

        onView(withId(R.id.price_editText)).perform(typeText("10.00"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Bharat’s Brownies"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }



//test to show that price is mandatory

    @Test

    public void priceMandatoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Apple"));

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.BaddItem)).perform(scrollTo()).perform(click());

        onView(withId(R.id.location_editText)).perform(typeText("fridge"));

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Amanda’s Favorite Apples"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

    }





//test to show that misc is not mandatory

    @Test

    public void miscNotMandatoryTest(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Apple"));

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.BaddItem)).perform(scrollTo()).perform(click());

        onView(withId(R.id.location_editText)).perform(typeText("fridge"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

        onView(withId(R.id.expirationDate_editText)).perform(scrollTo()).perform(typeText("4/8/18"));

    }



//test to show that expiration date is not mandatory

    @Test

    public void miscNotMandatoryTest2(){

        onView(withId(R.id.add)).perform(click());

        onView(withId(R.id.name_editText)).perform(typeText("Apple"));

        onView(withId(R.id.quantity_editText)).perform(typeText("2"));

        onView(withId(R.id.BaddItem)).perform(scrollTo()).perform(click());

        onView(withId(R.id.location_editText)).perform(scrollTo()).perform(typeText("fridge"));

        onView(withId(R.id.Bsave)).perform(scrollTo()).perform(click());

        onView(withId(R.id.miscNotes_editText)).perform(scrollTo()).perform(typeText("Amanda’s Favorite Apples"));

    }
}
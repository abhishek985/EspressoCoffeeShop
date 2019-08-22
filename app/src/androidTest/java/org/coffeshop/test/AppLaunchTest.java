package org.coffeshop.test;

import android.support.test.rule.ActivityTestRule;

import com.noralynn.coffeeShop.beveragelist.BeverageListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;

public class AppLaunchTest {


    @Rule
    public ActivityTestRule<BeverageListActivity> mactivity = new ActivityTestRule<>(
            BeverageListActivity.class);


    @Test
    public void testLaunch() {
        onView(withContentDescription("Fox mug")).check(matches(isDisplayed()));
        System.out.println("App launched successfully");
    }

}

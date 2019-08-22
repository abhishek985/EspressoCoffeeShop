package org.coffeshop.test;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.noralynn.coffeeShop.beveragelist.BeverageListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

public class CoffeeTypeListTest {

    @Rule
    public ActivityTestRule<BeverageListActivity> mactivity = new ActivityTestRule<>(
            BeverageListActivity.class);

    @Test
    public void coffeeList(){

        onView(allOf(withText("Café au lait"))).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(allOf(withText("Caffè latte"))).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(allOf(withText("Cà phê sữa đá"))).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(allOf(withText("Flat white"))).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

    }

    @Test
    public void lastCoffeeNotDisplayed() {
        onView(withText("Latte macchiato")).check(matches(not(isDisplayed())));
    }

}

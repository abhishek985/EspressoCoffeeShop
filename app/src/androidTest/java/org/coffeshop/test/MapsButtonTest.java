package org.coffeshop.test;

import android.app.Activity;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;

import com.noralynn.coffeeShop.R;
import com.noralynn.coffeeShop.beveragelist.BeverageListActivity;
import com.noralynn.coffeeShop.coffeeshoplist.CoffeeShopListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


public class MapsButtonTest {

    private IdlingResource mIdlingResource;


    @Rule
    public ActivityTestRule<BeverageListActivity> mactivity = new ActivityTestRule<>(
            BeverageListActivity.class);




    @Test
    public void mapButton() throws InterruptedException {

        onView(withId(R.id.map_fab)).perform(click());

        Activity currentActivity = getCurrentActivity();



        System.out.println("current activity "+(currentActivity instanceof CoffeeShopListActivity) );


        IdlingRegistry.getInstance().register(((CoffeeShopListActivity)currentActivity).getCoffeeShopsIdlingResource());


        System.out.println("current activity after wait "+(currentActivity instanceof CoffeeShopListActivity) );

        onView(withText("Sorry! I can't find any coffee shops at this time.")).check(matches(isDisplayed()));

        IdlingRegistry.getInstance().unregister(((CoffeeShopListActivity)currentActivity).getCoffeeShopsIdlingResource());

    }


    public static Activity getCurrentActivity() {
        final Activity[] activity = new Activity[1];

        onView(isRoot()).check((view, noViewFoundException) -> {

            View checkedView = view;

            while (checkedView instanceof ViewGroup && ((ViewGroup) checkedView).getChildCount() > 0) {

                checkedView = ((ViewGroup) checkedView).getChildAt(0);

                if (checkedView.getContext() instanceof Activity) {
                    activity[0] = (Activity) checkedView.getContext();
                    return;
                }
            }
        });
        return activity[0];
    }
}


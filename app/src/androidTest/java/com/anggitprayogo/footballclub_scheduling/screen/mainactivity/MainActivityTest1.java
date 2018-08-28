package com.anggitprayogo.footballclub_scheduling.screen.mainactivity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.anggitprayogo.footballclub_scheduling.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest1() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.menu_next),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_bottom),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.menu_favourite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_bottom),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.menu_prev),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_bottom),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction _CardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.rv_prev_schedule),
                                childAtPosition(
                                        withClassName(is("org.jetbrains.anko._LinearLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        _CardView.perform(click());

        pressBack();

        ViewInteraction _CardView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.rv_prev_schedule),
                                childAtPosition(
                                        withClassName(is("org.jetbrains.anko._LinearLayout")),
                                        0)),
                        1),
                        isDisplayed()));
        _CardView2.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.menu_favourite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_bottom),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction _CardView3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("android.support.v4.widget.SwipeRefreshLayout")),
                                0),
                        0),
                        isDisplayed()));
        _CardView3.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction bottomNavigationItemView5 = onView(
                allOf(withId(R.id.menu_prev),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation_bottom),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView5.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep2Activity;
import com.jupitervn.uitest.espresso.actions.ExtraViewActions;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/26/16.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep2ActivityTest {
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule<SignUpStep2Activity> mActivityRule = new ActivityTestRule<>(SignUpStep2Activity.class);
    public static final String[] SPORTS_STRING = new String[]{"Football", "Tennis", "Ping pong", "Swimming",
            "Volleyball", "Basketball"};

    @Test
    public void testUI_ShouldDisplaySalarySeekbar() throws Exception {
        getSalaraySeekbar().check(matches(isDisplayed()));
        onView(withText(containsString("Your salary"))).check(matches(isDisplayed()));
        onView(withText("$0")).check(matches(isDisplayed()));
        onView(withText("$100000")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_SalaryRangeShouldIncreaseBy100() throws Exception {
        getSalaraySeekbar().perform(ExtraViewActions.setSeekbarProgresss(10));
        onView(withText("Your salary: 10000 dollars")).check(matches(isDisplayed()));
        getSalaraySeekbar().perform(ExtraViewActions.setSeekbarProgresss(32));
        onView(withText("Your salary: 32000 dollars")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_ShouldHaveDoneButton() throws Exception {
        onView(withText("DONE")).check(matches(isDisplayed())).check(matches(isClickable()));
    }

    @Test
    public void testShouldHaveSportsCheckBox() throws Exception {
        for (String sport : SPORTS_STRING) {
            onView(withText(sport)).check(matches(isDisplayed())).check(matches(withClassName(containsString("CheckBox")))).check(matches(isClickable()));
        }
    }

    @Test
    public void testUI_shouldSelectSportsBeforePressDone() throws Exception {
        pressDoneButton();
        onView(withText("Step2")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_shouldCloseAppIfSportsIsChosen() throws Exception {
        onView(withText(SPORTS_STRING[0])).check(matches(isDisplayed())).perform(click());
        pressDoneButton();
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("forget to launch"));
    }

    private void pressDoneButton() {
        onView(withText("DONE")).perform(click());
    }


    private ViewInteraction getSalaraySeekbar() {
        return onView(ViewMatchers.withClassName(Matchers.containsString("SeekBar")));
    }
}

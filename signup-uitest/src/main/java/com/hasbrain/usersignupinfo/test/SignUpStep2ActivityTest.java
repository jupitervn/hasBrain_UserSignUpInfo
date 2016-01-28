package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep2Activity;
import com.hasbrain.usersignupinfo.test.page.Step2Page;
import com.jupitervn.uitest.espresso.ScreenshotIfFailRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/26/16.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep2ActivityTest {

    @Rule
    public ScreenshotIfFailRule<SignUpStep2Activity> mActivityRule = new ScreenshotIfFailRule<>(SignUpStep2Activity.class,
            new ActivityTestRule<>(SignUpStep2Activity.class));
    private Step2Page step2Page;

    @Before
    public void setUp() throws Exception {
        step2Page = new Step2Page();

    }

    @Test
    public void testUI_ShouldDisplaySalarySeekbar() throws Exception {
        step2Page.getSalaraySeekbar().check(matches(isDisplayed()));
        onView(withText(containsString("Your salary"))).check(matches(isDisplayed()));
        onView(withText("$0")).check(matches(isDisplayed()));
        onView(withText("$100000")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_SalaryRangeShouldIncreaseBy100() throws Exception {
        step2Page.setSalaryPercent(10);
        onView(withText("Your salary: 10000 dollars")).check(matches(isDisplayed()));
        step2Page.setSalaryPercent(32);
        onView(withText("Your salary: 32000 dollars")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_ShouldHaveDoneButton() throws Exception {
        step2Page.getDoneButton().check(matches(isDisplayed())).check(matches(isClickable()));
    }

    @Test
    public void testShouldHaveSportsCheckBox() throws Exception {
        for (String sport : Step2Page.SPORTS_STRING) {
            step2Page.checkSportWithString(sport);
        }
    }

    @Test
    public void testUI_shouldSelectSportsBeforePressDone() throws Exception {
        step2Page.pressDoneButton();
        step2Page.isPageDisplay();
        onView(withText("Step 3")).check(doesNotExist());
    }

    @Test
    public void testUI_shouldShowStep3IfSportsChecked() throws Exception {
        onView(withText(Step2Page.SPORTS_STRING[0])).check(matches(isDisplayed())).perform(click());
        step2Page.isPageDisappear();
        onView(withText("Step 3")).check(matches(isDisplayed()));
    }



}

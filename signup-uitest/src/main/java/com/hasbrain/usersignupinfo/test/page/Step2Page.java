package com.hasbrain.usersignupinfo.test.page;

import com.jupitervn.uitest.espresso.actions.ExtraViewActions;

import org.hamcrest.Matchers;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/28/16.
 */
public class Step2Page {

    public static final String[] SPORTS_STRING = new String[]{"Football", "Tennis", "Ping pong", "Swimming",
            "Volleyball", "Basketball"};
    public static final String PAGE_TITLE = "Step 2";

    public void setSalaryPercent(int percent) {
        getSalaraySeekbar().perform(ExtraViewActions.setSeekbarProgresss(percent));

    }

    public ViewInteraction getSalaraySeekbar() {
        return onView(ViewMatchers.withClassName(Matchers.containsString("SeekBar")));
    }

    public void pressDoneButton() {
        getDoneButton().perform(click());
    }

    public ViewInteraction getDoneButton() {
        return onView(withText(equalToIgnoringCase("DONE")));
    }

    public void isPageDisplay() {
        onView(withText("Step 2")).check(matches(isDisplayed()));
    }

    public void isPageDisappear() {
        onView(withText("Step 2")).check(doesNotExist());
    }

    public void inputDefaultDetail() {
        checkSportWithString(SPORTS_STRING[2]);
    }

    public void checkSportWithString(String sport) {
        withSportCheckBox(sport).check(matches(isClickable())).perform(click());
    }

    @NonNull
    private ViewInteraction withSportCheckBox(String sport) {
        return onView(withText(sport)).check(matches(isDisplayed())).check(matches(withClassName(containsString("CheckBox"))));
    }

    public void isSportChecked(String sport) {
        withSportCheckBox(sport).check(matches(isChecked()));
    }
}

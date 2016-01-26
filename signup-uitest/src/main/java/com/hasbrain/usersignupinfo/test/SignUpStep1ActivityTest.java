package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep1Activity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAttributeAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/7/15.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep1ActivityTest {

    @Rule
    public ActivityTestRule<SignUpStep1Activity> mActivityRule = new ActivityTestRule<>(SignUpStep1Activity.class);

    @Test
    public void testUI_ShouldContainsTextWithHintFirstName() throws Exception {
        onView(withHint("First name")).check(matches(isDisplayed()));
        onView(withHint("Last name")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_FirstNameAndLastNameShouldHaveEqualWidth() throws Exception {
        onView(withHint("First name")).check(ViewAttributeAssertions.hasEqualWidth(withHint("Last name")));
//        onView(withHint("First name")).check(PositionAssertions.isLeftAlignedWith(withHint("Last name")));
//        onView(withHint("First name")).check(PositionAssertions.isRightAlignedWith(withHint("Last name")));
    }

    @Test
    public void testUI_ShouldContainsEmailEditText() throws Exception {
        onView(withHint("Email")).check(matches(isDisplayed()));
        onView(withHint("Email")).check(matches(withInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS|InputType.TYPE_CLASS_TEXT)));
    }

    @Test
    public void testUI_ShouldContainsPhoneNumberEditText() throws Exception {
        onView(withHint("Phone number")).check(matches(isDisplayed()));
        onView(withHint("Phone number")).check(matches(withInputType(InputType.TYPE_CLASS_PHONE)));
    }

    @Test
    public void testUI_ShouldContainsGenderRadioButton() throws Exception {
        onView(withText("Male")).check(matches(isDisplayed())).check(matches(ViewMatchers.withClassName(
                Matchers.containsString("RadioButton"))));
        onView(withText("Female")).check(matches(isDisplayed())).check(matches(ViewMatchers.withClassName(
                Matchers.containsString("RadioButton"))));
    }

    @Test
    public void testUI_ShouldContainsNextButton() throws Exception {
        onView(withText("NEXT")).check(matches(isDisplayed())).check(matches(isClickable()));
    }

    @Test
    public void testUI_OnlyMaleOrFemaleCanBeChosen() throws Exception {
        onView(withText("Male")).perform(ViewActions.click());
        onView(withText("Male")).check(matches(isChecked()));
        onView(withText("Female")).check(matches(isNotChecked()));

        onView(withText("Female")).perform(ViewActions.click());
        onView(withText("Female")).check(matches(isChecked()));
        onView(withText("Male")).check(matches(isNotChecked()));
    }




}

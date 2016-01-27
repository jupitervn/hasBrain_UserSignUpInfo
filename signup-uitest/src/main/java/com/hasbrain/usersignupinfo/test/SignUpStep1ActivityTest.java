package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep1Activity;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAttributeAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.jupitervn.uitest.espresso.matchers.ExtraViewMatchers.hasSafelyErrorText;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/7/15.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep1ActivityTest {

    @Rule
    public ActivityTestRule<SignUpStep1Activity> mActivityRule = new ActivityTestRule<>(SignUpStep1Activity.class);

    @Test
    public void testUI_ShouldContainsTextWithHintFirstName() throws Exception {
        getFirstNameView().check(matches(isDisplayed()));
        getLastNameView().check(matches(isDisplayed()));
    }

    @Test
    public void testUI_FirstNameAndLastNameShouldHaveEqualWidth() throws Exception {
        getFirstNameView().check(ViewAttributeAssertions.hasEqualWidth(withHint("Last name")));
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

    @Test
    public void testUI_ShouldOpenStep2WhenPressNext() throws Exception {
        inputFirstName("First name");
        inputLastName("Last name");
        inputEmail("abc@gmail.com");
        inputPhoneNumber("01234568789");
        onView(withText("Male")).perform(ViewActions.click());
        pressDoneButton();
        onView(withText("Step 2")).check(matches(isDisplayed()));
    }


    @Test
    public void testUI_pressBackShouldShowStep1() throws Exception {
        inputFirstName("First name");
        inputLastName("Last name");
        inputEmail("abc@gmail.com");
        inputPhoneNumber("01234568789");
        onView(withText("Male")).perform(ViewActions.click());
        pressDoneButton();
        onView(withText("Step 2")).check(matches(isDisplayed()));
        pressBack();
        onView(withText("Step1")).check(matches(isDisplayed()));
    }

    @Test
    public void testUI_ShouldNotOpenStep2IfGenderIsNotChosen() throws Exception {
        inputFirstName("First name");
        inputLastName("Last name");
        inputEmail("abc@gmail.com");
        inputPhoneNumber("01234568789");
        pressDoneButton();
        onView(withText("Step 2")).check(doesNotExist());
    }

    @Test
    public void testUI_ShouldShowErrorIfFirstNameEmpty() throws Exception {
        Spoon.screenshot(mActivityRule.getActivity(), "before_press_done");
        pressDoneButton();
        Spoon.screenshot(mActivityRule.getActivity(), "after_press_done");
        onView(hasSafelyErrorText("You must enter FirstName")).check(matches(isDisplayed()));
        inputFirstName("First name");
        onView(hasSafelyErrorText("You must enter FirstName")).check(doesNotExist());
    }

    @Test
    public void testUI_ShouldShowErrorIfLastNameEmpty() throws Exception {
        pressDoneButton();
        Spoon.screenshot(mActivityRule.getActivity(), "no_last_name");
        onView(hasSafelyErrorText("You must enter LastName")).check(matches(isDisplayed()));
        inputFirstName("last name");
        onView(hasSafelyErrorText("You must enter LastName")).check(doesNotExist());
    }

    @Test
    public void testUI_ShouldShowErrorIfEmailEmpty() throws Exception {
        pressDoneButton();
        onView(hasSafelyErrorText("You must enter Email")).check(matches(isDisplayed()));
        inputEmail("email");
        onView(hasSafelyErrorText("You must enter Email")).check(doesNotExist());
    }

    @Test
    public void testUI_ShouldShowErrorIfPhoneNumberEmpty() throws Exception {
        pressDoneButton();
        onView(hasSafelyErrorText("You must enter PhoneNumber")).check(matches(isDisplayed()));
        inputPhoneNumber("0978123712");
        onView(hasSafelyErrorText("You must enter PhoneNumber")).check(doesNotExist());
    }

    @Test
    public void testUI_ShouldShowErrorIfEmailInvalid() throws Exception {
        inputEmail("abc@gmail");
        pressDoneButton();
        onView(hasSafelyErrorText("Email is invalid")).check(matches(isDisplayed()));
        clearEmailText();
        Spoon.screenshot(mActivityRule.getActivity(), "after_clear_text");
        onView(hasSafelyErrorText("Email is invalid")).check(doesNotExist());
        inputEmail("abc@g");
        pressDoneButton();
        onView(hasSafelyErrorText("Email is invalid")).check(matches(isDisplayed()));
    }





    private void clearEmailText() {
        onView(withHint("Email")).perform(clearText());
    }


    private void inputPhoneNumber(String phoneNumber) {
        onView(withHint("Phone number")).perform(typeText(phoneNumber));
    }

    private void inputEmail(String email) {
        onView(withHint("Email")).perform(typeText(email));
    }

    private void inputLastName(String lastname) {
        getLastNameView().perform(typeText(lastname));
    }

    private void inputFirstName(String firstname) {
        getFirstNameView().perform(typeText(firstname));
    }

    private void pressDoneButton() {
        onView(withText("NEXT")).perform(click());
    }

    private ViewInteraction getLastNameView() {
        return onView(withHint("Last name"));
    }

    private ViewInteraction getFirstNameView() {
        return onView(withHint("First name"));
    }
}

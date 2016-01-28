package com.hasbrain.usersignupinfo.test.page;

import android.support.annotation.IntDef;
import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/28/16.
 */
public class Step1Page {

    public static final int MALE_TYPE = 0x0;
    public static final int FEMALE_TYPE = 0x1;
    public static final String PAGE_TITLE = "Step 1";

    public void clearEmailText() {
        onView(withHint("Email")).perform(clearText());
    }

    public void inputPhoneNumber(String phoneNumber) {
        onView(withHint("Phone number")).perform(typeText(phoneNumber));
    }

    public void inputEmail(String email) {
        onView(withHint("Email")).perform(typeText(email));
    }

    public void inputLastName(String lastname) {
        withLastNameViewByHint().perform(typeText(lastname));
    }

    public void inputFirstName(String firstname) {
        withFirstNameViewByHint().perform(typeText(firstname));
    }

    public void pressDoneButton() {
        onView(withText(equalToIgnoringCase("NEXT"))).perform(click());
    }

    public ViewInteraction withLastNameViewByHint() {
        return onView(withHint("Last name"));
    }

    public ViewInteraction withFirstNameViewByHint() {
        return onView(withHint("First name"));
    }

    public ViewInteraction withEmailNameViewByHint() {
        return onView(withHint("Email"));
    }

    public void chooseGender(@GENDER_TYPE int genderType) {
        if (genderType == MALE_TYPE) {
            onView(withText(equalToIgnoringCase("Male"))).perform(click());
        } else if (genderType == FEMALE_TYPE) {
            onView(withText(equalToIgnoringCase("Female"))).perform(click());
        }
    }

    public void inputDetail(String firstName, String lastName, String email, String phoneNumber, @GENDER_TYPE int genderType) {
        inputFirstName(firstName);
        inputLastName(lastName);
        inputEmail(email);
        inputPhoneNumber(phoneNumber);
        chooseGender(genderType);
    }

    public void inputDefaultDetail() {
        inputDetail("firstname", "lastname", "abc@gmail.com", "0123781234", MALE_TYPE);
    }

    @IntDef({MALE_TYPE, FEMALE_TYPE})
    public @interface GENDER_TYPE{}
}

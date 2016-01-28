package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep1Activity;
import com.hasbrain.usersignupinfo.test.page.Step1Page;
import com.hasbrain.usersignupinfo.test.page.Step2Page;
import com.hasbrain.usersignupinfo.test.page.Step3Page;
import com.jupitervn.uitest.espresso.ScreenshotIfFailRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.Is.is;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/28/16.
 */
@RunWith(AndroidJUnit4.class)
public class UserFlowTest {

    @Rule
    public ScreenshotIfFailRule<SignUpStep1Activity> mActivityRule = new ScreenshotIfFailRule<>(SignUpStep1Activity.class,
            new ActivityTestRule<>(SignUpStep1Activity.class));
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Step1Page step1Page;
    private Step2Page step2Page;
    private Step3Page step3Page;

    @Before
    public void setUp() throws Exception {
        step1Page = new Step1Page();
        step2Page = new Step2Page();
        step3Page = new Step3Page();
    }

    @Test
    public void testUI_ShouldOpenStep2WhenPressNext() throws Exception {
        step1Page.inputDefaultDetail();
        step1Page.pressDoneButton();
        checkIfStep2IsShowing();
    }

    @Test
    public void testUI_pressBackShouldShowStep1WithDetail() throws Exception {
        step1Page.inputDetail("firstname", "lastname", "abc@gmail.com", "0123783912", Step1Page.MALE_TYPE);
        step1Page.pressDoneButton();
        checkIfStep2IsShowing();
        pressBack();
        checkIfPage1IsShowing();
        onView(withText(is("firstname"))).check(matches(isDisplayed()));
        onView(withText(is("lastname"))).check(matches(isDisplayed()));
        onView(withText(is("abc@gmail.com"))).check(matches(isDisplayed()));
        onView(withText(is("0123783912"))).check(matches(isDisplayed()));
        onView(withText(equalToIgnoringCase("Male"))).check(matches(isChecked()));
    }

    @Test
    public void testFlow_Step2PressDoneShouldGoToStep3() throws Exception {
        step1Page.inputDefaultDetail();
        step1Page.pressDoneButton();
        checkIfStep2IsShowing();
        step2Page.inputDefaultDetail();
        step2Page.pressDoneButton();
        checkIfStep3IsShowing();
    }

    @Test
    public void testFlow_pressBackOnStep3ShouldShowStep2WithPreviousDetail() throws Exception {
        step1Page.inputDefaultDetail();
        step1Page.pressDoneButton();
        checkIfStep2IsShowing();
        step2Page.inputDefaultDetail();
        step2Page.pressDoneButton();
        checkIfStep3IsShowing();
        pressBack();
        checkIfStep2IsShowing();
        step2Page.isSportChecked(Step2Page.SPORTS_STRING[2]);
    }

    @Test
    public void testFlow_pressRestartOnStep3ShouldShowStep1AsRoot() throws Exception {
        step1Page.inputDefaultDetail();
        step1Page.pressDoneButton();
        checkIfStep2IsShowing();
        step2Page.inputDefaultDetail();
        step2Page.pressDoneButton();
        checkIfStep3IsShowing();
        step3Page.withRestartButton().perform(click());
        checkIfPage1IsShowing();
        step1Page.withFirstNameViewByHint().check(matches(withText(isEmptyOrNullString())));
        step1Page.withLastNameViewByHint().check(matches(withText(isEmptyOrNullString())));
        step1Page.withEmailNameViewByHint().check(matches(withText(isEmptyOrNullString())));
        pressBack();
        //Check if press back will kill the app.
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage(containsString("forget to start"));
    }

    private void checkIfPage1IsShowing() {
        onView(withText(Step1Page.PAGE_TITLE)).check(matches(isDisplayed()));
    }

    private void checkIfStep2IsShowing() {
        onView(withText(Step2Page.PAGE_TITLE)).check(matches(isDisplayed()));
    }

    private void checkIfStep3IsShowing() {
        onView(withText(Step3Page.PAGE_TITLE)).check(matches(isDisplayed()));
    }
}

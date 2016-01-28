package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep3Activity;
import com.hasbrain.usersignupinfo.test.page.Step3Page;
import com.jupitervn.uitest.espresso.ScreenshotIfFailRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.PositionAssertions;
import android.support.test.espresso.assertion.ViewAttributeAssertions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/27/16.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep3ActivityTest {

    @Rule
    public ScreenshotIfFailRule<SignUpStep3Activity> activityRule = new ScreenshotIfFailRule<SignUpStep3Activity>(SignUpStep3Activity.class,
            new IntentsTestRule<>(SignUpStep3Activity.class));
    private Step3Page step3Page;

    @Before
    public void setUp() throws Exception {
        step3Page = new Step3Page();
    }

    @Test
    public void testUI_shouldShowTwoButtons() throws Exception {
        step3Page.withEmailButton().check(matches(isDisplayed())).check(matches(isClickable()));
        step3Page.withRestartButton().check(matches(isDisplayed())).check(matches(isClickable()));
    }

    @Test
    public void testUI_twoButtonsShouldHaveSameWidth() throws Exception {
        step3Page.withEmailButton().check(ViewAttributeAssertions.hasEqualWidth(step3Page.getRestartButton()));
    }

    @Test
    public void testUI_emailButtonShouldOnTopOfRestart() throws Exception {
        step3Page.withEmailButton().check(PositionAssertions.isAbove(step3Page.getRestartButton()));
    }

    @Test
    public void testUI_testSendEmailShouldSendIntent() throws Exception {
        Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO);
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@hasbrain.com"});
        if (sendEmailIntent.resolveActivity(InstrumentationRegistry.getTargetContext().getPackageManager()) != null) {
            step3Page.withEmailButton().perform(click());
            intended(hasAction(isOneOf(Intent.ACTION_SEND, Intent.ACTION_SENDTO)));
            intended(hasExtra(is(Intent.EXTRA_EMAIL), Matchers.arrayWithSize(Matchers.greaterThan(0))));
            intended(hasExtra(is(Intent.EXTRA_SUBJECT), containsString("User's registration info")));
        }
    }
}

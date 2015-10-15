package com.hasbrain.usersignupinfo.test;

import com.hasbrain.usersignupinfo.SignUpStep1Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 10/7/15.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpStep1ActivityTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<SignUpStep1Activity> mActivityRule = new ActivityTestRule<>(SignUpStep1Activity.class);

    @Test
    public void testUI_ShouldContainsTextWithHintFirstName() throws Exception {
        onView(withHint("First name")).check(doesNotExist());
        onView(withHint("Last name")).check(matches(isDisplayed()));
    }


}

package com.hasbrain.usersignupinfo.test.page;

import org.hamcrest.Matcher;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/28/16.
 */
public class Step3Page {

    public static final String RESTART_BUTTON_LABEL = "RESTART";
    public static final String SEND_EMAIL_BUTTON_LABEL = "SEND EMAIL";
    public static final String PAGE_TITLE = "Step 3";

    public ViewInteraction withRestartButton() {
        return onView(getRestartButton());
    }

    @NonNull
    public Matcher<View> getRestartButton() {
        return withText(equalToIgnoringCase(RESTART_BUTTON_LABEL));
    }

    public ViewInteraction withEmailButton() {
        return onView(getEmailButton());
    }

    @NonNull
    public Matcher<View> getEmailButton() {
        return withText(equalToIgnoringCase(SEND_EMAIL_BUTTON_LABEL));
    }

}

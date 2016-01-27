package com.jupitervn.uitest.espresso.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import static org.hamcrest.Matchers.is;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/26/16.
 */
public class ExtraViewMatchers {

    public static Matcher<View> hasSafelyErrorText(String errorText) {
        return hasSafelyErrorText(is(errorText));
    }

    public static Matcher<View> hasSafelyErrorText(final Matcher<String> stringMatcher) {
        assert stringMatcher != null;
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(EditText view) {
                return view.getError() != null && stringMatcher.matches(view.getError().toString());
            }
        };
    }


}

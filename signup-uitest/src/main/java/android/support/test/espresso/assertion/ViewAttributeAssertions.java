package android.support.test.espresso.assertion;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/26/16.
 */
public class ViewAttributeAssertions {
    public static ViewAssertion hasEqualWidth(final Matcher<View> viewMatcher) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                StringDescription description = new StringDescription();
                if (noViewFoundException != null) {
                    description.appendText(String.format(
                            "' check could not be performed because view '%s' was not found.\n",
                            noViewFoundException.getViewMatcherDescription()));
                    throw noViewFoundException;
                } else {
                    description.appendText("View:").appendText(HumanReadables.describe(view))
                            .appendText("'s width is not equal to")
                            .appendText(" view ")
                            .appendText(viewMatcher.toString());
                    View targetView = PositionAssertions.findView(viewMatcher, getTopViewGroup(view));
                    assertThat(description.toString(), view.getWidth(), equalTo(targetView.getWidth()));
                }
            }
        };
    }

    private static ViewGroup getTopViewGroup(View view) {
        ViewParent currentParent = view.getParent();
        ViewGroup topView = null;
        while (currentParent != null) {
            if (currentParent instanceof ViewGroup) {
                topView = (ViewGroup) currentParent;
            }
            currentParent = currentParent.getParent();
        }
        return topView;
    }

}

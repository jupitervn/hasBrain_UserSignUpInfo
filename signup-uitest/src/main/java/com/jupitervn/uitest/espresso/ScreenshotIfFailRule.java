package com.jupitervn.uitest.espresso;

import org.hamcrest.Matcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/27/16.
 */
public class ScreenshotIfFailRule<T extends Activity> extends ActivityTestRule<T> {

    public ScreenshotIfFailRule(Class<T> activityClass) {
        super(activityClass);
    }

    public ScreenshotIfFailRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public ScreenshotIfFailRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        final String testClassName = description.getClassName();
        final String testMethodName = description.getMethodName();
        final Context context = InstrumentationRegistry.getTargetContext();
        Espresso.setFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable throwable, Matcher<View> matcher) {
                DefaultFailureHandler defaultFailureHandler = new DefaultFailureHandler(context);
                //Prevent failure to be propagated if screenshot taking fails.
                Espresso.setFailureHandler(defaultFailureHandler);
                SpoonScreenshotAction.perform("failure", testClassName, testMethodName);
                defaultFailureHandler.handle(throwable, matcher);
            }
        });
        return super.apply(base, description);
    }
}
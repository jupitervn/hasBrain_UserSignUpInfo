package com.jupitervn.uitest.espresso.actions;

import org.hamcrest.Matcher;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by Jupiter (vu.cao.duy@gmail.com) on 1/26/16.
 */
public class ExtraViewActions {
    public static ViewAction setSeekbarProgresss(final int progressInPercent) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }

            @Override
            public String getDescription() {
                return String.format("Set progressInPercent value at %d%% to seekbar", progressInPercent);
            }

            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progressInPercent * seekBar.getMax() / 100);
            }
        };
    }
}

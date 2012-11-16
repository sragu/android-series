package com.tw;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;
import junit.framework.TestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.tw.FirstActivityTest \
 * com.tw.tests/android.test.InstrumentationTestRunner
 */
public class FirstActivityTest extends ActivityInstrumentationTestCase2<FirstActivity> {

    public FirstActivityTest() {
        super("com.tw", FirstActivity.class);
    }

    public void testDoSomething() {
        FirstActivity activity = getActivity();
        TextView contentView = (TextView) activity.findViewById(R.id.content_view);
        assertEquals(contentView.getText().toString(), "Android Series Chennai");
    }



}

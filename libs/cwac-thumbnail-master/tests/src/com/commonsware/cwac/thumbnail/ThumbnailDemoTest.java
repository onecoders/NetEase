package com.commonsware.cwac.thumbnail;

import android.test.ActivityInstrumentationTestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.commonsware.cwac.thumbnail.ThumbnailDemoTest \
 * com.commonsware.cwac.thumbnail.tests/android.test.InstrumentationTestRunner
 */
public class ThumbnailDemoTest extends ActivityInstrumentationTestCase<ThumbnailDemo> {

    public ThumbnailDemoTest() {
        super("com.commonsware.cwac.thumbnail", ThumbnailDemo.class);
    }

}

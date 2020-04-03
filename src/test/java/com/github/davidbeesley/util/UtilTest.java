package com.github.davidbeesley.util;

import com.github.davidbeesley.logger.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void getTimeStamp() {
        Logger.getInstance().debug(Util.getTimeStamp());
    }

    @Test
    public void getDateTimeStamp() {
        Logger.getInstance().debug(Util.getDateTimeStamp());
    }

    @Before
    public void setUp() throws Exception {
        Logger.getInstance().pushLogLevel(Logger.LogLevel.TRACE);
    }

    @After
    public void tearDown() throws Exception {
        Logger.getInstance().popLogLevel();
    }
}
package com.trafficmon;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CongestionChargeFunctionsTest {

    @Test
    public void minutesBetweenTest(){
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        long startTimeMs = 120000;
        long endTimeMs = 180000;
        assertThat(functions.minutesBetween(startTimeMs,endTimeMs),is(1));
    }

}

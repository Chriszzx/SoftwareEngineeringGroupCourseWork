package com.trafficmon;

import com.trafficmon.*;
import org.junit.Test;

import org.joda.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TimeTest {
    FakeTime faketime = new FakeTime();
    LocalTime local = new LocalTime();
    @Test
    public void CurrentTimeTest()
    {
        assertEquals(faketime.getTime(),local.getHourOfDay()*60+local.getMinuteOfHour());
    }

    @Test
    public void DelayTimeTest()
    {
        faketime.delayhours(2);
        assertEquals(faketime.getTime(),local.getHourOfDay()*60+local.getMinuteOfHour()+120);
        faketime.resetTime();
    }

    @Test
    public void SetTimeTest()
    {
        faketime.setTime(16,20);
        assertEquals(faketime.getTime(),16*60+20);
        faketime.resetTime();
    }

    @Test
    public void ResetTimeTest()
    {
        faketime.setTime(16,20);
        assertEquals(faketime.getTime(),16*60+20);
        faketime.delayhours(2);
        assertEquals(faketime.getTime(),16*60+20+120);
        faketime.resetTime();
        assertEquals(faketime.getTime(),local.getHourOfDay()*60+local.getMinuteOfHour());
    }


}
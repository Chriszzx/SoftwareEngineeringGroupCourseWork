package com.trafficmon;

import org.joda.time.LocalTime;

/*
    This class stores real world time from which the zoneboundarycrossing class read, it aslo provides methods to generate a fake time for
    testing to simulate any scenario.
 */

public class FakeTime {

    private static LocalTime localtime=new LocalTime();
    private static long currentTime=localtime.getHourOfDay()*60+localtime.getMinuteOfHour();;
    private static long offset;

    public void FakeTime()
    {
        offset=0;
    }

    void delayhours(int hour)
    {
        offset+=hour*60;
    }

    long getTime()
    {
        return currentTime+offset;
    }

    void setTime(int hour, int minute)
    {
        currentTime=hour*60+minute;
    }

    void resetTime()
    {
        currentTime=localtime.getHourOfDay()*60+localtime.getMinuteOfHour();
        offset=0;
    }
}
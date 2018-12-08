package com.trafficmon;

import org.joda.time.LocalTime;

public class FakeTime {

    private static LocalTime localtime=new LocalTime();
    private static int currentTime=localtime.getHourOfDay()*60+localtime.getMinuteOfHour();
    private static int offset;

    public void FakeTime()
    {
        offset=0;
    }

    public void delayhours(int hour)
    {
        offset=hour*60;
    }

    public int getTime()
    {
        return currentTime+offset;
    }

    public void setTime(int hour, int minute)
    {
        currentTime=hour*60+minute;
    }

    public void resetTime()
    {
        currentTime=localtime.getHourOfDay()*60+localtime.getMinuteOfHour();
        offset=0;
    }
}
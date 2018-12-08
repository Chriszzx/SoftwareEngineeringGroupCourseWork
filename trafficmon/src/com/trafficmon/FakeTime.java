package com.trafficmon;

import org.joda.time.LocalTime;

    public class FakeTime {

        private static int currentTime;
        private static int offset;

        void delayhours(int hour)
        {
            offset+=hour*60;
        }

        int getTime()
        {
            return currentTime+offset;
        }

        void setTime(int hour,int minute)
        {
            currentTime= hour * 60 + minute;
        }

        public void resetTime()
        {
            currentTime=new LocalTime().getHourOfDay()*60+new LocalTime().getMinuteOfHour();
        }
    }


package NewTest;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

public class FakeTime {

    private static int currentTime;
    private static int offset;
    public void FakeTime()
    {

        currentTime=new LocalTime().getHourOfDay()*60+new LocalTime().getMinuteOfHour();
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
        currentTime=new LocalTime().getHourOfDay()*60+new LocalTime().getMinuteOfHour();
    }
}

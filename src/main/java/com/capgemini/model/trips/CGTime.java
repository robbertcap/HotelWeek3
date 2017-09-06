package com.capgemini.model.trips;

import java.sql.Time;
import java.util.TimeZone;

public class CGTime
{
    private long seconds;

    public CGTime()
    {
        this.seconds = 0;
    }

    public static CGTime now()
    {
        CGTime aTime;
        aTime = CGTime.fromSeconds (System.currentTimeMillis() / 1000) ;
        return  aTime;
    }

    public static CGTime fromSeconds(long iseconds)
    {
        CGTime aTime;
        aTime = new CGTime();
        aTime.seconds = iseconds;
        return aTime;
    }

    public CGTime addTime(CGTime aTime)
    {
        CGTime aNewTime;
        aNewTime = CGTime.fromSeconds(this.asSeconds() + aTime.asSeconds());
        return aNewTime;
    }

    public long asSeconds()
    {
        return this.seconds;
    }

    public CGTime subtractTime(CGTime aTime)
    {
        CGTime aNewTime = CGTime.fromSeconds(this.asSeconds() - aTime.asSeconds());
        return aNewTime;
    }

    public String toString ()
    {
        Time aJavaTime;

        // The java Time class can make strings of time objects
        // However,unwanted conversations take place in relation with GMT
        // Correct the situation
        TimeZone current = TimeZone.getDefault();
        TimeZone aTZ = TimeZone.getTimeZone("GMT");
        TimeZone.setDefault(aTZ);

        aJavaTime  = new Time (this.asSeconds() * 1000);
        String timeString;
        timeString = aJavaTime.toString();

        TimeZone.setDefault(current);
        return timeString;
    }
}

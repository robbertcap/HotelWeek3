package com.capgemini.model.trips;

public class RiverTrip extends Trip
{
    public CGTime getDuration()
    {
        CGTime bonus, aDuration;
        bonus = CGTime.fromSeconds (30 * 60);
        aDuration = super.getDuration();
        aDuration = aDuration.subtractTime(bonus);
        return aDuration;
    }
}
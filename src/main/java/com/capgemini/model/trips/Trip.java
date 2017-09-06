package com.capgemini.model.trips;

public abstract class Trip
{
    private CGTime startTime;
    private CGTime endTime;

    public Trip()
    {
        this.startTime = CGTime.now();
    }

    public void end()
    {
        this.endTime = CGTime.now();
    }

    public boolean isEnded()
    {
        boolean answer;
        answer = (this.endTime != null);
        return answer;
    }

    public CGTime getDuration()
    {
        CGTime aDuration;
        aDuration = this.endTime.subtractTime(this.startTime);
        return aDuration;
    }
}
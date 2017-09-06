package com.capgemini.model.trips;

import java.util.ArrayList;

public class Tripbox
{
    private ArrayList trips;

    public Tripbox()
    {
        this.trips = new ArrayList();
    }

    public Trip getTrip(int number)
    {
        Trip aTrip =(Trip)this.trips.get(number-1);
        return aTrip;
    }

    public int getNumberOfEndedTrips()
    {
        int numberTrips, numberEndedTrips;
        numberTrips = this.trips.size();
        numberEndedTrips = 0;
        for (int i=1; i<=numberTrips; i=i+1)
        {
            boolean answer;
            Trip aTrip;
            aTrip = this.getTrip(i);
            answer = aTrip.isEnded();
            if (answer)
            {
                numberEndedTrips = numberEndedTrips + 1;
            }
        }
        return numberEndedTrips;
    }

    public CGTime getAverageDuration()
    {
        int numberTrips, numberEndedTrips;
        CGTime totalDuration;
        CGTime averageDuration;
        numberTrips = this.trips.size();
        numberEndedTrips = 0;
        totalDuration = CGTime.fromSeconds(0);

        for (int i=1; i<=numberTrips; i=i+1)
        {
            boolean answer;
            CGTime aDuration;
            Trip aTrip;
            aTrip = this.getTrip(i);
            answer = aTrip.isEnded();
            if (answer)
            {
                numberEndedTrips = numberEndedTrips + 1;
                aDuration = aTrip.getDuration();
                totalDuration = totalDuration.addTime(aDuration);
            }
        }
        if (numberEndedTrips > 0)
        {
            long durationInSeconds;
            long averageDurationInSeconds;
            durationInSeconds = totalDuration.asSeconds();
            averageDurationInSeconds = durationInSeconds / numberEndedTrips;
            averageDuration = CGTime.fromSeconds(averageDurationInSeconds);
        }
        else
        {
            averageDuration = CGTime.fromSeconds(0);
        }
        return averageDuration;
    }

    public CGTime getTotalDuration()
    {
        int numberTrips;
        CGTime totalDuration;
        numberTrips = this.trips.size();
        totalDuration = CGTime.fromSeconds(0);

        for (int i=1; i<=numberTrips; i=i+1)
        {
            boolean answer;
            CGTime aDuration;
            Trip aTrip;
            aTrip = this.getTrip(i);
            answer = aTrip.isEnded();
            if (answer)
            {
                aDuration = aTrip.getDuration();
                totalDuration = totalDuration.addTime(aDuration);
            }
        }
        return totalDuration;
    }

    public int save(Trip aTrip)
    {
        int tripNumber;
        this.trips.add (aTrip);
        tripNumber = this.trips.size();
        return tripNumber;
    }
}
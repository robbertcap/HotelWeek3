package com.capgemini.model.trips;

public class Boat {

    private CGTime sailingTime;
    private int boatNumber;
    private Trip theLastTrip;

    public Boat(int number)
    {
        this.sailingTime = CGTime.fromSeconds(0);
        this.boatNumber = number;
        // this.boatTrips = new Tripbox();
    }

    public boolean hasNumber(int number)
    {
        boolean answer;
        answer = (this.boatNumber == number);
        return answer;
    }

    public boolean isAvailable()
    {
        boolean available;
        if (this.theLastTrip == null)
        {
            available = true;
        }
        else
        {
            available = this.theLastTrip.isEnded();
        }
        return available;
    }

    public int getNumber()
    {
        return this.boatNumber;
    }

    public boolean isInspectionRequired()
    {
        boolean required;
        long sailingSeconds;
        // long sailingTime;
        // sailingTime = this.boatTrips.getTotalDuration();
        sailingSeconds = this.sailingTime.asSeconds();
        required = (sailingSeconds >= 1800);
        return required;
    }

    public void resetInspection()
    {
        this.sailingTime = CGTime.fromSeconds(0);
        // this.boatTrips = new Tripbox();
    }

    public Trip makeLakeTrip()
    {
        this.theLastTrip = new LakeTrip();
        // this.boatTrips.save(this.theLastTrip);
        return this.theLastTrip;
    }

    public Trip makeRiverTrip()
    {
        this.theLastTrip = new RiverTrip();
        // this.boatTrips.save(this.theLastTrip);
        return this.theLastTrip;
    }

    public CGTime endTrip()
    {
        CGTime aDuration;
        this.theLastTrip.end();
        aDuration = this.theLastTrip.getDuration();
        this.sailingTime = sailingTime.addTime(aDuration);
        // previous statement lapsed in alternative solution
        return aDuration;
    }
}
